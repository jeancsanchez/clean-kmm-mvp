package br.com.onze.data

import br.com.onze.data.local.LocalProvider
import br.com.onze.data.rest.RestProvider
import br.com.onze.data.rest.pojos.AuthResponse
import br.com.onze.domain.PlayerRepository
import br.com.onze.domain.models.Player

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 23/09/17.
 * Jesus loves you.
 */
class PlayerRepositoryImpl(
    private val localProvider: LocalProvider,
    private val restProvider: RestProvider
) : PlayerRepository {

    override suspend fun loginWithEmail(email: String, password: String): Player {
        val auth = restProvider.loginWithEmail(email, password)
        return saveAuthAndReturnUser(auth)
    }

    override suspend fun loginWithNickname(nickname: String, password: String): Player {
        val auth = restProvider.loginWithNickname(nickname, password)
        return saveAuthAndReturnUser(auth)
    }

    private fun saveAuthAndReturnUser(auth: AuthResponse): Player {
        val user = Player(id = auth.id.toInt(), nickname =  auth.nickname)
        val token = auth.createdAt + user.nickname
        localProvider.saveToken(token)
        localProvider.saveLoggedUser(user)
        return user
    }
}