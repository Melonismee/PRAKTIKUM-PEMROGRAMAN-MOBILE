plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.calcompose"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.calcompose"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    // Compose BOM (WAJIB)
    implementation(platform(libs.androidx.compose.bom))
    testImplementation("junit:junit:4.13.2")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose UI
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // ✅ Material 3 (cukup satu ini)
    implementation(libs.androidx.compose.material3)

    // ✅ Icon
    implementation("androidx.compose.material:material-icons-extended")

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
}