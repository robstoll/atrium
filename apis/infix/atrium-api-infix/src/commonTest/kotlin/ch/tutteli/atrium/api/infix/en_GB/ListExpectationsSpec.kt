package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.jvm.JvmName

class ListExpectationsSpec : Spek({
    include(ListSpec)
    include(IterableSpec)
    include(SequenceSpec)
}) {
    object ListSpec : ch.tutteli.atrium.specs.integration.ListExpectationsSpec(
        feature1<List<Int>, Int, Int>(Expect<List<Int>>::get),
        fun2<List<Int>, Int, Expect<Int>.() -> Unit>(Companion::get),
        feature1<List<Int?>, Int, Int?>(Expect<List<Int?>>::get).withNullableSuffix(),
        fun2<List<Int?>, Int, Expect<Int?>.() -> Unit>(Companion::get).withNullableSuffix(),
        lastFeaturePair(),
        fun1<List<Int?>, Expect<Int?>.() -> Unit>(Expect<List<Int?>>::last),
        "[Atrium][List] "
    )

    object IterableSpec : Spek({
        describe("Narrow Iterable down to List") {
            it("Containing the same List after narrowing down the type") {
                val list = listOf(1, 2, 3)
                val expect: Expect<Iterable<Int>> = expect(list)
                expect asList o toEqual list
            }

            it("Containing the same List after narrowing down the type using the assertionCreator") {
                val list = listOf(1, 2, 3)
                val expect: Expect<Iterable<Int>> = expect(list)
                expect asList {
                    it toEqual list
                }
            }
        }
    })

    object SequenceSpec : Spek({
        describe("Terminate Sequence by transforming it to a List") {
            it("Containing the same sequence of values after transforming the Sequence to a List ") {
                val sequence = sequenceOf(1, 2, 3)
                val expect: Expect<Sequence<Int>> = expect(sequence)
                expect asList o toEqual listOf(1, 2, 3)
            }

            it("Containing the same sequence of values after transforming the Sequence to a List using the assertionCreator") {
                val sequence = sequenceOf(1, 2, 3)
                val expect: Expect<Sequence<Int>> = expect(sequence)
                expect asList {
                    it toEqual listOf(1, 2, 3)
                }
            }
        }
    })

    companion object {

        private fun get(expect: Expect<List<Int>>, index: Int, assertionCreator: Expect<Int>.() -> Unit) =
            expect get index(index) { assertionCreator() }

        @JvmName("getNullable")
        private fun get(
            expect: Expect<List<Int?>>,
            index: Int,
            assertionCreator: Expect<Int?>.() -> Unit
        ) = expect get index(index) { assertionCreator() }

        private fun lastFeaturePair() = feature1<List<Int?>, o, Int?>(Expect<List<Int?>>::last).name to ::lastFeature
        private fun lastFeature(expect: Expect<List<Int?>>) = expect last o
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<AbstractList<Int>> = notImplemented()
        var a1b: Expect<MutableList<Int?>> = notImplemented()

        var star: Expect<List<*>> = notImplemented()

        a1 get 1
        a1 = a1 get index(1) { }

        a1b get 1
        a1b = a1b get index(1) { }

        star get 1
        star = star get index(1) { }
    }
}
