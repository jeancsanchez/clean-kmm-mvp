package br.com.onze.presentation.presenters

import br.com.onze.data.errors.Conflict
import br.com.onze.data.errors.Unauthorized
import br.com.onze.domain.usecases.FazerLoginEmailUc
import br.com.onze.domain.usecases.FazerLoginNicknameUc
import br.com.onze.presentation.extensions.isEmail
import kotlinx.coroutines.launch

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 30/09/17.
 * Jesus loves you.
 */
class LoginPresenter(
    private val fazerLoginNicknameUc: FazerLoginNicknameUc,
    private val fazerLoginUc: FazerLoginEmailUc
) : LoginContract.Presenter() {

    override fun onLoginName(login: String?) {
        login?.let {
            if (it.isBlank()) {
                view?.showLoginNameInvalid()

            } else {
                view?.showLoginNameValid(login)
            }
        } ?: view?.showLoginNameInvalid()
    }

    override fun onPassword(password: String?) {
        password?.let {
            if (it.isBlank()) {
                view?.showInvalidPassword()
            }
        } ?: view?.showInvalidPassword()
    }

    override fun onLogin(login: String, password: String) {
        if ((login.isNotEmpty()) && password.isNotEmpty()) {
            view?.showLoading()

            launch {
                try {
                    val user = if (login.isEmail()) {
                        fazerLoginUc.execute(FazerLoginEmailUc.Params(login, password))
                    } else {
                        fazerLoginNicknameUc.execute(FazerLoginNicknameUc.Params(login, password))
                    }

                    view?.hideLoading()
                    view?.showLoginSuccess(user)
                } catch (throwable: Throwable) {
                    view?.hideLoading()

                    when (throwable) {
                        is Unauthorized -> {
                            view?.showInvalidCredentials()
                        }
                        is Conflict -> {
                            view?.showConflictAccount()
                        }
                        else -> {
                            view?.showError(throwable)
                        }
                    }
                }
            }
        } else {
            view?.showInvalidCredentials()
        }
    }
}