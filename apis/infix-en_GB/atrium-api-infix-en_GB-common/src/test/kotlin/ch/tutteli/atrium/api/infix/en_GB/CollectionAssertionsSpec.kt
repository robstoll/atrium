package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class CollectionAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionAssertionsSpec(
    "toBe ${Empty::class.simpleName}" to Companion::isEmpty,
    "notToBe ${Empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object : WithAsciiReporter() {
        private fun isEmpty(expect: Expect<Collection<Int>>) = expect toBe Empty
        private fun isNotEmpty(expect: Expect<Collection<Int>>) = expect notToBe Empty

    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<List<Int>> = notImplemented()
        val a1b: Expect<Set<Int?>> = notImplemented()

        val star: Expect<Collection<*>> = notImplemented()

        a1 toBe Empty
        a1 notToBe Empty

        a1b toBe Empty
        a1b notToBe Empty

        star toBe Empty
        star notToBe Empty
    }
}

