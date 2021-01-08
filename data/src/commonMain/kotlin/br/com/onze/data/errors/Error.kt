package br.com.onze.data.errors

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 04/04/19.
 * Jesus is alive!
 */
open class Error(code: Int, msg: String) : Throwable("$code: $msg")