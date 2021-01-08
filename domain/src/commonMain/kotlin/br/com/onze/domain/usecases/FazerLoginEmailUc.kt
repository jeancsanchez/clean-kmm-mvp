package br.com.onze.domain.usecases

import br.com.onze.domain.PlayerRepository
import br.com.onze.domain.models.Player

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 19/05/17.
 * Jesus loves you.
 */
class FazerLoginEmailUc(private val playerRepository: PlayerRepository) :
    UseCase<FazerLoginEmailUc.Params, Player>() {

    override suspend fun buildUseCase(params: Params): Player {
        return playerRepository.loginWithEmail(params.email, params.password)
    }

    class Params(
        val email: String,
        val password: String
    )
}