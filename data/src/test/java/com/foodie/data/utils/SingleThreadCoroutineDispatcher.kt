package com.foodie.data.utils

import com.foodie.data.data.AppCoroutineDispatchers
import kotlinx.coroutines.Dispatchers.Main

// Everything on the UI
val testCoroutineDispatchers = AppCoroutineDispatchers(Main, Main, Main)
