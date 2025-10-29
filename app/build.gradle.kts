plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.evaluationp1"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.evaluationp1"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    implementation(project(":features:agents:ui"))
    implementation(project(":features:agents:api"))
    implementation(project(":features:maps:ui"))
    implementation(project(":features:maps:api"))
    implementation(project(":core:data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose.ui)

    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    // Dépendences pour la navigation
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.navigation.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)
}