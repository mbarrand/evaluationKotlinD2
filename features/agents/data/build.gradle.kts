plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    // Plugin pour room : ksp
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.evaluationp1.features.agents.data"
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
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":features:agents:domain"))

    // Dépendences pour Ktor
    api(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.gson)

    // Dépendences pour room et son compiler
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
}