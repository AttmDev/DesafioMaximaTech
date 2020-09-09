package com.attm.maximatech.data

import androidx.room.TypeConverter
import com.google.gson.Gson

object Converters {

    val gson: Gson = Gson()

    @TypeConverter
    @JvmStatic
    public fun listStringToJson(list: List<String>?): String {
        if (list.isNullOrEmpty())return "" else return gson.toJson(list)
    }
    @TypeConverter
    @JvmStatic
    fun jsonTolistString(json: String): List<String> {
        if (json.isNullOrBlank()) {
            return emptyList()
        }
        return gson.fromJson<List<String>>(json, List::class.java)
    }
}