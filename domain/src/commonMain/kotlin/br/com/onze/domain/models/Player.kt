package br.com.onze.domain.models

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 02/11/16.
 * Jesus loves you.
 */
data class Player(
    var id: Int = -1,
    var name: String = "",
    var email: String? = "",
    var nickname: String = "",
    var goals: Int = 0,
    var faults: Int = 0,
    var redCards: Int = 0,
    var yellowCards: Int = 0,
    var isGoogle: Boolean = false,
    var stars: Double = 0.0,
    var teams: Int = 0,
    var rachas: Int = 0,
    var needChangePass: Boolean = false,
    var photo: String? = "",
    var locationId: Int = -1
) {
    fun decreaseGoals() {
        if (goals > 0) {
            goals--
        }
    }

    fun increaseGoals() {
        this.goals++
    }

    fun decreaseFaults() {
        if (faults > 0) {
            faults--
        }
    }

    fun increaseFaults() {
        this.faults++
    }

    fun decreaseRedCards() {
        if (redCards > 0) {
            redCards--
        }
    }

    fun increaseRedCards() {
        this.redCards++
    }

    fun decreaseYellowCards() {
        if (yellowCards > 0) {
            yellowCards--
        }
    }

    fun increaseYellowCards() {
        this.yellowCards++
    }

    override fun toString(): String = "$name (@$nickname)"
}
