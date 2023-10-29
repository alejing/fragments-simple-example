package com.example.simplenavigationfragments.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.simplenavigationfragments.R
import com.example.simplenavigationfragments.databinding.FragmentInicioBinding


class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentInicioBinding.inflate(inflater, container, false)

        // Navigate to Iniciar Sesión Fragment
        binding.iniBtnIniciarSesion.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_iniciarSesionFragment)
        }
        // Navigate to Registro Fragment
        binding.iniBtnRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_registroFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Disable the native back button functionality
        setupBackPressListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Disable the native back button functionality
    private fun setupBackPressListener() {
        this.view?.isFocusableInTouchMode = true
        this.view?.requestFocus()
        this.view?.setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_BACK
        }
    }

}