import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "2.3.0"
	kotlin("kapt") version "2.3.0"
	kotlin("plugin.allopen") version "2.3.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

val coroutinesVersion = project.properties["coroutinesVersion"]
val kotestVersion = project.properties["kotestVersion"]
val mockkVersion = project.properties["mockkVersion"]
val junitVersion = project.properties["junitVersion"]
val reactorVersion = project.properties["reactorVersion"]

dependencies {
	// import boms
	implementation(platform("io.projectreactor:reactor-bom:${reactorVersion}"))
	// https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-bom
	implementation(platform("org.jetbrains.kotlinx:kotlinx-coroutines-bom:${coroutinesVersion}"))


	// build
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.20.1")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	// test
	// Junit
	testImplementation(platform("org.junit:junit-bom:${junitVersion}"))

	// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter
	testImplementation("org.junit.jupiter:junit-jupiter")

	// test helpers for Kotlin coroutines
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")

	// Kotest assertions
	testImplementation("io.kotest:kotest-assertions-core-jvm:${kotestVersion}")

	// mockk: mocking framework for Kotlin
	testImplementation("io.mockk:mockk-jvm:${mockkVersion}")
	testImplementation("io.projectreactor:reactor-test")
}

kotlin{
	jvmToolchain(21)
	compilerOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
