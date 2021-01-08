package br.com.onze.data.local

import br.com.onze.domain.models.Player

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 30/09/17.
 * Jesus loves you.
 */

class LocalProviderImpl(private val IPreferences: IPreferences) : LocalProvider {

    override fun saveLoggedUser(player: Player) {
        IPreferences.saveLoggedUser(player)
    }

    override fun saveToken(token: String) {
        IPreferences.saveToken(token)
    }
}