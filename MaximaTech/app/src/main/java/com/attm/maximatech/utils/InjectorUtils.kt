package com.attm.maximatech.utils

import android.content.Context
import com.attm.maximatech.ui.dados.DadosViewModelFactory
import com.attm.maximatech.ui.historico.HistoricoViewModelFactory
import com.attm.maximatech.data.repository.Repository

object InjectorUtils {


    fun provideDadosViewModelFactory(
        context: Context
    ): DadosViewModelFactory {
        return DadosViewModelFactory(
            Repository.initialize(context)
        )
    }

    fun provideHistoricoViewModelFactory(
        context: Context
    ): HistoricoViewModelFactory {
        return HistoricoViewModelFactory(
            Repository.initialize(context)
        )
    }

}