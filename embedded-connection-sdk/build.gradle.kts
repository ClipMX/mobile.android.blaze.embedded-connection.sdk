plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(clipLibs.plugins.hilt) apply false
    alias(clipLibs.plugins.firebase.crashlytics) apply false
    alias(clipLibs.plugins.firebase.perf) apply false
    alias(clipLibs.plugins.datadog) apply false
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("maven-publish")
}

configurations.all {
    resolutionStrategy {
        force(clipLibs.kotlin.stdlib)
    }
}

android {
    namespace = "com.payclip.blaze.emc.sdk"
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
                "proguard-rules.pro",
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

val sourcesJar: Jar = tasks.create("sourcesJar", Jar::class.java) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

project.artifacts {
    archives(sourcesJar)
}
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.payclip.blaze"
                artifactId = "embedded-connection.sdk"
                // the version is provided by the TAG name
                version = System.getenv("GITHUB_REF_NAME") ?: "0.0.0.0"

                afterEvaluate {
                    from(components["release"])
                    artifact(project.tasks.getByName("sourcesJar"))
                }
            }
        }

        repositories {
            maven {
                name = "GitHubPackages"
                url =
                    uri("https://maven.pkg.github.com/ClipMX/mobile.android.blaze.embedded-connection.sdk")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Forking
    implementation(libs.clip.commons.hardware)
    implementation(libs.clip.commons)
    implementation(libs.clip.printer)
}
