plugins {
    id("com.android.application")
}

android {
    namespace = "com.app.bincore"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.app.bincore"
        minSdk = 25
        targetSdk = 35
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
}

dependencies {
    implementation("androidx.drawerlayout:drawerlayout:1.2.0")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-inappmessaging:19.0.0")
    implementation("com.google.guava:guava:26.0-android") {
        exclude(group = "com.google.guava", module = "listenablefuture")
    }
    // Remove listenablefuture dependency explicitly
    // implementation("com.google.guava:listenablefuture:1.0")

    // JUnit for unit testing
    testImplementation("junit:junit:4.13.2")

    // Android testing libraries
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

// Exclude listenablefuture from all dependencies globally
configurations.all {
    exclude(group = "com.google.guava", module = "listenablefuture")
}
