package com.example.trabajofinalproyectomarcelojara.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.trabajofinalproyectomarcelojara.databinding.FragmentSecondBinding
import com.example.trabajofinalproyectomarcelojara.viewModel.ViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ViewModel by activityViewModels()
    private var shoeId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            shoeId = bundle.getInt("shoeId")
        }
        shoeId?.let { id->
            viewModel.getShoeDetailByIdFromInternet(id)
        }
        viewModel.getShoesDetail().observe(viewLifecycleOwner, Observer {
            var id = it.id
            var name = it.nombre
            Glide.with(binding.imgDetail).load(it.imagenLink).into(binding.imgDetail)
            binding.txtName.text = it.nombre
            binding.txtBrand.text = it.marca
            binding.txtColors.text = it.Colores
            binding.txtSize.text = "Talla: ${it.numero.toString()}"
            binding.txtPrice.text = "$${it.precio.toString()}"
            if (it.entrega){
                binding.txtDelivery.text = "Cuenta con despacho"
            }else{
                binding.txtDelivery.text = "Sin despacho"
            }

            binding.btnMail.setOnClickListener {
                val mintent = Intent(Intent.ACTION_SEND)
                mintent.data = Uri.parse("mailto")
                mintent.type = "text/plain"

                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("Zapato.ventas@unica.cl"))
                mintent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por producto $name id: $id"
                )
                mintent.putExtra(
                    Intent.EXTRA_TEXT,"“Hola\n" +
                            "Vi el producto $name de código $id y me gustaría\n" +
                            "que me contactaran a este correo o al siguiente número\n" +
                            "+569 73445532\n"+
                            "Quedo atento.”"
                )
                try {
                    startActivity(mintent)
                }catch (e:Exception){
                    Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
                }
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}