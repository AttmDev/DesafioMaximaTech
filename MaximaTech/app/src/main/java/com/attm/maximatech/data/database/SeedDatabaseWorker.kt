package com.attm.maximatech.data.database

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.attm.maximatech.data.AppDatabase
import com.attm.maximatech.data.entity.DadosCliente
import com.attm.maximatech.data.entity.Pedidos
import com.attm.maximatech.utils.Constants.CLIENTES_JSON_FILENAME
import com.attm.maximatech.utils.Constants.PEDIDOS_JSON_FILENAME
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker (context, workerParameters){
    override suspend fun doWork(): Result = coroutineScope{
        try {
            applicationContext.assets.open(CLIENTES_JSON_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val cliente: DadosCliente = Gson().fromJson(jsonReader, DadosCliente::class.java)
                    val database = AppDatabase.getInstance(applicationContext)
                    database.dadosClienteDao().insert(cliente)
                    cliente.contatos?.forEach {
                        it.clienteId = cliente.id
                        database.contactDao().insert(it)
                    }
                }
                applicationContext.assets.open(PEDIDOS_JSON_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use {
                        val pedidos: PedidosList = Gson().fromJson(it, PedidosList::class.java)
                        val database = AppDatabase.getInstance(applicationContext)
                        database.pedidosDao().insert(pedidos.p)
                    }
                }
                Result.success()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }

}

class PedidosList {
    var p: MutableList<Pedidos> = emptyList<Pedidos>().toMutableList()
}