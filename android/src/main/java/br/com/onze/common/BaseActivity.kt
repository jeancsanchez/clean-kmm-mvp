package br.com.onze.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.jeancsanchez.onze.R
import br.com.onze.data.errors.Unauthorized
import br.com.onze.features.splash.SplashActivity
import br.com.onze.presentation.BasePresenter
import com.afollestad.materialdialogs.MaterialDialog
import timber.log.Timber
import java.net.ConnectException


/**
 * Created by @jeancsanchez
 * @date 09/09/17.
 * Jesus loves you.
 */
@SuppressLint("Registered")
abstract class BaseActivity<PRESENTER : BasePresenter<*>> :
    AppCompatActivity(),
    br.com.onze.presentation.BaseView {

    internal lateinit var presenter: PRESENTER

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag(localClassName)

        inject()
        presenter.onCreate()
        presenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }


    override fun onSupportNavigateUp(): Boolean {
        //This method is called when the up button is pressed. Just the pop back stack.
        supportFragmentManager.popBackStack()
        return true
    }

    protected val colorPrimary: Int
        get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                return applicationContext.getColor(R.color.colorPrimary)

            return ContextCompat.getColor(applicationContext, R.color.colorPrimary)
        }


    val colorAccent: Int
        get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                return applicationContext.getColor(R.color.colorAccent)

            return ContextCompat.getColor(applicationContext, R.color.colorAccent)
        }


    override fun showError(exception: Throwable) {
        Timber.e(exception)

        if (exception is ConnectException) {
            if (isFinishing.not()) {
                MaterialDialog(this).show {
                    title(text = "Parece que você está sem conexão com nossos servidores :/ ")
                    positiveButton(text = "Ok", click = { dialog -> dialog.dismiss() })
                }
            }
        } else if (exception is Unauthorized || exception is br.com.onze.data.errors.Forbidden) {
            Toast.makeText(this, "Por favor, faça login novamente", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, SplashActivity::class.java))
            finishAffinity()
        } else {
            Timber.log(Log.ERROR, exception)

            if (isFinishing.not()) {
                MaterialDialog(this).show {
                    title(text = "Algo inesperado aconteceu :S")
                    positiveButton(text = "Ok", click = { dialog -> dialog.dismiss() })
                }
            }
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }


    fun showAlert(title: String, msg: String, context: Context): AlertDialog.Builder {
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(msg)
    }


    protected fun hidesStatusBar() {
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            clearAnimation()
        }
    }

    fun launchActivity(from: Context, destiny: Class<*>) {
        val intent = Intent(from, destiny)
        from.startActivity(intent)
    }

    fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
