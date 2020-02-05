package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableAllAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableAllAssertionsSpec(
    fun1(Expect<Iterable<Double>>::all).name to ::all,
    fun1(Expect<Iterable<Double?>>::all).withNullableSuffix().name to ::allNullable,
    "* ", "(!) ", "- ", "» ", ">> ", "=> "
) {
    companion object : WithAsciiReporter()

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterable<Double>> = notImplemented()
        var a1b: Expect<Iterable<Double?>> = notImplemented()

        var star: Expect<Iterable<*>> = notImplemented()

        a1 = a1.all {}

        a1b = a1b.all {}
        a1b = a1b.all(null)

        star = star.all {}
    }
}

private fun all(expect: Expect<Iterable<Double>>, assertionCreator: Expect<Double>.() -> Unit) =
    expect all assertionCreator

private fun allNullable(expect: Expect<Iterable<Double?>>, assertionCreator: (Expect<Double>.() -> Unit)?) =
    expect all assertionCreator
