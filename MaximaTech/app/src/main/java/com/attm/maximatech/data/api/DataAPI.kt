package com.attm.maximatech.data.api

import com.attm.maximatech.data.repository.ClienteResponse
import com.attm.maximatech.data.repository.PedidosResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ClienteService {
    @GET("cliente")
    fun pullCliente(): Call<ClienteResponse>
}

interface PedidoService {
    @GET("pedido")
    fun pullPedidos(): Call<PedidosResponse>
}

object DataAPI {
    private const val BASE_URL = "https://private-anon-d5c119f727-maximatech.apiary-mock.com/android/"
    private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    fun getClienteService(): ClienteService {
        return retrofit.create(ClienteService::class.java)
    }

    fun getPedidosService(): PedidoService {
        return retrofit.create(PedidoService::class.java)
    }
}