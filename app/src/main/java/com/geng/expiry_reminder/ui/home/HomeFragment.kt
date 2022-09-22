package com.geng.expiry_reminder.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geng.expiry_reminder.R
import com.geng.expiry_reminder.activities.NewCategoryActivity.NewCategoryActivity
import com.geng.expiry_reminder.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var fabMenuButton: FloatingActionButton
    lateinit var newCategoryButton: FloatingActionButton
    lateinit var fab3: FloatingActionButton
    var isFABOpen: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val categoryListView: RecyclerView = binding.categoryListView
        categoryListView.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        homeViewModel.categoryViewAdapter.observe(viewLifecycleOwner) {
            categoryListView.adapter = it
        }
        fabMenuButton = root.findViewById<View>(R.id.new_action_button) as FloatingActionButton
        newCategoryButton = root.findViewById<View>(R.id.new_category_button) as FloatingActionButton
        fab3 = root.findViewById<View>(R.id.new_reminder_button) as FloatingActionButton
        fabMenuButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                Log.d("fabMenuButton CLick", isFABOpen.toString())
                if (!isFABOpen) {
                    showFABMenu()
                } else {
                    closeFABMenu()
                }
            }
        })

        newCategoryButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(context, NewCategoryActivity::class.java).apply {
                    // putExtra(EXTRA_MESSAGE, message)
                }
                startActivity(intent)
            }
        })
        return root
    }

    private fun showFABMenu() {
        isFABOpen = true
        fabMenuButton.animate().rotation(360f)
        newCategoryButton.animate().translationY(resources.getDimension(R.dimen.standard_55))
        fab3.animate().translationY(resources.getDimension(R.dimen.standard_105))
    }

    private fun closeFABMenu() {
        fabMenuButton.animate().rotation(0f)
        isFABOpen = false
        newCategoryButton.animate().translationY(resources.getDimension(R.dimen.fab_button_closed))
        fab3.animate().translationY(resources.getDimension(R.dimen.fab_button_closed))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}