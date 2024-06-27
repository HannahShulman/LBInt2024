// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
//    ext {
//        hilt_version = "2.41"
//    }
//    dependencies {
//        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.41")
//    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}