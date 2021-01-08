package br.com.onze.shared

import br.com.onze.data.local.IPreferences
import io.ktor.client.engine.*

expect val engine: HttpClientEngineFactory<HttpClientEngineConfig>
expect val preferences: IPreferences