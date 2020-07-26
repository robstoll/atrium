package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.iterableLikeTypes
import ch.tutteli.kbox.glue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KFunction2

class IterableContainsInOrderOnlyElementsOfAssertionSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)

    // TODO remove with 1.0.0
    describe("contains.inOrder.only.elementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)).contains.inOrder.only.elementsOf(listOf())
            }.toThrow<IllegalArgumentException>()
        }
    }

    // TODO remove with 1.0.0
    describe("containsExactlyElementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)).containsExactlyElementsOf(listOf())
            }.toThrow<IllegalArgumentException>()
        }
    }

    iterableLikeTypes.forEach { (description, input) ->
        describe(description) {
            it("passing an empty iterable throws an IllegalArgumentException") {
                expect {
                    expect(listOf(1, 2)).contains.inOrder.only.elementsOf(input)
                }.toThrow<IllegalArgumentException>()
            }
        }
    }

    iterableLikeTypes.forEach { (description, input) ->
        describe(description) {
            it("passing an empty iterable throws an IllegalArgumentException") {
                expect {
                    expect(listOf(1, 2)).containsExactlyElementsOf(input)
                }.toThrow<IllegalArgumentException>()
            }
        }
    }

    describe("contains.inOrder.only.elementsOf") {
        it("passing a string instead of an IterableLike throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)).contains.inOrder.only.elementsOf("test")
            }.toThrow<IllegalArgumentException> {
                messageContains("toVarArg accepts arguments of types Iterable, Sequence, Array")
            }
        }
    }

    describe("containsExactlyElementsOf") {
        it("passing a string instead of an IterableLike throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)).containsExactlyElementsOf("test")
            }.toThrow<IllegalArgumentException> {
                messageContains("toVarArg accepts arguments of types Iterable, Sequence, Array")
            }
        }
    }
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() = "$contains.$inOrder.$inOrderElementsOf" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.contains.inOrder.only.elementsOf(listOf(a, *aX))

        fun getContainsNullablePair() =
            "$contains.$inOrder.$inOrderElementsOf" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.contains.inOrder.only.elementsOf(listOf(a, *aX))

        private val containsExactlyElementsOfShortcutFun: KFunction2<Expect<Iterable<Double>>, Iterable<Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsExactlyElementsOf

        private fun getContainsShortcutPair() = Pair(containsExactlyElementsOfShortcutFun.name, Companion::containsExactlyElementsOfShortcut)

        private fun containsExactlyElementsOfShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.containsExactlyElementsOf(a.glue(aX))

        private val containsExactlyElementsOfNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Iterable<Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsExactlyElementsOf

        private fun getContainsNullableShortcutPair() = Pair(containsExactlyElementsOfNullableShortcutFun.name, Companion::containsExactlyElementsOfNullableShortcut)

        private fun containsExactlyElementsOfNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.containsExactlyElementsOf(a.glue(aX))

    }
}

