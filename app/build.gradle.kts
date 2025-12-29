plugins {
    id("com.android.application")
}

android {
    namespace = "io.github.vvb2060.ims"
    defaultConfig {
        versionCode = 7
        versionName = "3.0"
    }
    signingConfigs {
        create("release") {
            val keystoreFile = file("release.jks")
            if (keystoreFile.exists()) {
                storeFile = keystoreFile
                storePassword = System.getenv("APP_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("APP_KEY_ALIAS")
                keyPassword = System.getenv("APP_KEY_PASSWORD")
                enableV1Signing = true
                enableV2Signing = true
                enableV3Signing = true
                enableV4Signing = true
            } else {
                storeFile = file("debug.keystore") 
            }
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            vcsInfo.include = false
            proguardFiles("proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "**"
        }
    }
    lint {
        checkReleaseBuilds = false
    }
    dependenciesInfo {
        includeInApk = false
    }
}

dependencies {
    implementation(libs.shizuku.provider)
    implementation(libs.shizuku.api)
    implementation(libs.hiddenapibypass)
}
