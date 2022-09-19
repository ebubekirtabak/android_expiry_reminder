package com.geng.expiry_reminder.activities.NewCategoryActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import com.geng.expiry_reminder.R
import com.geng.expiry_reminder.adapters.ColorItemAdapter
import com.geng.expiry_reminder.databinding.FragmentNewCategoryBinding
import com.geng.expiry_reminder.models.items.ColorItem

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentNewCategoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colorSpinner: Spinner = view.findViewById(R.id.color_spinner)
        val colorItemAdapter = ColorItemAdapter(
            context,
            R.layout.color_spinner_item,
            listOf(ColorItem(0, "Red", R.color.red))
        )
        colorItemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorSpinner.setOnItemSelectedListener(this)
        colorSpinner.adapter = colorItemAdapter

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
