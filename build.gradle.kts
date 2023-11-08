// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0" apply false
}

buildscript {
    repositories {
        maven(url = "https://repo1.maven.org/maven2")
    }
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
