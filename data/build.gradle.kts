plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
}


kotlin {
    jvm()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":domain"))

                implementation("org.jetbrains.kotlin:kotlin-stdlib")

                implementation("io.ktor:ktor-client-core:${Deps.KTOR_VERSION}")
                implementation("io.ktor:ktor-client-json:${Deps.KTOR_VERSION}")
                implementation("io.ktor:ktor-client-logging:${Deps.KTOR_VERSION}")
                implementation("io.ktor:ktor-client-serialization:${Deps.KTOR_VERSION}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Deps.SERIALIZATION_VERSION}")
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:${Deps.SERIALIZATION_VERSION}")
            }
        }
    }
}
