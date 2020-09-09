package com.attm.maximatech.data.api

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.attm.maximatech.data.AppDatabase
import com.attm.maximatech.data.entity.DadosCliente
import com.attm.maximatech.data.entity.Pedidos
import com.attm.maximatech.data.repository.ClienteResponse
import com.attm.maximatech.data.repository.PedidosResponse
import com.attm.maximatech.data.repository.Repository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchApiPedidosWorker (
    context: Context,
    workerParameters: WorkerParameters, appContext: Context, params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    private var pedidosService: PedidoService = DataAPI.getPedidosService()
    override suspend fun doWork(): Result = coroutineScope{
        fetchPedidosData()
        Result.success()
    }


    suspend fun fetchPedidosData() {
        Log.i("worker", "enqueue")
        pedidosService.pullPedidos().enqueue(object : Callback<PedidosResponse> {
            override fun onResponse(
                call: Call<PedidosResponse>,
                response: Response<PedidosResponse>
            ) {
                Log.i("worker", response.message())
                if (response.code() == 200) {
                    val database = AppDatabase.getInstance(applicationContext)
                    response.body()?.let {
                        it.pedidos.forEach {
                            val pedido: Pedidos = Pedidos(
                                numeroPedidoRca = it.numero_ped_Rca.toLong(),
                                numeroPedidoErp = it.numero_ped_erp,
                                codigoCliente = it.codigoCliente.toString(),
                                nomeCliente = it.NOMECLIENTE.toString(),
                                status = it.status.toString(),
                                critica = it.critica.toString(),
                                tipo = it.tipo.toString(),
                                legendas = it.legendas,
                                data = it.data)
                            runBlocking {
                                database.pedidosDao().insert(pedido)
                            }

                        }
                    }
                }
            }

            override fun onFailure(call: Call<PedidosResponse>, t: Throwable) {
                Log.i("worker", t.message?:"null")
            }

        })
    }
}