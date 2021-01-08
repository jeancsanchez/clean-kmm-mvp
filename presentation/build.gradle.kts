plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":data"))
                api(project(":domain"))

                implementation("org.jetbrains.kotlin:kotlin-stdlib")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Deps.COROUTINES_VERSION}")
            }
        }
    }
}
