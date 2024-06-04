package com.illeyrocci.cityweather.di

import com.illeyrocci.cityweather.data.remote.RemoteCityDataSourceImpl
import com.illeyrocci.cityweather.data.remote.ktor.RemoteCityDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {

            install(Logging) {
                level = LogLevel.INFO
                logger = Logger.SIMPLE
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            isLenient = false
            ignoreUnknownKeys = true
        }
    }

    @Provides
    @Singleton
    fun provideRemoteCityDataSource(client: HttpClient, json: Json): RemoteCityDataSource {
        return RemoteCityDataSourceImpl(client, json)
    }
}