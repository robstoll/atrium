package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class CollectionAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionAssertionsSpec(
    "toBe ${empty::class.simpleName}" to Companion::isEmpty,
    "notToBe ${empty::class.simpleName}" to Companion::isNotEmpty,
    property<Collection<Int>, Int>(Expect<Collection<Int>>::size),
    fun1<Collection<Int>, Expect<Int>.() -> Unit>(Expect<Collection<Int>>::size)
) {
    companion object : WithAsciiReporter() {
        private fun isEmpty(expect: Expect<Collection<Int>>) = expect toBe empty
        private fun isNotEmpty(expect: Expect<Collection<Int>>) = expect notToBe empty

    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<List<Int>> = notImplemented()
        var a1b: Expect<Set<Int?>> = notImplemented()

        var star: Expect<Collection<*>> = notImplemented()

        a1 toBe empty
        a1 notToBe empty

        a1b toBe empty
        a1b notToBe empty

        star toBe empty
        star notToBe empty

        a1.size
        a1 = a1 size { }

        a1b.size
        a1b = a1b size { }

        star.size
        star = star size { }
    }
}

