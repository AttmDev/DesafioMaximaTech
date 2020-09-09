package com.attm.maximatech.ui.historico

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.attm.maximatech.data.entity.Pedidos
import com.attm.maximatech.data.repository.Repository

class HistoricoViewModel constructor(
 val repository: Repository) : ViewModel() {

    val dadosHistorico: LiveData<List<Pedidos>> = repository.getPedidosLiveData()

}