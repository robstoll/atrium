plugins {
    kotlin("js")
    id("build-logic.kotlin-conventions")
}

kotlin {
    js(IR) {
        nodejs {
        }
    }
}
