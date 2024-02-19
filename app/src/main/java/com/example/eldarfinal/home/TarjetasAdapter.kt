package com.example.eldarfinal.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarfinal.databinding.ItemTarjetaBinding

class TarjetasAdapter(private var tarjetas: List<Tarjeta>) : RecyclerView.Adapter<TarjetasAdapter.TarjetaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarjetaViewHolder {
        val binding = ItemTarjetaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TarjetaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TarjetaViewHolder, position: Int) {
        holder.bind(tarjetas[position])
    }

    override fun getItemCount(): Int {
        return tarjetas.size
    }

    inner class TarjetaViewHolder(private val binding: ItemTarjetaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tarjeta: Tarjeta) {
            binding.nombreTarjeta.text = tarjeta.nombre
            binding.numeroTarjeta.text = tarjeta.numero
            binding.codigoTarjeta.text = tarjeta.codigoSeguridad
            binding.vencimientoTarjeta.text = tarjeta.vencimiento
        }
    }

    fun actualizarTarjetas(nuevasTarjetas: List<Tarjeta>) {
        tarjetas = nuevasTarjetas
        notifyDataSetChanged()

        Log.d("TarjetasAdapter", "Se llamó a notifyDataSetChanged")
    }
}