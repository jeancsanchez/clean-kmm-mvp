package br.com.onze.domain

import br.com.onze.domain.models.Player


/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 23/09/17.
 * Jesus loves you.
 */
interface PlayerRepository {

    suspend fun loginWithEmail(email: String, password: String): Player

    suspend fun loginWithNickname(nickname: String, password: String): Player
}