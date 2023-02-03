plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    // keep in sync with /build.gradle
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    api("ch.tutteli.niok:niok:1.4.7")
}
