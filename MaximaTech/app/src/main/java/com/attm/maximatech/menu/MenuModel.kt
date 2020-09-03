package com.attm.maximatech.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.attm.maximatech.R

data class MenuOptionModel (
    @DrawableRes val optionImage: Int,
    @StringRes val optionTitle: Int
)

object MenuOptions {
    val CLIENTE = MenuOptionModel(R.drawable.ic_maxima_pessoas, R.string.menu_opt_cliente)
    val PEDIDOS = MenuOptionModel(R.drawable.ic_maxima_pedido, R.string.menu_opt_pedidos)
    val VENDAS = MenuOptionModel(R.drawable.ic_maxima_resumo_vendas, R.string.menu_opt_resumo_vendas)
    val FERRAMENTAS = MenuOptionModel(R.drawable.ic_maxima_ferramentas, R.string.menu_opt_ferramentas)

    fun getValues(): List<MenuOptionModel> = listOf(CLIENTE, PEDIDOS, VENDAS, FERRAMENTAS)
}
