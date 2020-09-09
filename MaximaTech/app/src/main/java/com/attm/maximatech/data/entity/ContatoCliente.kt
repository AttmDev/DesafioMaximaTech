package com.attm.maximatech.data.entity

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.attm.maximatech.data.entity.DadosCliente
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity(
    tableName = "contato_cliente",
    indices = [Index("cliente_id")],
    foreignKeys = [
        ForeignKey(entity = DadosCliente::class, parentColumns = ["id"], childColumns = ["cliente_id"], onDelete = CASCADE)
    ]
)
@Parcelize
data class ContatoCliente (
    @field:[PrimaryKey(autoGenerate = true)] var id: Int,
    @field:[ColumnInfo(name = "cliente_id")] var clienteId: Int?,
    @field:[ColumnInfo]var nome: String?,
    @field:[ColumnInfo]var telefone: String?,
    @field:[ColumnInfo]var celular: String?,
    @field:[ColumnInfo]var conjugue: String?,
    @field:[ColumnInfo]var tipo: String?,
    @field:[ColumnInfo]var time: String?,
    @field:[ColumnInfo SerializedName("e_mail")]var e_mail: String?,
    @field:[ColumnInfo SerializedName("data_nascimento")]var dataNascimento: String?,
    @field:[ColumnInfo]var dataNascimentoConjugue: String?
): Parcelable