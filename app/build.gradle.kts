plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "woowacourse.campus"
    compileSdk = 34

    defaultConfig {
        applicationId = "woowacourse.campus"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    // mockk
    testImplementation("io.mockk:mockk:1.12.0")
    implementation(project(":network"))
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation("androidx.test.ext:truth:1.5.0")
    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.4")
    // material
    implementation("androidx.compose.material:material:1.5.3")
    // view model
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    // fragment
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    // kotest
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.7.2")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.7.2")
    // reflection
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.8.21")
    // ktor
    implementation("io.ktor:ktor-client-core:2.1.3")
    implementation("io.ktor:ktor-client-cio:2.1.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.1.3")
    // gson
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("io.ktor:ktor-client-gson:2.1.3")
    implementation("io.ktor:ktor-serialization-gson:2.1.3")
}