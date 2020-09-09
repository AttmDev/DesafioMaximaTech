package com.attm.maximatech.ui.dados

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.attm.maximatech.R
import com.attm.maximatech.data.entity.ContatoCliente
import com.attm.maximatech.databinding.ItemContatoBinding

class DadosAdapter(var viewModel: DadosViewModel, lifecycleOwner: LifecycleOwner): RecyclerView.Adapter<DadosAdapter.ViewHolder>() {

    var contatos: MutableList<ContatoCliente> = emptyList<ContatoCliente>().toMutableList()

    class ViewHolder(var binding: ItemContatoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(contato: ContatoCliente){
            binding.apply {
                contact = contato
                executePendingBindings()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_contato, parent, false))
    }
    override fun getItemCount() = contatos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(contatos[position])
    }

}