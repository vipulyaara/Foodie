package com.foodie.data.data.db

import androidx.room.TypeConverter
import com.foodie.data.model.detail.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Vipul Kumar; dated 24/12/18.
 */
class FoodieTypeConverters {

    @TypeConverter
    fun stringToCategoryList(data: String): List<Category>? {
        val listType = object : TypeToken<ArrayList<Category>>() {
        }.type
        return Gson().fromJson<List<Category>>(data, listType)
    }

    @TypeConverter
    fun categoryListToString(stringList: List<Category?>?): String {
        return Gson().toJson(stringList)
    }
}
