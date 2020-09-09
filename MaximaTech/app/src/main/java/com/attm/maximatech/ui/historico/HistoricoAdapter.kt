package com.attm.maximatech.ui.historico

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.attm.maximatech.R
import com.attm.maximatech.data.entity.Pedidos
import com.attm.maximatech.databinding.HistoricoItemBinding

class HistoricoAdapter(): RecyclerView.Adapter<HistoricoAdapter.ViewHolder>() {

    var pedidos: MutableList<Pedidos> = emptyList<Pedidos>().toMutableList()

    class ViewHolder(var binding: HistoricoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(pedidos: Pedidos) {
            binding.apply {
                pedido = pedidos
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.historico_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(pedidos[position])
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }

}