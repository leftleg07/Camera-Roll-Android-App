plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Config.SdkVersions.compile)

    defaultConfig {
        minSdkVersion(Config.SdkVersions.min)
        targetSdkVersion(Config.SdkVersions.target)

        versionCode = Config.Version.code
        versionName = Config.Version.name

        vectorDrawables.useSupportLibrary = true
        applicationId = "us.koller.cameraroll.kotlin"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release").configure {
            isMinifyEnabled = true
        }

        named("debug").configure {
            applicationIdSuffix = ".debug"
            resValue("string", "app_name", "Camera Roll (debug)")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {
    implementation(Config.Libs.Kotlin.jvm)
    implementation(Config.Libs.AndroidX.appcompat)
    implementation(Config.Libs.AndroidX.core)
    implementation(Config.Libs.AndroidX.fragment)
    implementation(Config.Libs.AndroidX.cardView)
    implementation(Config.Libs.Material.material)
    implementation(Config.Libs.AndroidX.palette)
    implementation(Config.Libs.AndroidX.exifInterface)
    implementation(Config.Libs.AndroidX.preference)
    implementation(Config.Libs.AndroidX.annotation)

    implementation(Config.Libs.Misc.glide)
    annotationProcessor(Config.Libs.Misc.glideCompiler)

    implementation(Config.Libs.Misc.scaleImageView)

    // using an older Version because I still want to use the ImageView
    //noinspection GradleDependency
    implementation(Config.Libs.Misc.photoView)

    implementation(Config.Libs.Misc.dragSelectRecyclerView)

    implementation(Config.Libs.Misc.exoPlayer)

    implementation(Config.Libs.Misc.pageIndicatorView)

    implementation(Config.Libs.Misc.recyclerFastScroll)

    // rx preferences
    // https://github.com/f2prateek/rx-preferences
    implementation("com.f2prateek.rx.preferences2:rx-preferences:2.0.0")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")
}
