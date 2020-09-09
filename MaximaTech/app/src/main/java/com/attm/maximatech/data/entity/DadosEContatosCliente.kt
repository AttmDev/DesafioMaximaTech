package com.attm.maximatech.data.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.attm.maximatech.data.entity.ContatoCliente
import com.attm.maximatech.data.entity.DadosCliente

data class DadosEContatosCliente (
    @Embedded
    val dados: DadosCliente,

    @Relation(parentColumn = "id", entityColumn = "cliente_id")
    val contatos: List<ContatoCliente>? = emptyList()
)