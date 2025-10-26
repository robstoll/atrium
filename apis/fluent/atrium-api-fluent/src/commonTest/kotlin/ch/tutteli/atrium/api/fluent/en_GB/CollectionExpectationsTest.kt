package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.test.Test

class CollectionExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractCollectionExpectationsTest(
    fun0(Expect<Collection<Int>>::toBeEmpty),
    fun0(Expect<Collection<Int>>::notToBeEmpty),
    property<Collection<Int>, Int>(Expect<Collection<Int>>::size),
    fun1<Collection<Int>, Expect<Int>.() -> Unit>(Expect<Collection<Int>>::size)
) {
    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE", "unused")
    @Test
    fun ambiguityTest() {
        var a1a: Expect<List<Int>> = expect(emptyList())
        var a1b: Expect<List<Int>> = expect(listOf(1))
        var a2a: Expect<Set<Int?>> = expect(emptySet())
        var a2b: Expect<Set<Int?>> = expect(setOf(1))

        var starA: Expect<Collection<Any?>> = expect(emptyList())
        var starB: Expect<Collection<*>> = expect(listOf(1))

        a1a = a1a.toBeEmpty()
        a1b = a1b.notToBeEmpty()

        a2a = a2a.toBeEmpty()
        a2b = a2b.notToBeEmpty()

        starA = starA.toBeEmpty()
        starB = starB.notToBeEmpty()

        val i1: Expect<Int> = a1a.size
        a1a = a1a.size { this.toEqual(0) }

        val i2: Expect<Int> = a1b.size
        a1b = a1b.size { this.toEqual(1) }

        val i3: Expect<Int> = starA.size
        starA = starA.size { this.toEqual(0) }

        val i4: Expect<Int> = starB.size
        starA = starB.size { this.toEqual(1) }
    }
}
