package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableContainsInOrderOnlyValuesAssertionsSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

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
            "$contains $filler $inOrder $andOnly $inOrderOnlyValues" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect contains o inGiven order and only value a
            else expect contains o inGiven order and only the values(a, *aX)

        fun getContainsNullablePair() =
            "$contains $filler $inOrder $andOnly $inOrderOnlyValues" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect contains o inGiven order and only value a
            else expect contains o inGiven order and only the values(a, *aX)

        private val containsShortcutFun: KFunction2<Expect<Iterable<Double>>, Double, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::containsExactly

        fun getContainsShortcutPair() = containsShortcutFun.name to Companion::containsInOrderOnlyValuesShortcut

        private fun containsInOrderOnlyValuesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect containsExactly a
            else expect containsExactly values(a, *aX)

        private val containsNullableShortcutFun: KFunction2<Expect<Iterable<Double?>>, Double?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::containsExactly

        fun getContainsNullableShortcutPair() =
            containsNullableShortcutFun.name to Companion::containsInOrderOnlyNullableValuesShortcut

        private fun containsInOrderOnlyNullableValuesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect containsExactly a
            else expect containsExactly values(a, *aX)
    }
}

