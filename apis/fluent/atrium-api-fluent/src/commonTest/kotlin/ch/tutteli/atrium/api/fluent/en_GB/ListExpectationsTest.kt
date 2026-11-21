package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class ListExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractListExpectationsTest(
    feature1(Expect<List<Int>>::get),
    fun2(Expect<List<Int>>::get),
    feature1(Expect<List<Int?>>::get).withNullableSuffix(),
    fun2(Expect<List<Int?>>::get).withNullableSuffix(),
) {

    @Suppress("UNUSED_VALUE", "UNUSED_VARIABLE", "unused", "AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var a1: Expect<AbstractList<Int>> = expect(object : AbstractList<Int>() {
            override val size = 2
            override fun get(index: Int) = index + 1
        })
        var a1b: Expect<MutableList<Int?>> = expect(mutableListOf(1, null))

        var star: Expect<List<*>> = expect(listOf(1, 2))

        a1.get(1)
        a1 = a1.get(1) { toEqual(2) }

        a1b.get(1)
        a1b = a1b.get(1) { toEqual(null) }

        star.get(1)
        star = star.get(1) { toEqual(2) }
    }
}
