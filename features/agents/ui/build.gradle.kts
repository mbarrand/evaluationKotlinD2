plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.evaluationp1.features.agents.ui"
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
    implementation(project(":features:agents:domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:system"))

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
    // Phosphor pour les icones
    implementation(libs.bundles.phosphor)

    debugImplementation(libs.androidx.compose.ui.tooling)
}