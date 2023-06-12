group = "me.jakejmattson"
version = "1.1.0"

plugins {
    kotlin("jvm") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.github.ben-manes.versions") version "0.47.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:5.6.0")
    implementation("org.slf4j:slf4j-nop:2.0.7")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }

    compileJava {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    shadowJar {
        archiveFileName.set("airshare.jar")
        manifest {
            attributes("Main-Class" to "me.jakejmattson.airshare.AirShareKt")
        }
    }
}