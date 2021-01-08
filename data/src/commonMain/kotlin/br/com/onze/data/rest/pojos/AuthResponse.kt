package br.com.onze.data.rest.pojos

import kotlinx.serialization.Serializable

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 22/02/19.
 * Jesus loves you.
 */
@Serializable
data class AuthResponse(
     val id: String,
     val nickname: String,
     val password: String,
     val createdAt: String,
)