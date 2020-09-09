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

class FetchApiWorker (
    context: Context,
    workerParameters: WorkerParameters, appContext: Context, params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    private var clienteService: ClienteService = DataAPI.getClienteService()
    private var pedidosService: PedidoService = DataAPI.getPedidosService()
    override suspend fun doWork(): Result = coroutineScope{
        fetchData()
        Result.success()
    }

    suspend fun fetchData() {
        clienteService.pullCliente().enqueue(object : Callback<ClienteResponse> {
            override fun onResponse(call: Call<ClienteResponse>, response: Response<ClienteResponse>) {
                Log.i("repository", response.message())
                if (response.code() == 200) {
                    response.body()?.let {c ->
                        val cliente = DadosCliente(
                            id = c.cliente.id.toInt(),
                            codigo = c.cliente.codigo,
                            razaoSocial = c.cliente.razao_social.toString(),
                            nomeFantasia = c.cliente.nomeFantasia.toString(),
                            cnpj = c.cliente.cnpj.toString(),
                            ramoAtividade = c.cliente.ramo_atividade.toString(),
                            endereco = c.cliente.endereco.toString(),
                            status = c.cliente.status.toString(),
                            contatos = c.cliente.contatos.orEmpty() )
                        CoroutineScope(Job() + Dispatchers.IO).launch {
                            Log.i("repository", "Inserting: $cliente")
                            var database = AppDatabase.getInstance(applicationContext)
                            database.dadosClienteDao().insert(cliente)
                            c.cliente.contatos.forEach {
                                it.clienteId = cliente.id
                                database.contactDao().insert(it)
                            }
                        }

                    }
                }
            }

            override fun onFailure(call: Call<ClienteResponse>, t: Throwable) {

            }

        })
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
                        it.pedidos.map {
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