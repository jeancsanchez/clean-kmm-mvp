buildscript {
    val kotlin_version by extra("1.4.21")
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath(kotlin("gradle-plugin", version = Deps.Kotlin.KOTLIN_VERSION))
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Deps.Kotlin.KOTLIN_VERSION}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}