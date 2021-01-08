package br.com.onze.presentation

/**
 * Created by @jeancsanchez
 * @date 20/01/17.
 * Jesus loves you.
 */

interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun showError(exception: Throwable)
}
