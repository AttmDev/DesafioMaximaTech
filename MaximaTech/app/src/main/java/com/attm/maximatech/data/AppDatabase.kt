package com.attm.maximatech.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.attm.maximatech.data.DAO.ContactDAO
import com.attm.maximatech.data.DAO.DadosClienteDAO
import com.attm.maximatech.data.DAO.PedidosDAO
import com.attm.maximatech.data.entity.ContatoCliente
import com.attm.maximatech.data.entity.DadosCliente
import com.attm.maximatech.data.entity.Pedidos
import com.attm.maximatech.utils.Constants.DATABASE_NAME

@Database(entities = [ContatoCliente::class, DadosCliente::class, Pedidos::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dadosClienteDao(): DadosClienteDAO
    abstract fun pedidosDao(): PedidosDAO
    abstract fun contactDao(): ContactDAO

    companion object{
        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }

    }

}