package com.attm.maximatech.ui.dados

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.attm.maximatech.data.entity.DadosEContatosCliente
import com.attm.maximatech.data.repository.Repository

class DadosViewModel constructor(
 val repository: Repository) : ViewModel() {


    val dadosCliente: LiveData<DadosEContatosCliente> = repository.getClientesLiveData()

}