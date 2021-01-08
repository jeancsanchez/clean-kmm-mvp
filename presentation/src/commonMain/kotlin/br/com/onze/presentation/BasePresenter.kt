package br.com.onze.presentation

import kotlinx.coroutines. CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by @jeancsanchez
 * @date 09/09/17.
 * Jesus loves you.
 */

abstract class BasePresenter<VIEW : BaseView> : CoroutineScope {

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Main

    protected var view: VIEW? = null


    @Suppress("UNCHECKED_CAST")
    open fun attachView(view: BaseView?) {
        this.view = view as? VIEW
    }

    open fun onCreate() {}

    open fun onStart() {}

    open fun onResume() {}

    open fun onPause() {}

    open fun onStop() {}

    open fun onDestroy() {
        job.cancel()
    }
}
