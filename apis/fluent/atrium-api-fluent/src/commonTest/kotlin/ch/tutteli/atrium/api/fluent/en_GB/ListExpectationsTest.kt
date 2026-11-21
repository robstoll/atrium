package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.withNullableSuffix
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test

class ListExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractListExpectationsTest(
    feature1(Expect<List<Int>>::get),
    fun2(Expect<List<Int>>::get),
    feature1(Expect<List<Int?>>::get).withNullableSuffix(),
    fun2(Expect<List<Int?>>::get).withNullableSuffix(),
) {

    @TestFactory
    fun asList() = testFactory {
        describe("Narrow Iterable down to List") {
            it("Containing the same List after narrowing down the type") {
                val list = listOf(1, 2, 3)
                expect(list as Iterable<Int>).asList().toEqual(list)
            }

            it("Containing the same List after narrowing down the type using the expectationCreator") {
                val list = listOf(1, 2, 3)
                expect(list as Iterable<Int>).asList {
                    toEqual(list)
                }
            }
        }

        describe("Terminate Sequence by transforming it to a List") {
            it("Containing the same sequence of values after transforming the Sequence to a List") {
                val sequence = sequenceOf(1, 2, 3)
                expect(sequence).asList().toEqual(listOf(1, 2, 3))
            }

            it("Containing the same sequence of values after transforming the Sequence to a List using the expectationCreator") {
                val sequence = sequenceOf(1, 2, 3)
                expect(sequence).asList {
                    toEqual(listOf(1, 2, 3))
                }
            }
        }
    }

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
