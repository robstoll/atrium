package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class CollectionAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionAssertionsSpec(
    "toBe ${Empty::class.simpleName}" to ::isEmpty,
    "toBe ${Empty::class.simpleName}" to ::isNotEmpty
) {
    companion object : WithAsciiReporter()

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Collection<Int>> = notImplemented()
        val a1b: Expect<Collection<Int?>> = notImplemented()

        val star: Expect<Collection<*>> = notImplemented()

        a1 toBe Empty
        a1 notToBe Empty

        a1b toBe Empty
        a1b notToBe Empty

        star toBe Empty
        star notToBe Empty
    }
}

fun isEmpty(expect: Expect<Collection<Int>>) = expect toBe Empty
fun isNotEmpty(expect: Expect<Collection<Int>>) = expect notToBe Empty
