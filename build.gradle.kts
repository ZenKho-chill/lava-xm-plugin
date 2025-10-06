plugins {
    java
    `maven-publish`
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            
            pom {
                name.set("Lava XM Plugin")
                description.set("A Lavalink plugin for playing XM/MOD tracker files")
                url.set("https://github.com/ZenKho-chill/lava-xm-plugin")
                
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                
                developers {
                    developer {
                        id.set("ZenKho-chill")
                        name.set("ZenKho")
                    }
                }
                
                scm {
                    connection.set("scm:git:git://github.com/ZenKho-chill/lava-xm-plugin.git")
                    developerConnection.set("scm:git:ssh://github.com:ZenKho-chill/lava-xm-plugin.git")
                    url.set("https://github.com/ZenKho-chill/lava-xm-plugin/tree/main")
                }
            }
        }
    }
}