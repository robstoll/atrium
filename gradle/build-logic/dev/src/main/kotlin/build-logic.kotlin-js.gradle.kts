plugins {
    id("build-logic.kotlin-multiplatform-conventions")
}

kotlin {
    js(IR) {
        nodejs {
        }
    }
}
