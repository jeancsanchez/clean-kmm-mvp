package br.com.onze.data.rest

import br.com.onze.data.errors.Conflict
import br.com.onze.data.errors.Unauthorized
import br.com.onze.data.rest.pojos.AuthResponse
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 24/09/17.
 * Jesus loves you.
 */

class RestProviderImpl(engine: HttpClientEngineFactory<HttpClientEngineConfig>) : RestProvider {

    // TODO: Fazer isso de forma dinâmica
    private val baseUrl: String = "https://reqres.in/api/login"

    private val json = Json {
        ignoreUnknownKeys = true
        useArrayPolymorphism = true
    }

    private val client = HttpClient(engine) {
        configureJson()
        configureLogging()
        configureDefaultRequest()
        configureExceptionMapping()
    }

    private fun HttpClientConfig<*>.configureJson() {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
    }

    private fun HttpClientConfig<*>.configureLogging() {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    private fun HttpClientConfig<*>.configureDefaultRequest() {
        defaultRequest {
            url.takeFrom(
                URLBuilder().takeFrom(baseUrl).apply {
                    encodedPath += url.encodedPath
                })
        }
    }

    private fun HttpClientConfig<*>.configureExceptionMapping() {
        HttpResponseValidator {
            validateResponse {
                if (!it.status.isSuccess()) {
                    when (it.status) {
                        HttpStatusCode.Unauthorized -> throw Unauthorized
                        HttpStatusCode.Forbidden -> throw br.com.onze.data.errors.Forbidden
                        HttpStatusCode.BadRequest -> throw br.com.onze.data.errors.BadRequest
                        HttpStatusCode.NotFound -> throw br.com.onze.data.errors.NotFound
                        HttpStatusCode.Conflict -> throw Conflict
                        else -> throw br.com.onze.data.errors.InternalServerError
                    }
                }
            }
        }
    }

    @Serializable
    data class LoginWithNickRequest(val nickname: String, val password: String)

    override suspend fun loginWithNickname(nickname: String, password: String): AuthResponse {
        return client.post {
            url { path("auth/login") }
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            body = LoginWithNickRequest(nickname, password)
        }
    }


    @Serializable
    data class LoginWithEmailRequest(val email: String, val password: String)

    override suspend fun loginWithEmail(email: String, password: String): AuthResponse {
        return client.post {
            url { path("auth/login") }
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            body = LoginWithEmailRequest(email, password)
        }
    }
}
