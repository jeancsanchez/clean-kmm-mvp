plugins {
    kotlin("multiplatform")
}


kotlin {
    jvm()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib")
//                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:${Deps.Kotlin.STD_LIB_VERSION}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Deps.COROUTINES_VERSION}")
            }
        }
    }
}