package com.attm.maximatech.ui.historico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.attm.maximatech.data.repository.Repository

class HistoricoViewModelFactory (
    private val repository: Repository
    ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HistoricoViewModel(repository) as T
        }

}
