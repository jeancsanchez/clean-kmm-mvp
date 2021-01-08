@file:Suppress("SpellCheckingInspection")

package br.com.onze.shared

import br.com.onze.data.PlayerRepositoryImpl
import br.com.onze.data.local.LocalProvider
import br.com.onze.data.local.LocalProviderImpl
import br.com.onze.data.rest.RestProvider
import br.com.onze.data.rest.RestProviderImpl
import br.com.onze.domain.PlayerRepository
import br.com.onze.domain.usecases.FazerLoginEmailUc
import br.com.onze.domain.usecases.FazerLoginNicknameUc
import br.com.onze.presentation.presenters.LoginContract
import br.com.onze.presentation.presenters.LoginPresenter
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object Injector {

    // DATA
    private val localProvider: LocalProvider by lazy { LocalProviderImpl(preferences) }
    private val restProvider: RestProvider by lazy { RestProviderImpl(engine) }
    private val playerRepository: PlayerRepository by lazy {
        PlayerRepositoryImpl(
            localProvider,
            restProvider
        )
    }


    // DOMAIN
    private val fazerLoginNicknameUc by lazy { FazerLoginNicknameUc(playerRepository) }
    private val fazerLoginUc by lazy { FazerLoginEmailUc(playerRepository) }


    // PRESENTATION
    fun providesLoginPresenter(): LoginContract.Presenter = LoginPresenter(
        fazerLoginNicknameUc,
        fazerLoginUc
    )
}