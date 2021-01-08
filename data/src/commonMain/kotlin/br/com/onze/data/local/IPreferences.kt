package br.com.onze.data.local

import br.com.onze.domain.models.Player

interface IPreferences {

    fun saveLoggedUser(player: Player)

    fun getLoggedUser(): Player

    fun saveToken(token: String)

    fun getToken(): String

    fun clearAll()
}