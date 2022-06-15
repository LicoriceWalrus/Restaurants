package com.example.restaurants

import android.app.Application
import com.example.restaurants.features.restaurants.restaurantsModule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            koin.loadModules(
                listOf(
                    network, restaurantsModule
                )
            )
        }
    }

    private val network = module {
        single<Gson> {
            GsonBuilder()
                .setLenient()
                .create()
        }
        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
        }
    }

    companion object {
        private const val BASE_URL = "https://front-task.chibbistest.ru/api/v1/"
    }
}