package com.attm.maximatech.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "dados_cliente")
@Parcelize
data class DadosCliente @Ignore constructor(
    @field:[PrimaryKey] val id: Int = 0,
    @field:[ColumnInfo] val codigo: String = "",
    @field:[ColumnInfo SerializedName("razao_social")] val razaoSocial: String? = "",
    @field:[ColumnInfo] val nomeFantasia: String? = "",
    @field:[ColumnInfo] val cnpj: String? = null,
    @field:[ColumnInfo SerializedName("ramo_atividade")] val ramoAtividade: String? = null,
    @field:[ColumnInfo] val endereco: String? = null,
    @field:[ColumnInfo] val status: String? = null,
    @field:[ColumnInfo Ignore] val codigoERazaoSocial: String = "$codigo - $razaoSocial",
    @field:[ColumnInfo Ignore] val contatos: List<ContatoCliente>? = emptyList()
) : Parcelable {
    constructor(
         id: Int = 0,
         codigo: String = "",
         razaoSocial: String? = "",
         nomeFantasia: String? = "",
         cnpj: String? = null,
         ramoAtividade: String? = null,
         endereco: String? = null,
         status: String? = null
    ) : this(
        id = id,
        codigo = codigo,
        razaoSocial = razaoSocial,
        nomeFantasia = nomeFantasia,
        cnpj = cnpj,
        ramoAtividade = ramoAtividade,
        endereco = endereco,
        status = status,
        codigoERazaoSocial = "$codigo - $razaoSocial",
        contatos = null
    )

}
