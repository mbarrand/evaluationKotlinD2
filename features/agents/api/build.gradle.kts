plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.evaluationp1.features.agents.api"
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

    implementation(project(":features:agents:data"))
    implementation(project(":features:agents:domain"))
    implementation(project(":features:agents:ui"))

    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    // Bundle de Room
    implementation(libs.bundles.room)

}