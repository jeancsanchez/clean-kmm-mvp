package br.com.onze.presentation.presenters

import br.com.onze.domain.models.Player
import br.com.onze.presentation.BasePresenter
import br.com.onze.presentation.BaseView

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 30/09/17.
 * Jesus loves you.
 */

interface LoginContract {

    interface View : BaseView {

        fun showLoginNameInvalid()

        fun showLoginNameValid(email: String)

        fun showInvalidCredentials()

        fun showConflictAccount()

        fun showLoginSuccess(user: Player)

        fun showInvalidPassword()

        fun showInvalidEmail()

        fun showEmailIsRequired()

        fun showNeedLoginWithEmail()
    }

    abstract class Presenter : BasePresenter<View>() {

        abstract fun onLoginName(login: String?)

        abstract fun onPassword(password: String?)

        abstract fun onLogin(login: String, password: String)
    }
}