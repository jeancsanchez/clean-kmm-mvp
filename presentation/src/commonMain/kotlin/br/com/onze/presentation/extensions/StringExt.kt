package br.com.onze.presentation.extensions


/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 30/09/17.
 * Jesus loves you.
 */

fun String.isEmail(): Boolean {
    val email = try {
        extractDomain(this)
    } catch (e: Exception) {
        ""
    }

    if (email.isNullOrEmpty()) return false
    return true
}

private fun extractDomain(email: String): String? {
    val emailCharIndex = email.indexOf("@")
    if (emailCharIndex == -1 || email.isEmpty()) return null
    return email.substring(emailCharIndex + 1, email.indexOf(".com")).toUpperCase()
}
