plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "gob.pe.msi.trakingrealtime"
    compileSdk = 34

    defaultConfig {
        applicationId = "gob.pe.msi.trakingrealtime"
        minSdk = 29
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
            buildConfigField("String", "SERVER_BASE_URL", "\"taylor swift release real\"")
            buildConfigField("boolean", "ENABLE_CUSTOM_BUILD_CONFIG", "true")
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation("com.vmadalin:easypermissions-ktx:1.0.0")


    val retrofitVersion = "2.9.0"

    val retrofitDependency = when (retrofitVersion) {
        "2.9.0" -> "com.squareup.retrofit2:retrofit:$retrofitVersion"
        else -> "com.squareup.retrofit2:retrofit:$retrofitVersion" // Puedes agregar más casos si es necesario
    }
    val gsonDependency = when (retrofitVersion) {
        "2.9.0" -> "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        else -> "com.squareup.retrofit2:converter-gson:$retrofitVersion" // Puedes agregar más casos si es necesario
    }
    implementation(retrofitDependency)
    implementation(gsonDependency)

    implementation("com.google.android.material:material:1.6.1")
    implementation("com.google.android.gms:play-services:7.0.0")
            implementation("com.google.android.gms:play-services-maps:18.0.2")
            implementation("com.google.android.gms:play-services-location:20.0.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}