package com.attm.maximatech.ui.dados

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.attm.maximatech.data.repository.Repository

class DadosViewModelFactory(
    private val repository: Repository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DadosViewModel(repository) as T
    }

}
