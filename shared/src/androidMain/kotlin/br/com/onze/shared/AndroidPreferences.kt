package br.com.onze.shared

import br.com.onze.data.errors.Unauthorized
import br.com.onze.data.local.IPreferences
import br.com.onze.domain.models.Player
import com.orhanobut.hawk.Hawk

class AndroidPreferences : IPreferences {
    companion object {
        private const val USER_KEY: String = "USER_KEY"
        private const val USER_TOKEN_KEY: String = "USER_TOKEN_KEY"
    }

    override fun saveLoggedUser(player: Player) {
        Hawk.put(USER_KEY, player)
    }

    override fun getLoggedUser(): Player {
        val user: Player? = Hawk.get(USER_KEY, null)
        user?.let { return it } ?: throw Unauthorized
    }

    override fun saveToken(token: String) {
        Hawk.put(USER_TOKEN_KEY, token)
    }

    override fun getToken(): String {
        val token: String? = Hawk.get(USER_TOKEN_KEY, null)
        token?.let { return it } ?: throw Unauthorized
    }

    override fun clearAll() {
        Hawk.delete(USER_TOKEN_KEY)
        Hawk.delete(USER_KEY)
    }
}