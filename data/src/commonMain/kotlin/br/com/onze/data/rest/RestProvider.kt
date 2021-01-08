package br.com.onze.data.rest

import br.com.onze.data.rest.pojos.AuthResponse

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 24/09/17.
 * Jesus loves you.
 */
interface RestProvider {

    suspend fun loginWithNickname(nickname: String, password: String): AuthResponse

    suspend fun loginWithEmail(email: String, password: String): AuthResponse
}
