package com.attm.maximatech.data.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.attm.maximatech.data.entity.DadosEContatosCliente
import com.attm.maximatech.data.entity.DadosCliente
@Dao
interface DadosClienteDAO {

    @Transaction
    @Query("SELECT * FROM dados_cliente")
    fun getAll(): LiveData<List<DadosEContatosCliente>>

    @Transaction
    @Query("SELECT * FROM dados_cliente WHERE dados_cliente.id == :id limit 1")
    fun getById(id: Int): DadosEContatosCliente?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: DadosCliente)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSync(cliente: DadosCliente)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(clientes: List<DadosCliente>)

    @Transaction
    @Query("SELECT * FROM dados_cliente WHERE dados_cliente.id != 0 limit 1")
    fun getFirst(): LiveData<DadosEContatosCliente>

}