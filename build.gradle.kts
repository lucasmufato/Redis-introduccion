import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val coroutinesV = "1.7.1"
val lettuceV = "6.2.4.RELEASE"
val mockitoV = "4.1.0"
val kotlinTestV = "1.8.21"
val junitV = "5.9.3"
val embeddedRedisV="0.7.3"
val kotlinxJsonVersion="1.5.1"

plugins {
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"
    application
}

group = "com.mufato"
version = "1.0-SNAPSHOT"



repositories {
    mavenCentral()
}

dependencies {
    implementation("io.lettuce:lettuce-core:$lettuceV")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesV")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$coroutinesV")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxJsonVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinTestV")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:$mockitoV")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:$junitV")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:$junitV")
    testImplementation ("it.ozimov:embedded-redis:$embeddedRedisV")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
