plugins {
    java
    alias(libs.plugins.lavalink)
}

group = "com.github.ZenKho-chill"
version = "0.2.8"

lavalinkPlugin {
    name = "lava-xm-plugin"
    apiVersion = libs.versions.lavalink.api
    serverVersion = libs.versions.lavalink.server
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
}

sourceSets {
    main {
        java.setSrcDirs(listOf<java.io.File>())
    }
}

repositories {
    maven { url = uri("https://jitpack.io") }
}

repositories {
    maven { url = uri("https://jitpack.io") }
}