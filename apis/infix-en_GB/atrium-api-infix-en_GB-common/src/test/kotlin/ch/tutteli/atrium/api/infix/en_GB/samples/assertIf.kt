package ch.tutteli.atrium.api.infix.en_GB.samples

fun assertIf(shouldAssert: Boolean, assert: () -> Unit) {
    if (shouldAssert) assert()
}
