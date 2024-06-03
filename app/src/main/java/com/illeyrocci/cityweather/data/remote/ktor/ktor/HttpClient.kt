package com.illeyrocci.cityweather.data.remote.ktor.ktor

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.host
import io.ktor.http.URLProtocol

fun getHttpClient() = HttpClient(createEngine()) {

    install(Logging) {
        level = LogLevel.INFO
        logger = Logger.SIMPLE
    }

    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }

    defaultRequest {
        host = "gist.githubusercontent.com"
        url {
            protocol = URLProtocol.HTTPS
        }
    }
}