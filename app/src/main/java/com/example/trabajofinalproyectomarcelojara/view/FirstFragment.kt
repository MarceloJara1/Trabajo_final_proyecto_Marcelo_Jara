package com.example.trabajofinalproyectomarcelojara.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trabajofinalproyectomarcelojara.R
import com.example.trabajofinalproyectomarcelojara.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private val viewModel: com.example.trabajofinalproyectomarcelojara.viewModel.ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = Adapter()
        binding.recyclerFirst.adapter = adapter
        binding.recyclerFirst.layoutManager = GridLayoutManager(context, 2)
        viewModel.getShoesList().observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.update(it)
            }
        })

        adapter.selectedShoe().observe(viewLifecycleOwner, Observer{
            val bundle = Bundle().apply {
                putInt("shoeId", it.id)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}