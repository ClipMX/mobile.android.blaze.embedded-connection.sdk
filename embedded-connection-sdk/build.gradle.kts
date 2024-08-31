plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(clipLibs.plugins.hilt) apply false
    alias(clipLibs.plugins.firebase.crashlytics) apply false
    alias(clipLibs.plugins.firebase.perf) apply false
    alias(clipLibs.plugins.datadog) apply false
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

configurations.all{
    resolutionStrategy{
        force(clipLibs.kotlin.stdlib)
    }
}

android {
    namespace = "com.payclip.blaze.embedded_connection.sdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 22

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.clip.commons.hardware)
    implementation(libs.clip.commons)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

}