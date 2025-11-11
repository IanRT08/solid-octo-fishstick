plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "mx.edu.utez.appgps"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "mx.edu.utez.appgps"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //UI
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    //Navegación (NavController)
    implementation("androidx.navigation:navigation-compose:2.7.7")
    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.1")
    //Base de Datos (Room / SQLite)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1") //Para Coroutines y Flow
    ksp(libs.androidx.room.compiler)
    //Ubicación (GPS)
    implementation("com.google.android.gms:play-services-location:21.2.0")
    //Mapas (OSM)
    implementation("org.osmdroid:osmdroid-compose:1.0.0")
    //Cámara (CameraX)
    implementation("androidx.camera:camera-core:1.3.3")
    implementation("androidx.camera:camera-camera2:1.3.3")
    implementation("androidx.camera:camera-lifecycle:1.3.3")
    implementation("androidx.camera:camera-view:1.3.3")
    //Carga de Imágenes (Coil para la galería)
    implementation("io.coil-kt:coil-compose:2.6.0")
}