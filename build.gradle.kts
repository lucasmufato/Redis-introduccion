import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val coroutinesV = "1.5.0"
val lettuceV = "6.1.4.RELEASE"
val mockitoV = "3.2.0"
val kotlinTestV = "1.5.21"
val junitV = "5.7.2"
val embededRedisV="0.7.3"
val kotlinxJsonVersion="1.1.0"

plugins {
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.21"
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
    testImplementation ("it.ozimov:embedded-redis:$embededRedisV")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
