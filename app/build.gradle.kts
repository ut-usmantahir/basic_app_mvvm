plugins {
//    kotlin("kapt")
    id("com.android.application")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
}

android {
    namespace = "ai.chatprism.modlife"
    compileSdk = 34

    defaultConfig {
        applicationId = "ai.chatprism.modlife"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Koin Core features
    implementation ("io.insert-koin:koin-core:3.2.1")
    // Koin main features for Android
    implementation ("io.insert-koin:koin-android:3.2.1")

    /*show retrofit network message in a log*/
    implementation("com.squareup.okhttp3:logging-interceptor:4.3.1")

    /*Retrofit for making API calls*/
    implementation ("com.squareup.retrofit2:retrofit:2.4.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.4.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.3.0")

    //coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    //Add KTX dependencies
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    //TODO: remove these dependencies of RX
    /*RxAndroid and RxJava*/
    implementation ("io.reactivex.rxjava2:rxjava:2.2.6")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")

    /*Dimen lib*/
    implementation ("com.intuit.sdp:sdp-android:1.0.6")
    implementation ("com.intuit.ssp:ssp-android:1.0.6")

    /*WorkManager Version 2.7.0 is required for apps targeting Android 12 (S).*/
    implementation ("androidx.work:work-runtime-ktx:2.9.0")

    /* Glide lib*/
    implementation ("com.github.bumptech.glide:glide:4.13.2")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.2")

}
