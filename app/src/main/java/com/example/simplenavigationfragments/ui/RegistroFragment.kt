package com.example.simplenavigationfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.simplenavigationfragments.MainActivity
import com.example.simplenavigationfragments.R
import com.example.simplenavigationfragments.database.AppDatabase
import com.example.simplenavigationfragments.database.user.UserDao
import com.example.simplenavigationfragments.database.user.Usuario
import com.example.simplenavigationfragments.databinding.FragmentRegistroBinding
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch

class RegistroFragment : Fragment(), MainActivity.FragmentInteractionListener {

    private var _binding: FragmentRegistroBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var root: View
    private lateinit var toolBar: MaterialToolbar

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
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        root = binding.root

        // Show the AppToolBar
        toolBar = requireActivity().findViewById(R.id.app_toolbar)
        toolBar.isVisible = true

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Catch the UI EditText data and insert a new user in the database
        binding.btnRegistro.setOnClickListener {
            lifecycleScope.launch {
                val user = binding.usuario.editText?.text.toString().trim()
                val name = binding.nombre.editText?.text.toString().trim()
                val lastName = binding.apellido.editText?.text.toString().trim()
                val password = binding.contrasena.editText?.text.toString().trim()
                //val confirmPassword = binding.contrasenaNuevo.editText?.text.toString().trim()
                // TODO After insert, check if the fields are empty.
                // TODO After insert, check if the password field and confirmPassword are the same
                val userToInsert = Usuario(0, user, name, lastName, password)
                userDao.insertUser(userToInsert)
                Toast.makeText(requireContext(), "Usuario Registrado", Toast.LENGTH_SHORT).show()
                clearFields()
            }
        }
        // Clear the UI EditText
        binding.btnLimpiar.setOnClickListener {
            clearFields()
        }
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

    private fun clearFields() {
        // Clear all the EditText
        binding.usuario.editText?.text?.clear()
        binding.nombre.editText?.text?.clear()
        binding.apellido.editText?.text?.clear()
        binding.contrasena.editText?.text?.clear()
        binding.contrasenaNuevo.editText?.text?.clear()
        // Put the focus on the EditText usuario
        binding.usuario.editText?.requestFocus()
    }

}