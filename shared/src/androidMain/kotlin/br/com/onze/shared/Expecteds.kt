package br.com.onze.shared

import br.com.onze.data.local.IPreferences
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*

actual val engine: HttpClientEngineFactory<HttpClientEngineConfig> = Android
actual val preferences: IPreferences = AndroidPreferences()