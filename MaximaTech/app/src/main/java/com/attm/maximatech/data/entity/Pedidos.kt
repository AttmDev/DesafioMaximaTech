package com.attm.maximatech.data.entity

import android.graphics.Color
import android.icu.text.DateFormat.getTimeInstance
import android.text.format.DateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.attm.maximatech.data.Converters
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class Pedidos (
    @field:[PrimaryKey SerializedName("numero_ped_Rca")] var numeroPedidoRca: Long,
    @field:[ColumnInfo SerializedName("numero_ped_erp")] var numeroPedidoErp: String?,
    @field:[ColumnInfo]var codigoCliente: String?,
    @field:[ColumnInfo SerializedName("NOMECLIENTE")]var nomeCliente: String?,
    @field:[ColumnInfo]var data: String?,
    @field:[ColumnInfo]var status: String?,
    @field:[ColumnInfo]var critica: String?,
    @field:[ColumnInfo]var tipo: String?,
    @field:[ColumnInfo]@TypeConverters(Converters::class) var legendas: List<String>? = emptyList()
) {
    fun getFormattedTime(): String{
        val dataP = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssSSSS", Locale.getDefault()).parse(data.orEmpty())
        if (dataP != null) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_WEEK, -1)
            if (dataP.before(calendar.time)) {
                return DateFormat.format("HH:mm", dataP).toString()
            } else {
                return DateFormat.format("dd MMM", dataP).toString()
            }
        } else {
            return ""
        }


    }
}