package br.com.onze.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PPlayer(
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
) : Parcelable