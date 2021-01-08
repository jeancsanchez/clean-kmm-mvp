package br.com.onze.features.account

import android.os.Bundle
import android.widget.Toast
import br.com.jeancsanchez.onze.R
import br.com.jeancsanchez.onze.databinding.ActivityLoginBinding
import br.com.onze.common.BaseActivity
import br.com.onze.domain.models.Player
import br.com.onze.presentation.presenters.LoginContract
import br.com.onze.shared.Injector
import com.google.android.material.snackbar.Snackbar

class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {

    lateinit var binding: ActivityLoginBinding

    override fun inject() {
        presenter = Injector.providesLoginPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarLogin)
        title = ""

        binding.btnLogin.setOnClickListener {
            presenter.onLoginName(binding.edtLogin.text.toString())
            presenter.onPassword(binding.edtLoginPassword.text.toString())
            presenter.onLogin(
                binding.edtLogin.text.toString(),
                binding.edtLoginPassword.text.toString()
            )
        }
    }

    override fun showLoginNameValid(email: String) {

    }

    override fun showLoginNameInvalid() {
        binding.edtLogin.error = getString(R.string.general_required_error)
    }

    override fun showInvalidPassword() {
        binding.edtLoginPassword.error = "Digite sua senha"
    }

    override fun showInvalidCredentials() {
        Toast.makeText(this, "Credenciais inválidas.", Toast.LENGTH_LONG).show()
    }


    override fun showLoginSuccess(user: Player) {
        hideKeyboard()
        Toast.makeText(this, "Login realizado com sucesso :)", Toast.LENGTH_LONG).show()
    }


    override fun showEmailIsRequired() {
        Snackbar.make(binding.rootLogin, "Digite seu email", Snackbar.LENGTH_LONG).show()
    }

    override fun showInvalidEmail() {
        hideKeyboard()
        Snackbar.make(binding.rootLogin, "O email informado é invalido", Snackbar.LENGTH_INDEFINITE)
            .apply {
                setAction("Ok") {
                    this.dismiss()
                }
            }
            .show()
    }

    override fun showNeedLoginWithEmail() {
        hideKeyboard()
        Snackbar.make(
            binding.rootLogin,
            "Você solicitou uma nova senha, por favor, entre com seu email.",
            Snackbar.LENGTH_INDEFINITE
        )
            .apply {
                setAction("Ok") {
                    this.dismiss()
                }
            }
            .show()
    }

    override fun showConflictAccount() {
        hideKeyboard()
        Snackbar.make(
            binding.rootLogin,
            "Este email está vinculado a uma conta Google. Entre com o Google.",
            Snackbar.LENGTH_INDEFINITE
        )
            .apply {
                setAction("Ok") {
                    this.dismiss()
                }
            }
            .show()
    }

    override fun showLoading() {
        binding.btnLogin.startAnimation()
    }


    override fun hideLoading() {
        binding.btnLogin.postDelayed({
            binding.btnLogin.revertAnimation()
        }, 1000)
    }
}