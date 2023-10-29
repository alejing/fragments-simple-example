package com.example.simplenavigationfragments.ui

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.simplenavigationfragments.MainActivity
import com.example.simplenavigationfragments.R
import com.example.simplenavigationfragments.databinding.FragmentPrincipalBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView


class PrincipalFragment : Fragment(), MainActivity.FragmentInteractionListener, MenuProvider {

    private var _binding: FragmentPrincipalBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var root: View
    private lateinit var bottomBar: BottomNavigationView
    private lateinit var toolBar: MaterialToolbar

    // Access to sent args by the IniciarSesionFragment
    val args: PrincipalFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        root = binding.root

        // Show the AppToolBar
        toolBar = requireActivity().findViewById(R.id.app_toolbar)
        toolBar.isVisible = true

        // Enable the BottomBarNavigationView on this fragment
        bottomBar = requireActivity().findViewById(R.id.bottom_nav_bar)
        bottomBar.isGone = false

        // Put the menuItem Principal checked as default
        bottomBar.menu.findItem(R.id.bottom_nav_principal).isChecked = true

        // Help to control the menu in this specific Fragment
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // Navigation of the BottomNavigationView
        bottomBar.setOnItemSelectedListener {menuItem ->
            when (menuItem.itemId){
                R.id.bottom_nav_principal ->{
                    true
                }
                R.id.bottom_nav_cuenta ->{
                    findNavController().navigate(R.id.action_principalFragment_to_cuentaFragment)
                    true
                }
                R.id.bottom_nav_configuracion -> {
                    findNavController().navigate(R.id.action_principalFragment_to_configuracionFragment)
                    true
                }
                else -> false
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Disable the native back button functionality
        setupBackPressListener()

        // Catch the args with the user (Safe Args)
        //val user = args.user

        // Read the sharedPreferences file
        val sharedPreferences = context?.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val user = sharedPreferences?.getString("user", "Unknown user")

        // Put the user in the textView
        binding.prinTextView.text = getString(
            R.string.fragment_custom_messages, user, getString(R.string.prin_title_toolbar)
        )
    }

    override fun onResume() {
        super.onResume()
        // Call of the updateToolBarTextView function with the new ToolBar Title
        updateToolBarTextView(root.resources.getString(R.string.prin_title_toolbar))
    }

    override fun onStop() {
        super.onStop()
        // Hide the ActionBar when the Fragment disappear
        toolBar.isVisible = false
        // Hide the BottomNavigationView when the Fragment disappear
        bottomBar.isGone = true
    }

    // Override updateToolBarTextView method with the ability to change the text present
    // on the title ToolBar
    override fun updateToolBarTextView(text: String) {
        // Update a ToolBarTextView in the hosting Activity
        (activity as? MainActivity)?.updateToolBarTextView(text)
    }

    // Disable the native back button functionality
    private fun setupBackPressListener() {
        this.view?.isFocusableInTouchMode = true
        this.view?.requestFocus()
        this.view?.setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_BACK
        }
    }

    // Control the menu in the Top AppBar (custom layout)
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.appbar_activity_menu, menu)
    }

    // Control the menu in the Top AppBar (functionality)
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.icon_item_close -> {
                findNavController().navigate(R.id.action_principalFragment_to_inicioFragment)
                true
            }
            else -> false
        }
    }
}