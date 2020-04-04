package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class CollectionAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionAssertionsSpec(
    "toBe ${empty::class.simpleName}" to Companion::isEmpty,
    "notToBe ${empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object : WithAsciiReporter() {
        private fun isEmpty(expect: Expect<Collection<Int>>) = expect toBe empty
        private fun isNotEmpty(expect: Expect<Collection<Int>>) = expect notToBe empty

    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<List<Int>> = notImplemented()
        val a1b: Expect<Set<Int?>> = notImplemented()

        val star: Expect<Collection<*>> = notImplemented()

        a1 toBe empty
        a1 notToBe empty

        a1b toBe empty
        a1b notToBe empty

        star toBe empty
        star notToBe empty
    }
}

