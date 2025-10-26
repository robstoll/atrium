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

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE", "unused")
    @Test
    fun ambiguityTest() {
        var a1a: Expect<List<Int>> = expect(emptyList())
        var a1b: Expect<List<Int>> = expect(listOf(1))
        var a2a: Expect<Set<Int?>> = expect(emptySet())
        var a2b: Expect<Set<Int?>> = expect(setOf(1))

        var starA: Expect<Collection<Any?>> = expect(emptyList())
        var starB: Expect<Collection<*>> = expect(listOf(1))

        a1a = a1a toBe empty
        a1b = a1b notToBe empty

        a2a = a2a toBe empty
        a2b = a2b notToBe empty

        starA = starA toBe empty
        starB = starB notToBe empty

        val i1: Expect<Int> = a1a.size
        a1a = a1a size { it toEqual 0 }

        val i2: Expect<Int> = a1b.size
        a1b = a1b size { it toEqual 1 }

        val i3: Expect<Int> = starA.size
        starA = starA size { it toEqual 0 }

        val i4: Expect<Int> = starB.size
        starA = starB size { it toEqual 1 }
    }
}

