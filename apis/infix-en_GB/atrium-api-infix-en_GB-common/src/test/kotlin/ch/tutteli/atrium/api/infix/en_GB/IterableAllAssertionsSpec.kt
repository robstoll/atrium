package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented

class IterableAllAssertionsSpec : ch.tutteli.atrium.specs.integration.IterableAllAssertionsSpec(
    fun1(Expect<Iterable<Double>>::all).name to Companion::all,
    fun1(Expect<Iterable<Double?>>::all).name to Companion::allNullable,
    "* ", "(!) ", "- ", "» ", ">> ", "=> "
) {
    companion object {
        fun all(expect: Expect<Iterable<Double>>, assertionCreator: Expect<Double>.() -> Unit) =
            expect all assertionCreator

        fun allNullable(expect: Expect<Iterable<Double?>>, assertionCreator: (Expect<Double>.() -> Unit)?) =
            expect all assertionCreator
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Iterable<Double>> = notImplemented()
        var a2: Expect<out Iterable<Double>> = notImplemented()
        var a1b: Expect<Iterable<Double?>> = notImplemented()
        var a2b: Expect<out Iterable<Double?>> = notImplemented()

        var a3: Expect<out Iterable<*>> = notImplemented()

        a1 = a1.all {}
        a2 = a2.all {}

        a1b = a1b.all {}
        a2b = a2b.all {}
        a1b = a1b.all(null)
        a2b = a2b.all(null)

        a3 = a3.all {}
    }
}


