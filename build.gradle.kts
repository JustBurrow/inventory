import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.3.41"
    val springVersion = "2.1.6.RELEASE"

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion apply false

    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.noarg") version kotlinVersion apply false
    kotlin("plugin.allopen") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false

    id("org.springframework.boot") version springVersion
    id("io.spring.dependency-management") version "1.0.8.RELEASE"

    idea
}

repositories {
    mavenCentral()
    jcenter()
}

allprojects {
    group = "kr.lul.inventory"
    version = "0.0.1.SNAPSHOT"
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
        jcenter()
    }

    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }

        dependencies {
            dependency("org.apache.commons:commons-lang3:3.9")
            dependency("com.willowtreeapps.assertk:assertk-jvm:0.17")
            dependency("io.github.jpenren:thymeleaf-spring-data-dialect:3.4.1")
        }
    }

    dependencies {
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}
