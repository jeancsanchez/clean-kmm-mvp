package br.com.onze.data.local

import br.com.onze.domain.models.Player

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 24/09/17.
 * Jesus loves you.
 */
interface LocalProvider {

    fun saveLoggedUser(player: Player)

    fun saveToken(token: String)
}