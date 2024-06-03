package com.illeyrocci.cityweather.data.repository

import com.illeyrocci.cityweather.common.Resource
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException

abstract class BaseKtorRepository {
    suspend fun <T> doWebRequest(request: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(request.invoke())
        } catch (e: RedirectResponseException) {
            Resource.Error(
                "Информация не доступна. Попробуйте позже\n(${e.response.status.description})"
            )
        } catch (e: ClientRequestException) {
            Resource.Error(
                "Ошибка на стороне клиента. Проверьте подключение\n(${e.response.status.description})"
            )
        } catch (e: ServerResponseException) {
            Resource.Error(
                "Ошибка сервера\n(${e.response.status.description})"
            )
        } catch (e: Exception) {
            val message = e.localizedMessage.let {
                if (it != null && it.startsWith("Unable to resolve host", true)) {
                   ". Проверьте подключение\n($it)"
                } else "\n($it)"
            }
            Resource.Error("Произошла ошибка$message")
        }
    }
}