// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(Config.Plugins.android)
        classpath(Config.Plugins.kotlin)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.google.com")
    }
}
