package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.iterableLikeTypes
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KFunction2

class IterableContainsInAnyOrderAtLeast1ElementsOfAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)

    // TODO remove with 1.0.0
    describe("contains o inAny order atLeast 1 elementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) contains o inAny order atLeast 1 elementsOf listOf()
            }.toThrow<IllegalArgumentException>()
        }
    }

    // TODO remove with 1.0.0
    describe("containsElementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) containsElementsOf listOf()
            }.toThrow<IllegalArgumentException>()
        }
    }

    iterableLikeTypes.forEach { (description, input) ->
        describe(description) {
            it("passing an empty iterable throws an IllegalArgumentException") {
                expect {
                    expect(listOf(1, 2)) contains o inAny order atLeast 1 elementsOf input
                }.toThrow<IllegalArgumentException>()
            }
        }
    }

    iterableLikeTypes.forEach { (description, input) ->
        describe(description) {
            it("passing an empty iterable throws an IllegalArgumentException") {
                expect {
                    expect(listOf(1, 2)) containsElementsOf input
                }.toThrow<IllegalArgumentException>()
            }
        }
    }

    describe("contains o inAny order atLeast 1 elementsOf") {
        it("passing a string instead of an IterableLike throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) contains o inAny order atLeast 1 elementsOf "test"
            }.toThrow<IllegalArgumentException> { it messageContains ("toVarArg accepts arguments of types Iterable, Sequence, Array") }
        }
    }

    describe("containsElementsOf") {
        it("passing a string instead of an IterableLike throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)) containsElementsOf "test"
            }.toThrow<IllegalArgumentException> { it messageContains ("toVarArg accepts arguments of types Iterable, Sequence, Array") }
        }
    }

}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "* ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "* ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains $filler $inAnyOrder $atLeast 1 $inAnyOrderElementsOf" to Companion::containsValues

        private fun containsValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect contains o inAny order atLeast 1 elementsOf listOf(a, *aX)

        fun getContainsNullablePair() =
            "$contains $filler $inAnyOrder $atLeast 1 $inAnyOrderElementsOf" to Companion::containsNullableValues

        private fun containsNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect contains o inAny order atLeast 1 elementsOf listOf(a, *aX)

        private val containsElementsOfShortcutFun: KFunction2<Expect<Iterable<Double>>, Iterable<Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsElementsOf

        private fun getContainsShortcutPair() = containsElementsOfShortcutFun.name to Companion::containsInAnyOrderShortcut

        private fun containsInAnyOrderShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect containsElementsOf listOf(a, *aX)

        private val containsElementsOfNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Iterable<Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsElementsOf

        private fun getContainsNullableShortcutPair() = containsElementsOfNullableShortcutFun.name to Companion::containsInAnyOrderNullableShortcut;

        private fun containsInAnyOrderNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect containsElementsOf listOf(a, *aX)

    }
}
