package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KFunction3

class IterableContainsInOrderOnlyValuesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

    describe("elementsOf") {
        it("passing an empty iterable throws an IllegalArgumentException") {
            expect {
                expect(listOf(1, 2)).contains.inOrder.only.elementsOf(listOf())
            }.toThrow<IllegalArgumentException>()
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
        fun getContainsPair() = "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.contains.inOrder.only.value(a)
            else expect.contains.inOrder.only.values(a, *aX)

        fun getContainsNullablePair() =
            "$contains.$inOrder.$only.$inOrderOnlyValues" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.contains.inOrder.only.value(a)
            else expect.contains.inOrder.only.values(a, *aX)

        private val containsShortcutFun: KFunction3<Expect<Iterable<Double>>, Double, Array<out Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsExactly

        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyValuesShortcut

        private fun containsInOrderOnlyValuesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.containsExactly(a)
            else expect.containsExactly(a, *aX)

        private val containsNullableShortcutFun: KFunction3<Expect<Iterable<Double?>>, Double?, Array<out Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsExactly

        fun getContainsNullableShortcutPair() =
            containsNullableShortcutFun.name to Companion::containsInOrderOnlyNullableValuesShortcut

        private fun containsInOrderOnlyNullableValuesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.containsExactly(a)
            else expect.containsExactly(a, *aX)
    }
}

