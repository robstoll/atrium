plugins {
    `kotlin-dsl`
}
kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    api("ch.tutteli.niok:niok:1.4.7")
}
