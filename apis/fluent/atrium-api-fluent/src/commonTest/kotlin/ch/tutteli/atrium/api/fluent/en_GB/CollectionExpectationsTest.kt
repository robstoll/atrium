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
    @Suppress("AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var a1: Expect<List<Int>> = expect(emptyList())
        var a1b: Expect<List<Int>> = expect(listOf(1, 2))
        var a2: Expect<Set<Int?>> = expect(emptySet())
        var a2b: Expect<Set<Int?>> = expect(setOf(1, 2))

        var star: Expect<Collection<Any?>> = expect(emptyList())
        var star2: Expect<Collection<*>> = expect(listOf(1, 2))

        a1.toBeEmpty()
        a1b.notToBeEmpty()

        a2.toBeEmpty()
        a2b.notToBeEmpty()

        star.toBeEmpty()
        star2.notToBeEmpty()

        a1b.toHaveSize(2)
        a2b.toHaveSize(2)
        star2.toHaveSize(2)

        a1.size
        a1 = a1.size {
            this.toBeEqualComparingTo(0)
        }

        a1b.size
        a1b = a1b.size {
            this.toBeEqualComparingTo(2)
        }

        star.size
        star = star.size {
            this.toBeEqualComparingTo(0)
        }
    }
}
