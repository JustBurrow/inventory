import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"

    kotlin("jvm") version "1.3.41"
    kotlin("plugin.jpa") version "1.3.41"
    kotlin("plugin.spring") version "1.3.41"
}

group = "kr.lul.inventory"
version = "0.0.1.SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_12

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}