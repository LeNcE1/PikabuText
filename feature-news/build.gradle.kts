plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    kotlin("kapt")
}

val apiBaseUrl: String by project
val appScope: String by project

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        buildToolsVersion("30.0.0")
        buildConfigField("String", "BASE_URL", apiBaseUrl)
        buildConfigField("String", "APP_SCOPE", appScope)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

val kotlinVersion: String by project
val coreKtxVersion: String by project
val appcompatVersion: String by project
val lifecycleVersion: String by project
val constraintVersion: String by project
val okhttpVersion: String by project
val retrofitVersion: String by project
val recyclerviewVersion: String by project
val materialVersion: String by project
val fragmentKtxVersion: String by project
val toothpickVersion: String by project
val ciceroneVersion: String by project
val coroutinesVersion: String by project
val adapterDelegatesVersion: String by project
val swipeRefreshLayoutVersion: String by project

dependencies {
    implementation(project(":feature-database"))
    implementation(project(":feature-details"))
    implementation(project(":core"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.appcompat:appcompat:$appcompatVersion")

    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    implementation("androidx.constraintlayout:constraintlayout:$constraintVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttpVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation("androidx.recyclerview:recyclerview:$recyclerviewVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.fragment:fragment:$fragmentKtxVersion")
    implementation("androidx.fragment:fragment-ktx:$fragmentKtxVersion")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshLayoutVersion")

    implementation("com.hannesdorfmann:adapterdelegates4-kotlin-dsl:$adapterDelegatesVersion")
    implementation("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-layoutcontainer:$adapterDelegatesVersion")
    implementation("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:$adapterDelegatesVersion")

    implementation("com.github.stephanenicolas.toothpick:toothpick-runtime:$toothpickVersion")
    implementation("com.github.stephanenicolas.toothpick:smoothie-androidx:$toothpickVersion")
    implementation("com.github.stephanenicolas.toothpick:smoothie-lifecycle-ktp:$toothpickVersion")
    implementation("com.github.stephanenicolas.toothpick:ktp:$toothpickVersion")
    kapt("com.github.stephanenicolas.toothpick:toothpick-compiler:$toothpickVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    implementation("ru.terrakok.cicerone:cicerone:$ciceroneVersion")
}
