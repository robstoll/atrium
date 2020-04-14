package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.kbox.glue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KFunction2

class IterableContainsInAnyOrderAtLeast1ElementsOfAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)

    describe("contains.inAnyOrder.atLeast(1).elementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)).contains.inAnyOrder.atLeast(1).elementsOf(listOf())
            }.toThrow<IllegalArgumentException>()
        }
    }

    describe("containsElementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)).containsElementsOf(listOf())
            }.toThrow<IllegalArgumentException>()
        }
    }
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderElementsOf" to Companion::containsValues

        private fun containsValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.contains.inAnyOrder.atLeast(1).elementsOf(listOf(a, *aX))

        fun getContainsNullablePair() =
            "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderElementsOf" to Companion::containsNullableValues

        private fun containsNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.contains.inAnyOrder.atLeast(1).elementsOf(listOf(a, *aX))

        private val containsElementsOfShortcutFun: KFunction2<Expect<Iterable<Long>>, Iterable<Long>, Expect<Iterable<Long>>> =
            Expect<Iterable<Long>>::containsElementsOf

        private fun getContainsShortcutPair() = containsElementsOfShortcutFun.name to Companion::containsInAnyOrderShortcut

        private fun containsInAnyOrderShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = expect.containsElementsOf(a glue aX)

        private val containsElementsOfNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Iterable<Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsElementsOf

        private fun getContainsNullableShortcutPair() = containsElementsOfNullableShortcutFun.name to Companion::containsInAnyOrderNullableShortcut;

        private fun containsInAnyOrderNullableShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = expect.containsElementsOf(a glue aX)

    }
}
