package com.example.eldarfinal.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.eldarfinal.R
import com.example.eldarfinal.databinding.FragmentLoginBinding
import com.example.eldarfinal.login.data.AppDatabase
import com.example.eldarfinal.login.data.UserRepository
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var userRepository: UserRepository

    var isPasswordVisible = false
    private val passwordEditText: TextInputEditText
        get() = binding.passwordEditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        userRepository = UserRepository(AppDatabase.getInstance(requireContext()).userDao())

        setListeners()
    }

    private fun setListeners() {

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

        binding.btnLogin.setOnClickListener {

            val dni = binding.passwordEditText.editableText.toString()
            val fullName = binding.userEditText.editableText.toString()

            if (fullName.isNotEmpty() && dni.isNotEmpty()) {
                MainScope().launch {
                    val user = userRepository.getUserByFullNameAndDni(fullName, dni)
                    if (user != null) {
                        //Si los datos coinciden, navegar al HomeFragment
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        // Mostrar un mensaje de error indicando que las credenciales son incorrectas.
                        Toast.makeText(
                            requireContext(),
                            "Credenciales incorrectas. Inténtalo nuevamente.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                // Mostrar un mensaje de error indicando que se deben ingresar el nombre, apellido y DNI.
                Toast.makeText(
                    requireContext(),
                    "Por favor, ingresa tu nombre, apellido y DNI.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createUsserFragment)
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