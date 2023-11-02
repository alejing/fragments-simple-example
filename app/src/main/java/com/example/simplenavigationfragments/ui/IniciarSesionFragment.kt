package com.example.simplenavigationfragments.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.simplenavigationfragments.MainActivity
import com.example.simplenavigationfragments.R
import com.example.simplenavigationfragments.database.AppDatabase
import com.example.simplenavigationfragments.database.user.UserDao
import com.example.simplenavigationfragments.database.user.Usuario
import com.example.simplenavigationfragments.databinding.FragmentIniciarSesionBinding
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch


class IniciarSesionFragment : Fragment(), MainActivity.FragmentInteractionListener {

    private var _binding: FragmentIniciarSesionBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var root: View
    private lateinit var toolBar: MaterialToolbar
    private lateinit var sharedPreferences: SharedPreferences

    // Database data configurations
    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(requireActivity().baseContext)
    }
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create the userDao from Database
        userDao = database.UserDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentIniciarSesionBinding.inflate(inflater, container, false)
        root = binding.root

        // Show the AppToolBar
        toolBar = requireActivity().findViewById(R.id.app_toolbar)
        toolBar.isVisible = true

        // Create the sharedPreferences file
        sharedPreferences = requireActivity().getSharedPreferences(
            "MyAppPreferences", Context.MODE_PRIVATE
        )

        // Get all the users from the database and show in the console
        lifecycleScope.launch {
            val users = userDao.getAll()
            //println("users $users")
            for (user in users) {
                println("${user.id}. ${user.user}, ${user.name}, ${user.lastName}, ${user.password}")
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate to Principal Fragment with Safe Args
        binding.btnIngresar.setOnClickListener {
            // Edit the sharedPreferences file with the user
            val editor = sharedPreferences.edit()
            lifecycleScope.launch {

                // Catch the user and password from the UI and search in the database
                val user = userDao.getUserById(
                    binding.usuario.editText?.text.toString(),
                    binding.contraseA.editText?.text.toString()
                )
                // Check if the user exist
                if (user != null) {
                    //println("user: ${user.user} and password: ${user.password}")
                    editor?.putString("user", user.user)
                    editor?.apply()
                    // Navigate
                    findNavController().navigate(
                        IniciarSesionFragmentDirections.actionIniciarSesionFragmentToPrincipalFragment(
                            user = user.user
                        )
                    )
                }else {
                    Toast.makeText(requireContext(), "Usuario y/o contraseña no existe.", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        // Call of the updateToolBarTextView function with the new ToolBar Title
        updateToolBarTextView(root.resources.getString(R.string.ini_ses_title_toolbar))
        // Put again the ToolBar when the app has been the focus
        toolBar.isVisible = true
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