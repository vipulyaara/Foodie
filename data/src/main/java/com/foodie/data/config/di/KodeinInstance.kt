package com.foodie.data.config.di

import org.kodein.di.Kodein

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Stores an app-wide instance of Kodein.
 * Unlike Dagger, Kodein is a service locator and needs an instance of Kodein handy for injections.
 */
lateinit var kodeinInstance: Kodein
