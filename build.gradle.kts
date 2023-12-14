import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
    kotlin("plugin.jpa") version "1.9.20"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

group = "com.clicktive"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.springframework.boot")
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("mysql:mysql-connector-java:8.0.32")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.springdoc:springdoc-openapi-kotlin:1.7.0")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

project(":ctv") {
    dependencies {
        implementation(project(":core"))
    }
}