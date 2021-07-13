// Example project to show how to use Atrium in combination with mocha
// For more information on how to setup Atrium for a JS project -> https://github.com/robstoll/atrium#js

val atriumApi = "atrium-fluent-en_GB-js"
val atriumVersion = "0.16.0"

repositories {
    mavenCentral()
}

dependencies {
    // for infix-api -> change to 'atrium-infix-en_GB-js'
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib-js")

    // setup for Atrium:
    "testImplementation"("ch.tutteli.atrium:$atriumApi:$atriumVersion")

    // setup for mocha:
    "testImplementation"("org.jetbrains.kotlin:kotlin-test-js")
}

plugins {
    kotlin("js") version "1.5.20"
}

group = "org.atriumlib.samples"
version = "0.0.1"

kotlin {
    js {
        nodejs()
    }
}
