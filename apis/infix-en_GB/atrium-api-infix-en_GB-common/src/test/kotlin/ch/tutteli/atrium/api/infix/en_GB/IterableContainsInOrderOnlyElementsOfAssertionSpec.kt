package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.iterableLikeTypes
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KFunction2

class IterableContainsInOrderOnlyElementsOfAssertionSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)

    // TODO remove with 1.0.0
    describe("contains o inGiven order and only elementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) contains o inGiven order and only elementsOf listOf()
            }.toThrow<IllegalArgumentException>()
        }
    }

    // TODO remove with 1.0.0
    describe("containsExactlyElementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) containsExactlyElementsOf listOf()
            }.toThrow<IllegalArgumentException>()
        }
    }

    iterableLikeTypes.forEach { (description, input) ->
        describe(description) {
            it("passing an empty iterable throws an IllegalArgumentException") {
                expect {
                    expect(listOf(1, 2)) contains o inGiven order and only elementsOf input
                }.toThrow<IllegalArgumentException>()
            }
        }
    }

    iterableLikeTypes.forEach { (description, input) ->
        describe(description) {
            it("passing an empty iterable throws an IllegalArgumentException") {
                expect {
                    expect(listOf(1, 2)) containsExactlyElementsOf input
                }.toThrow<IllegalArgumentException>()
            }
        }
    }

    describe("contains o inGiven order and only elementsOf") {
        it("passing a string instead of an IterableLike throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) contains o inGiven order and only elementsOf "test"
            }.toThrow<IllegalArgumentException> { it messageContains ("toVarArg accepts arguments of types Iterable, Sequence, Array") }
        }
    }

    describe("containsExactlyElementsOf") {
        it("passing a string instead of an IterableLike throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) containsExactlyElementsOf "test"
            }.toThrow<IllegalArgumentException> { it messageContains ("toVarArg accepts arguments of types Iterable, Sequence, Array") }
        }
    }
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyValuesAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", ">> ", "=> ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains $filler $inOrder $andOnly $inOrderElementsOf" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect contains o inGiven order and only elementsOf listOf(a, *aX)

        fun getContainsNullablePair() =
            "$contains $filler $inOrder $andOnly $inOrderElementsOf" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect contains o inGiven order and only elementsOf listOf(a, *aX)

        private val containsExactlyElementsOfShortcutFun: KFunction2<Expect<Iterable<Double>>, Iterable<Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsExactlyElementsOf

        private fun getContainsShortcutPair() = containsExactlyElementsOfShortcutFun.name to Companion::containsExactlyElementsOfShortcut

        private fun containsExactlyElementsOfShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect containsExactlyElementsOf listOf(a, *aX)

        private val containsExactlyElementsOfNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Iterable<Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsExactlyElementsOf

        private fun getContainsNullableShortcutPair() = containsExactlyElementsOfNullableShortcutFun.name to Companion::containsExactlyElementsOfNullableShortcut;

        private fun containsExactlyElementsOfNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect containsExactlyElementsOf listOf(a, *aX)

    }
}

