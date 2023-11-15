import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

private val apiUrl: String = gradleLocalProperties(rootDir).getProperty("api.url")

plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
}

android {
    namespace = "com.example.splitandpay.network"
    compileSdk = 34
    buildFeatures.buildConfig = true

    defaultConfig {
        minSdk = 24
        targetSdk = 34
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                type = "String",
                name = "API_URL",
                value = "\"$apiUrl\""
            )
        }
        debug {
            isMinifyEnabled = false
            buildConfigField(
                type = "String",
                name = "API_URL",
                value = "\"$apiUrl\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.koin.android)
    api(libs.retrofit)
    api(libs.converter.gson)
    api(libs.logging.interceptor)
    api(libs.gson)
}