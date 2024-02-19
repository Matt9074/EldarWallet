package com.example.eldarfinal

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eldarfinal.databinding.QrFragmentBinding


class QrFragment : Fragment() {

    private lateinit var binding: QrFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QrFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val qrBitmap: Bitmap? = arguments?.getParcelable("qr_bitmap")
        qrBitmap?.let {
            binding.qrImageView.setImageBitmap(it)
        }

        binding.btnQrback.setOnClickListener {
            findNavController().navigate(R.id.action_qrFragment_to_homeFragment)
        }
    }
}