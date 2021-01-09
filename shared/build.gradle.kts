import Configurations.COMPILE_SDK
import Configurations.MIN_SDK
import Configurations.TARGET_SDK
import Deps.COROUTINES_VERSION
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

android {
    compileSdkVersion(COMPILE_SDK)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(MIN_SDK)
        targetSdkVersion(TARGET_SDK)
    }
}


kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "Shared"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":presentation"))

                implementation("org.jetbrains.kotlin:kotlin-stdlib")
                implementation("io.ktor:ktor-client-core:${Deps.KTOR_VERSION}")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
                implementation("com.soywiz.korlibs.klock:klock:2.0.0-rc3")
            }
        }

        val androidMain by getting {
            dependencies {
                api("com.orhanobut:hawk:${Deps.HAWK_VERSION}")
                implementation("io.ktor:ktor-client-android:${Deps.KTOR_VERSION}")
            }
        }


        val iosMain by getting {
            dependencies {
                // api("com.squareup.sqldelight:native-driver:$sqldelightVersion")
                implementation("io.ktor:ktor-client-ios:${Deps.KTOR_VERSION}")
            }
        }
    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework =
        kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)