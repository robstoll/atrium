package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

fun runIf(shouldRun: Boolean, action: () -> Unit) {
    if (shouldRun) action()
}
