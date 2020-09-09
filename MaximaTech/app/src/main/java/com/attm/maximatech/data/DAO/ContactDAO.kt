package com.attm.maximatech.data.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.attm.maximatech.data.entity.ContatoCliente
@Dao
interface ContactDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contato: ContatoCliente)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contatos: List<ContatoCliente>)

    @Query("SELECT * FROM contato_cliente WHERE cliente_id = :id")
    fun getAllByClienteId(id: String): List<ContatoCliente>
}