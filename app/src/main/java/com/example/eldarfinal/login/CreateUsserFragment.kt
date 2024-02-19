package com.example.eldarfinal.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eldarfinal.R
import com.example.eldarfinal.databinding.FragmentCreateUsserBinding
import com.example.eldarfinal.login.data.AppDatabase
import com.example.eldarfinal.login.data.UserEntity
import com.example.eldarfinal.login.data.UserRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CreateUsserFragment : Fragment(R.layout.fragment_create_usser) {

    private lateinit var userRepository: UserRepository
    private lateinit var binding: FragmentCreateUsserBinding

    var isPasswordVisible = false
    private val passwordEditText: TextInputEditText
        get() = binding.dniEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCreateUsserBinding.bind(view)
        userRepository = UserRepository(AppDatabase.getInstance(requireContext()).userDao())

        binding.btnCreateUsser.setOnClickListener {
            val fullName = binding.fullNameEditText.text.toString()
            val dni = binding.dniEditText.text.toString()

            if (fullName.isNotEmpty() && dni.isNotEmpty()) {
                val user = UserEntity(fullName = fullName, dni = dni)
                MainScope().launch {
                    userRepository.insertUser(user)

                    // Indica al usuario que se ha creado exitosamente.
                    Toast.makeText(requireContext(), "Usuario creado exitosamente.", Toast.LENGTH_SHORT).show()
                    // Navega de regreso al fragmento de inicio de sesión
                    findNavController().navigate(R.id.action_createUsserFragment_to_loginFragment)
                }
            } else {
                // Muestra un mensaje de error indicando que se deben completar ambos campos.
                Toast.makeText(requireContext(), "Por favor, completa ambos campos.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_createUsserFragment_to_loginFragment)
        }

        val btn_hidePass = requireView().findViewById(R.id.btn_hidePass) as View
        val btn_showPass = requireView().findViewById(R.id.btn_showPass) as View
        btn_hidePass.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility()
            btn_hidePass.visibility = View.GONE
            btn_showPass.visibility = View.VISIBLE
        }
        btn_showPass.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility()
            btn_hidePass.visibility = View.VISIBLE
            btn_showPass.visibility = View.GONE
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Mostrar contraseña
            passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            // Ocultar contraseña
            passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }
}