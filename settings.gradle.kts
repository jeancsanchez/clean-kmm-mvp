pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:4.0.1")
            }
        }
    }
}
rootProject.name = "onze"

include(":android")
include(":shared")
include(":iosOnze")
include(":presentation")
include(":data")
include(":domain")

enableFeaturePreview("GRADLE_METADATA")