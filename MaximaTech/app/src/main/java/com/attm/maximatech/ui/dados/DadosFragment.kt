package com.attm.maximatech.ui.dados

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.attm.maximatech.R
import com.attm.maximatech.ui.ClientesActivity
import com.attm.maximatech.databinding.FragmentDadosBinding
import com.attm.maximatech.utils.InjectorUtils
import com.google.android.material.snackbar.Snackbar
import java.util.*

class DadosFragment : Fragment() {

    val dadosViewModel: DadosViewModel by viewModels {
        InjectorUtils.provideDadosViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Fragment", dadosViewModel.dadosCliente.value?.dados.toString())
        val binding = DataBindingUtil.inflate<FragmentDadosBinding>(inflater, R.layout.fragment_dados, container, false).apply {
            viewModel = dadosViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        val adapter = DadosAdapter(dadosViewModel, viewLifecycleOwner)
        binding.listContact.adapter = adapter
        subscribeUi(adapter)
        binding.verifyStatus.setOnClickListener {
            val snackbar = Snackbar.make(binding.root, (DateFormat.format("dd/MM/yy HH:mm", Date()).toString() + " - " + dadosViewModel.dadosCliente.value?.dados?.status), Snackbar.LENGTH_SHORT)
            snackbar.setAction("Fechar"){snackbar.dismiss()}.setActionTextColor(Color.parseColor("#638735")).show()
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as ClientesActivity).showBottomNavigation()
    }

    private fun subscribeUi(adapter: DadosAdapter) {
        dadosViewModel.dadosCliente.observe(viewLifecycleOwner) { contact ->
            adapter.contatos.apply {
                clear()
                contact.contatos?.let { addAll(it) }
            }
            adapter.notifyDataSetChanged()
        }
    }
}