package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.test.Test

class CollectionExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractCollectionExpectationsTest(
    "toBe ${empty::class.simpleName}" to Companion::isEmpty,
    "notToBe ${empty::class.simpleName}" to Companion::isNotEmpty,
    property<Collection<Int>, Int>(Expect<Collection<Int>>::size),
    fun1<Collection<Int>, Expect<Int>.() -> Unit>(Expect<Collection<Int>>::size)
) {
    companion object {
        private fun isEmpty(expect: Expect<Collection<Int>>) = expect toBe empty
        private fun isNotEmpty(expect: Expect<Collection<Int>>) = expect notToBe empty

    }

    @Suppress("AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var a1: Expect<List<Int>> = expect(emptyList())
        var a1b: Expect<List<Int>> = expect(listOf(1))
        var a2: Expect<Set<Int?>> = expect(emptySet())
        var a2b: Expect<Set<Int?>> = expect(setOf(1))

        var star: Expect<Collection<Any?>> = expect(emptyList())
        var star2: Expect<Collection<*>> = expect(listOf(1))

        a1 toBe empty
        a1b notToBe empty

        a2 toBe empty
        a2b notToBe empty

        star toBe empty
        star2 notToBe empty

        a1.size
        a1 = a1 size {
            it toBeEqualComparingTo 0
        }

        a1b.size
        a1b = a1b size {
            it toBeEqualComparingTo 1
        }

        star.size
        star = star size {
            it toBeEqualComparingTo 0
        }
    }
}

