package br.com.onze.models.mappers

abstract class Mapper<PRESENTATION, DOMAIN> {
    abstract fun PRESENTATION.mapToDomain(): DOMAIN
    abstract fun List<PRESENTATION>.mapToDomain(): List<DOMAIN>
    abstract fun ArrayList<PRESENTATION>.mapToDomain(): ArrayList<DOMAIN>

    abstract fun DOMAIN.mapToPresentation(): PRESENTATION
    abstract fun List<DOMAIN>.mapToPresentation(): List<PRESENTATION>
    abstract fun ArrayList<DOMAIN>.mapToPresentation(): ArrayList<PRESENTATION>
}