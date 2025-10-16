package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import kotlin.test.Test

object IterableExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractIterableExpectationsTest(
    fun0(Expect<Iterable<Int>>::toHaveElements),
    fun0(Expect<Iterable<Int>>::notToHaveElements),
    feature0(Expect<Iterable<Int>>::min),
    fun1(Expect<Iterable<Int>>::min),
    feature0(Expect<Iterable<Int>>::max),
    fun1(Expect<Iterable<Int>>::max),
    fun0(Expect<Iterable<Int>>::toHaveElementsAndNoDuplicates),
    property(Expect<Iterable<Int>>::last),
    fun1(Expect<Iterable<Int>>::last),
) {

    @Test
    fun ambiguityTest() {
        val a1 = expect(listOf(1.0, 2.0))
        val a1b = expect(setOf(1.0, 2.0, null))
        val star = expect(listOf(1, 2, 3) as Collection<*>)

        a1.toHaveElements()
        a1.notToHaveElements()
        a1.toHaveElementsAndNoDuplicates()

        a1b.toHaveElements()
        a1b.notToHaveElements()
        a1b.toHaveElementsAndNoDuplicates()

        star.toHaveElements()
        star.notToHaveElements()
        star.toHaveElementsAndNoDuplicates()

        //nullable not supported by min/max or rather T : Comparable<T> does not exist for T? (one cannot implement an interface for the nullable type)
        //same for Iterable<*>
        a1.min()
        a1.max()

        a1.min { }
        a1.max { }

        a1.last
        a1.last { }
    }
}
