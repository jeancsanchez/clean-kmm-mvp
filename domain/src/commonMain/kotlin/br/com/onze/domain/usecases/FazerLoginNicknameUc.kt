package br.com.onze.domain.usecases

import br.com.onze.domain.PlayerRepository
import br.com.onze.domain.models.Player

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 19/05/17.
 * Jesus loves you.
 */
class FazerLoginNicknameUc(private val playerRepository: PlayerRepository) :
    UseCase<FazerLoginNicknameUc.Params, Player>() {

    override suspend fun buildUseCase(params: Params): Player {
        return playerRepository.loginWithNickname(params.nickname, params.password)
    }

    class Params(
        val nickname: String,
        val password: String
    )
}