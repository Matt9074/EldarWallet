package com.example.eldarfinal.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eldarfinal.home.Tarjeta

class HomeViewModel : ViewModel() {
    private val _tarjetasList = MutableLiveData<List<Tarjeta>>(emptyList())
    val tarjetasList: LiveData<List<Tarjeta>> = _tarjetasList

    fun agregarNuevaTarjeta(nuevaTarjeta: Tarjeta) {
        val currentList = _tarjetasList.value.orEmpty().toMutableList()
        val tarjetaConTipo = Tarjeta(
            nuevaTarjeta.nombre,
            nuevaTarjeta.numero,
            nuevaTarjeta.codigoSeguridad,
            nuevaTarjeta.vencimiento,
        )
        currentList.add(tarjetaConTipo)
        _tarjetasList.value = currentList

        // Agrega un log para verificar si se agrega una nueva tarjeta
        Log.d("HomeViewModel", "Nueva tarjeta agregada: ${nuevaTarjeta.nombre}")
    }
}