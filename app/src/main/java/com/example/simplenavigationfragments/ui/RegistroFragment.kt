package com.example.simplenavigationfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.simplenavigationfragments.MainActivity
import com.example.simplenavigationfragments.R
import com.example.simplenavigationfragments.databinding.FragmentRegistroBinding
import com.google.android.material.appbar.MaterialToolbar

class RegistroFragment : Fragment(), MainActivity.FragmentInteractionListener {

    private var _binding: FragmentRegistroBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var root: View
    private lateinit var toolBar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        root = binding.root

        // Show the AppToolBar
        toolBar = requireActivity().findViewById(R.id.app_toolbar)
        toolBar.isVisible = true

        return root
    }

    override fun onResume() {
        super.onResume()
        // Call of the updateToolBarTextView function with the new ToolBar Title
        updateToolBarTextView(root.resources.getString(R.string.reg_title_toolbar))
    }

    override fun onStop() {
        super.onStop()
        // Hide the ActionBar when the Fragment disappear
        toolBar.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // override updateToolBarTextView method with the ability to change the text present
    // on the title ToolBar
    override fun updateToolBarTextView(text: String) {
        // Update a ToolBarTextView in the hosting Activity
        (activity as? MainActivity)?.updateToolBarTextView(text)
    }

}