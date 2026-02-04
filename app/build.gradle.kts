import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.*

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization")
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "com.example.btube"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.btube"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            versionNameSuffix = UUID.randomUUID().toString().substring(2..5)
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    buildFeatures {
        compose = true
        buildConfig = true
    }
    
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    
    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Material Icons Extended
    implementation(libs.androidx.material.icons.extended)

    // Media3
    implementation(libs.bundles.media3)
    
    // Lifecycle
    implementation(libs.bundles.lifecycle)

    // ZXing
    implementation(libs.core)
    
    // Kotlinx DateTime
    implementation(libs.kotlinx.datetime)

    // Ktor Client
    implementation(libs.bundles.ktor.client)
    
    // Koin
    implementation(libs.bundles.koin)

    // Coil
    implementation(libs.bundles.coil)
    
    // Paging
    implementation(libs.bundles.paging)
    
    // DataStore
    implementation(libs.bundles.datastore)
    
    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // Palette
    implementation(libs.androidx.palette)

    // Lottie
    implementation(libs.lottie.compose)
    
    // Adaptive Layout
    implementation(libs.bundles.adaptive.layout)

    // Room
    ksp(libs.androidx.room.compiler)
    implementation(libs.bundles.room)
    
    // Sensebot (验证码)
    implementation(libs.sensebot)
    
    // Reorderable
    implementation(libs.reorderable)
    
    // ConstraintLayout
    implementation(libs.androidx.constraintlayout.compose)
    
    // Conscrypt
    implementation(libs.conscrypt.android)
    
    // Project modules
    implementation(project(":bili_sdk"))
    implementation(project(":common_ui_core"))
    implementation(project(":feature_annotations"))
}