package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.testutils.WithAsciiReporter
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property

class CollectionFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionFeatureAssertionsSpec(
    property<Collection<String>, Int>(Expect<Collection<String>>::size),
    fun1<Collection<String>, Expect<Int>.() -> Unit>(Expect<Collection<String>>::size).name to Companion::size
) {
    companion object : WithAsciiReporter() {
        fun size(expect: Expect<Collection<String>>, assertionCreator: Expect<Int>.() -> Unit) =
            expect size { assertionCreator() }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Collection<Int>> = notImplemented()
        var a1b: Expect<Collection<Int?>> = notImplemented()

        var star: Expect<out Collection<*>> = notImplemented()

        a1.size
        a1 = a1 size { }

        a1b.size
        a1b = a1b size { }

        star.size
        star = star size { }
    }
}
