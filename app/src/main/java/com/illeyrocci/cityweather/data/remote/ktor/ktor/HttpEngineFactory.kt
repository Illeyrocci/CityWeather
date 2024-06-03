package com.illeyrocci.cityweather.data.remote.ktor.ktor

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android

fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Android