package com.attm.maximatech.data.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.attm.maximatech.data.entity.Pedidos
@Dao
interface PedidosDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pedido: Pedidos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSync(pedido: Pedidos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pedidos: List<Pedidos>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSync(pedidos: List<Pedidos>)

    @Query("SELECT * FROM pedidos")
    fun getAll(): LiveData<List<Pedidos>>
}