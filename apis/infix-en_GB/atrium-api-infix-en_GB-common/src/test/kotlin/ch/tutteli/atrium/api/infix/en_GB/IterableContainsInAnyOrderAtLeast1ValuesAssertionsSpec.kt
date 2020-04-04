package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction2

class IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
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
            "$contains $filler $inAnyOrder $atLeast 1 $inAnyOrderValues" to Companion::containsValues

        private fun containsValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect contains o inAny order atLeast 1 value a
            else expect contains o inAny order atLeast 1 the values(a, *aX)

        fun getContainsNullablePair() =
            "$contains $filler $inAnyOrder $atLeast 1 $inAnyOrderValues" to Companion::containsNullableValues

        private fun containsNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect contains o inAny order atLeast 1 value a
            else expect contains o inAny order atLeast 1 the values(a, *aX)


        private val containsFun: KFunction2<Expect<Iterable<Double>>, Double, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::contains

        fun getContainsShortcutPair() = containsFun.name to Companion::containsValuesShortcut

        private fun containsValuesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect contains a
            else expect contains values(a, *aX)


        private val containsNullableFun: KFunction2<Expect<Iterable<Double?>>, Double?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::contains

        fun getContainsNullableShortcutPair() = containsNullableFun.name to Companion::containsNullableValuesShortcut

        private fun containsNullableValuesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect contains a
            else expect contains values(a, *aX)

    }
}
