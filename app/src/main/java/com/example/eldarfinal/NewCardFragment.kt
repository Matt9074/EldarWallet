package com.example.eldarfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.eldarfinal.databinding.FragmentNewCardBinding
import com.example.eldarfinal.home.Tarjeta
import com.example.eldarfinal.viewmodel.HomeViewModel


class NewCardFragment : Fragment() {

    private lateinit var binding: FragmentNewCardBinding
    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewCardBinding.inflate(inflater, container, false)
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewCardBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        setupAddCardButton()
    }

    private fun setupAddCardButton() {
    binding.btnAddCard.setOnClickListener {
        val numero = binding.numeroEditText.text.toString()
        val codigoSeguridad = binding.codigoEditText.text.toString()
        val vencimiento = binding.vencimientoEditText.text.toString()

        if (numero.isNotBlank() && codigoSeguridad.isNotBlank() && vencimiento.isNotBlank()) {
            val nombreTarjeta = when (numero.firstOrNull()) {
                '3' -> "American Express"
                '4' -> "Visa"
                '5' -> "Mastercard"
                else -> "Desconocido"
            }

            val nuevaTarjeta = Tarjeta(nombreTarjeta, numero, codigoSeguridad, vencimiento)
            viewModel.agregarNuevaTarjeta(nuevaTarjeta)
            findNavController().navigateUp()
        }
    }
}
}



