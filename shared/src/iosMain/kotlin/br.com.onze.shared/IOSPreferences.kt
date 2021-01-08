package br.com.onze.shared

import br.com.onze.data.local.IPreferences
import br.com.onze.domain.models.Player

class IOSPreferences : IPreferences {

    override fun saveLoggedUser(player: Player) {
        // TODO: IOS SOLUTION
    }

    override fun getLoggedUser(): Player {
        // TODO: IOS SOLUTION
        return Player()
    }

    override fun saveToken(token: String) {
        // TODO: IOS SOLUTION
    }

    override fun getToken(): String {
        // TODO: IOS SOLUTION
        return ""
    }

    override fun clearAll() {
        // TODO: IOS SOLUTION
        // TODO: IOS SOLUTION
    }
}