package br.com.onze.models.mappers

import br.com.onze.domain.models.Player
import br.com.onze.models.PPlayer


object PlayerMapper : Mapper<PPlayer, Player>() {

    override fun PPlayer.mapToDomain(): Player =
        Player(
            id = this.id,
            name = this.name,
            email = this.email,
            nickname = this.nickname,
            goals = this.goals,
            faults = this.faults,
            redCards = this.redCards,
            yellowCards = this.yellowCards,
            isGoogle = this.isGoogle,
            stars = this.stars,
            teams = this.teams,
            rachas = this.rachas,
            needChangePass = this.needChangePass,
            photo = this.photo,
            locationId = this.locationId,
        )

    override fun List<PPlayer>.mapToDomain(): List<Player> = this.map { it.mapToDomain() }
    override fun ArrayList<PPlayer>.mapToDomain(): ArrayList<Player> =
        this.map { it.mapToDomain() } as ArrayList


    override fun Player.mapToPresentation(): PPlayer =
        PPlayer(
            id = this.id,
            name = this.name,
            email = this.email,
            nickname = this.nickname,
            goals = this.goals,
            faults = this.faults,
            redCards = this.redCards,
            yellowCards = this.yellowCards,
            isGoogle = this.isGoogle,
            stars = this.stars,
            teams = this.teams,
            rachas = this.rachas,
            needChangePass = this.needChangePass,
            photo = this.photo,
            locationId = this.locationId,
        )

    override fun List<Player>.mapToPresentation(): List<PPlayer> =
        this.map { it.mapToPresentation() }

    override fun ArrayList<Player>.mapToPresentation(): ArrayList<PPlayer> =
        this.map { it.mapToPresentation() } as ArrayList<PPlayer>
}