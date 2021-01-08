import Configurations.COMPILE_SDK
import Configurations.KEY_ALIAS
import Configurations.KEY_PASSWORD
import Configurations.KEY_STORE_PATH
import Configurations.LOCAL_HOST
import Configurations.MIN_SDK
import Configurations.REMOTE_HOST
import Configurations.TARGET_SDK
import com.android.build.gradle.api.ApplicationVariant

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
    maven(url = "https://jitpack.io")
}

android {
    compileSdkVersion(COMPILE_SDK)
    buildToolsVersion("28.0.3")

    defaultConfig {
        applicationId = "br.com.jeancsanchez.onze"
        minSdkVersion(MIN_SDK)
        targetSdkVersion(TARGET_SDK)
        vectorDrawables.useSupportLibrary = true
        versionCode(1)
        versionName("1.0.0")
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        multiDexEnabled = true
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        val API_URL = "API_URL"
        val APP_NAME = "app_name"

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", API_URL, "\"" + REMOTE_HOST + "\"")
            resValue("string", APP_NAME, "Onze")
        }


        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false

            buildConfigField("String", API_URL, "\"" + LOCAL_HOST + "\"")
            resValue("string", APP_NAME, "DEBUG")
        }
    }

    signingConfigs {
        create("releaseConfig") {
            keyAlias = KEY_ALIAS
            keyPassword = KEY_PASSWORD
            storeFile = file(KEY_STORE_PATH)
            storePassword = KEY_STORE_PATH
        }
    }


    applicationVariants.all(object : Action<ApplicationVariant> {
        override fun execute(variant: ApplicationVariant) {
            variant.resValue("string", "version_name", variant.versionName!!)
        }
    })

    lintOptions {
        isAbortOnError = true
    }

    packagingOptions {
        exclude("META-INF/library_release.kotlin_module")
        exclude("META-INF/kotlinx-serialization-runtime.kotlin_module")
    }
}

dependencies {
    val androidxVersion = "1.0.0"

    implementation(project(":shared"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Deps.Kotlin.STD_LIB_VERSION}")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.fragment:fragment-ktx:1.2.5")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.cardview:cardview:$androidxVersion")
    implementation("androidx.legacy:legacy-support-v4:$androidxVersion")


    // Other libraries
    implementation("com.afollestad.material-dialogs:core:3.0.0-rc3")
    implementation("com.afollestad.material-dialogs:datetime:3.0.0-rc3")
    implementation("com.afollestad.material-dialogs:input:3.0.0-rc3")
    implementation("com.squareup.picasso:picasso:2.5.2")
    implementation("de.hdodenhof:circleimageview:3.0.0")
    implementation("br.com.simplepass:loading-button-android:1.3.3")
    implementation("com.jakewharton.timber:timber:4.7.1")
    api("com.orhanobut:hawk:${Deps.HAWK_VERSION}")
}