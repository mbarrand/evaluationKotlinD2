plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.evaluationp1.features.maps.ui"
    compileSdk = 36

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
    implementation(project(":features:maps:domain"))
    implementation(project(":core:ui"))

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose.ui)

    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.bundles.koin)
    // Navigation
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.compose.navigation)

    debugImplementation(libs.androidx.compose.ui.tooling)
}