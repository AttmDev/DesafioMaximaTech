package com.attm.maximatech.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.work.*
import com.attm.maximatech.data.AppDatabase
import com.attm.maximatech.data.DAO.ContactDAO
import com.attm.maximatech.data.DAO.DadosClienteDAO
import com.attm.maximatech.data.DAO.PedidosDAO
import com.attm.maximatech.data.api.*
import com.attm.maximatech.data.entity.DadosEContatosCliente
import com.attm.maximatech.data.entity.ContatoCliente
import com.attm.maximatech.data.entity.DadosCliente
import com.attm.maximatech.data.entity.Pedidos
import com.attm.maximatech.data.database.SeedDatabaseWorker
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit


object Repository
{
    private var clienteService: ClienteService = DataAPI.getClienteService()
    private var pedidosService: PedidoService = DataAPI.getPedidosService()

    private lateinit var dadosClienteDAO: DadosClienteDAO
    private lateinit var contactDAO: ContactDAO
    private lateinit var pedidosDAO: PedidosDAO
    private lateinit var appDatabase: AppDatabase

    fun initialize(context: Context) : Repository {
        appDatabase = AppDatabase.getInstance(context)
        pedidosDAO = appDatabase.pedidosDao()
        contactDAO = appDatabase.contactDao()
        pedidosDAO = appDatabase.pedidosDao()
        dadosClienteDAO = appDatabase.dadosClienteDao()

        //get json from local file
        val localRequest = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
        WorkManager.getInstance(context).enqueue(localRequest)

        fetchClienteData()
        fetchPedidosData()

        return this
    }

    fun getPedidosLiveData(): LiveData<List<Pedidos>> {
        return pedidosDAO.getAll()
    }

    fun getClientesLiveData(): LiveData<DadosEContatosCliente> {
        return dadosClienteDAO.getFirst()
    }

    fun fetchClienteData() {
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
                            dadosClienteDAO.insert(cliente)
                            c.cliente.contatos.forEach {
                                it.clienteId = cliente.id
                                contactDAO.insert(it)
                            }
                        }

                    }
                }
            }

            override fun onFailure(call: Call<ClienteResponse>, t: Throwable) {

            }

        })
    }

    fun fetchPedidosData() {
        Log.i("worker", "fetch_pedidos_data")
        pedidosService.pullPedidos().enqueue(object : Callback<PedidosResponse> {
            override fun onResponse(
                call: Call<PedidosResponse>,
                response: Response<PedidosResponse>
            ) {
                Log.i("worker", response.message())
                if (response.code() == 200) {
                    response.body()?.let {
                        it.pedidos.map {
                            val pedido: Pedidos = Pedidos(
                                numeroPedidoRca = it.numero_ped_Rca.toLong(),
                                numeroPedidoErp = it.numero_ped_erp,
                                codigoCliente = it.codigoCliente,
                                nomeCliente = it.NOMECLIENTE,
                                status = it.status,
                                critica = it.critica,
                                tipo = it.tipo,
                                legendas = it.legendas,
                                data = it.data)
                            CoroutineScope(Job() + Dispatchers.IO).launch {
                                Log.i("worker", pedido.toString())
                                pedidosDAO.insert(pedido)
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


data class ClienteResponse(
    val cliente: Cliente
)

data class Cliente(
    val cnpj: String,
    val codigo: String,
    val contatos: List<ContatoCliente>,
    val endereco: String,
    val id: Int,
    val nomeFantasia: String,
    val ramo_atividade: String,
    val razao_social: String,
    val status: String
)

data class Contato(
    val celular: String,
    val conjuge: String,
    val dataNascimentoConjuge: Any,
    val data_nascimento: String,
    val e_mail: String,
    val nome: String,
    val telefone: String,
    val time: String,
    val tipo: String
)

data class PedidosResponse(
    val pedidos: List<Pedido>
)

data class Pedido(
    val NOMECLIENTE: String,
    val codigoCliente: String,
    val critica: String,
    val `data`: String,
    val legendas: List<String>,
    val numero_ped_Rca: Int,
    val numero_ped_erp: String,
    val status: String,
    val tipo: String
)