package br.com.onze.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 10/12/17.
 * Jesus loves you.
 */
abstract class UseCase<in PARAMS, RESULT> {

    abstract suspend fun buildUseCase(params: PARAMS): RESULT

    suspend fun execute(params: PARAMS): RESULT =
        withContext(Dispatchers.Default) { buildUseCase(params) }
}