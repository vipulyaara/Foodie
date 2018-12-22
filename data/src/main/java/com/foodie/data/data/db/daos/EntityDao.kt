package com.foodie.data.data.db.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.foodie.data.entities.BaseEntity

interface EntityDao<in E : BaseEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: E): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg entity: E)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<E>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(entity: E)

    @Delete
    fun delete(entity: E): Int
}
