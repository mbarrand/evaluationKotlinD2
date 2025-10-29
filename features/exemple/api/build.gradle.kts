plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.evaluationp1.features.exemple.api"
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

    implementation(project(":features:exemple:data"))
    implementation(project(":features:exemple:domain"))
    implementation(project(":features:exemple:ui"))

    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.bundles.koin)

}