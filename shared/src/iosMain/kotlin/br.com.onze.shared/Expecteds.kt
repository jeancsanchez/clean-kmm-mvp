package br.com.onze.shared

import br.com.onze.data.local.IPreferences
import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*

actual val engine: HttpClientEngineFactory<HttpClientEngineConfig> = Ios
actual val preferences: IPreferences = IOSPreferences()