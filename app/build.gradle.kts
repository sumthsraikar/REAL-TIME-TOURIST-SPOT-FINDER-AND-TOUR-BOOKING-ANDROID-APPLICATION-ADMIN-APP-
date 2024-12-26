plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.adminapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.adminapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true // Recommended for production
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")                 // Firebase Authentication
    implementation("com.google.firebase:firebase-firestore")
    implementation ("com.google.firebase:firebase-firestore:24.7.1")
    implementation ("androidx.recyclerview:recyclerview:1.3.1")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.fragment:fragment:1.5.5")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-firestore-ktx:24.0.0") // For Firestore



}
