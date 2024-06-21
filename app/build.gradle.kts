plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "pe.gob.msi"
    compileSdk = 34

    defaultConfig {
        applicationId = "pe.gob.msi"
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")


    val retrofitVersion = "2.9.0"

    val retrofitDependency = when (retrofitVersion) {
        "2.9.0" -> "com.squareup.retrofit2:retrofit:$retrofitVersion"
        else -> "com.squareup.retrofit2:retrofit:$retrofitVersion" // Puedes agregar más casos si es necesario
    }
    val gsonDependency = when (retrofitVersion) {
        "2.9.0" -> "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        else -> "com.squareup.retrofit2:converter-gson:$retrofitVersion" // Puedes agregar más casos si es necesario
    }
    val adapterRxjavaDependency = when (retrofitVersion) {
        "2.9.0" -> "com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion"
        else -> "com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion" // Puedes agregar más casos si es necesario
    }
    implementation(retrofitDependency)
    implementation(gsonDependency)
    implementation(adapterRxjavaDependency)
    implementation("com.squareup.okhttp3:logging-interceptor:4.2.1")


    implementation("com.journeyapps:zxing-android-embedded:4.3.0") { isTransitive = false }
    implementation("androidx.activity:activity-ktx:1.9.0")
    //implementation("com.google.zxing:core:4.3.0")
    implementation("com.github.yuriy-budiyev:code-scanner:2.3.2")

    implementation(libs.javax.inject)


}