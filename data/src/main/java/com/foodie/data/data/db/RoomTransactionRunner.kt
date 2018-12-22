package com.foodie.data.data.db

import java.util.concurrent.Callable

class RoomTransactionRunner(private val db: FoodieDb) : DatabaseTransactionRunner {
    override operator fun <T> invoke(run: () -> T): T = db.runInTransaction(Callable<T> { run() })
}
