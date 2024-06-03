package com.illeyrocci.cityweather

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android

fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Android