package com.foodie.data.data.db

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.db.daos.EntityDao
import com.foodie.data.entities.BaseEntity
import org.kodein.di.generic.instance

class EntityInserter {
    private val transactionRunner: DatabaseTransactionRunner by kodeinInstance.instance()

    fun <E : BaseEntity> insertOrUpdate(dao: EntityDao<E>, entities: List<E>) =
        transactionRunner {
            entities.forEach {
                insertOrUpdate(dao, it)
            }
        }

    fun <E : BaseEntity> insertOrUpdate(dao: EntityDao<E>, entity: E): Long = when {
        entity.id == 0L -> {
            dao.insert(entity)
            entity.id
        }
        else -> {
            dao.update(entity)
            entity.id
        }
    }
}
