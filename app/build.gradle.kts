plugins {
    id("com.android.application")
}

android {
    namespace = "com.jadmaaz.androidwallpapermanager"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.jadmaaz.androidwallpapermanager"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets {
      named("main") {
        kotlin.srcDirs("src/main/kotlin")
      }
    }
}

dependencies {
  implementation("androidx.work:work-runtime-ktx:2.10.0")
  implementation("androidx.documentfile:documentfile:1.0.1")
}
