import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "2.0.20"
	kotlin("kapt") version "2.0.20"
	kotlin("plugin.allopen") version "2.0.20"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

val coroutinesVersion = project.properties["coroutinesVersion"]
val kotestVersion = project.properties["kotestVersion"]
val mockkVersion = project.properties["mockkVersion"]

dependencies {
	// import boms
	implementation(platform("io.projectreactor:reactor-bom:2024.0.0"))

	// build
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.1")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${coroutinesVersion}")

	// test
	// Junit
	// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter
	testImplementation("org.junit.jupiter:junit-jupiter:5.11.2")

	// test helpers for Kotlin coroutines
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${coroutinesVersion}")

	// Kotest assertions
	testImplementation("io.kotest:kotest-assertions-core-jvm:${kotestVersion}")

	// mockk: mocking framework for Kotlin
	testImplementation("io.mockk:mockk-jvm:${mockkVersion}")
	testImplementation("io.projectreactor:reactor-test")
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
