package br.com.onze

import android.app.Application
import android.util.Log
import br.com.jeancsanchez.onze.android.BuildConfig
import com.orhanobut.hawk.Hawk
import timber.log.Timber

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 23/09/17.
 * Jesus loves you.
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()

        if (BuildConfig.DEBUG || BuildConfig.BUILD_TYPE.contains("mock")) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    private class ReleaseTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
            if (priority == Log.ERROR || priority == Log.WARN) {
                throwable?.let {
//                    FirebaseCrashlytics.getInstance().recordException(it)
                }
            }
        }
    }
}