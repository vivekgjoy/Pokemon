plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {

    namespace = "com.mobil80.pokemon"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mobil80.pokemon"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        multiDexEnabled = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
    implementation("androidx.compose.ui:ui:1.6.0")
    implementation("androidx.compose.material:material:1.6.0")
    implementation("androidx.compose.ui:ui-tooling:1.6.0")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
//    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.navigation.compose)
    implementation("androidx.navigation:navigation-compose:2.7.0")
    implementation(libs.androidx.palette.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    // GSON
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    implementation("com.squareup.okhttp3:okhttp:3.4.1")
    implementation("com.squareup.okhttp3:logging-interceptor:3.4.1")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:3.1.2")

    // Koin
    implementation("io.insert-koin:koin-core:2.2.3")
    // Koin AndroidX Scope features
    implementation("io.insert-koin:koin-androidx-scope:2.2.3")
    // Koin AndroidX ViewModel features
    implementation("io.insert-koin:koin-androidx-viewmodel:2.2.3")

    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.3.0")
//    implementation("io.coil-kt:coil:1.1.1")
//    implementation("com.google.accompanist:accompanist-coil:0.15.0")
//    implementation("com.google.accompanist:accompanist-coil:0.7.0")

//    implementation("io.coil-kt:coil:2.4.0") // or latest stable version
//    implementation("com.google.accompanist:accompanist-coil:0.15.0")

    // GSON
    implementation("com.google.code.gson:gson:2.8.8")
}

