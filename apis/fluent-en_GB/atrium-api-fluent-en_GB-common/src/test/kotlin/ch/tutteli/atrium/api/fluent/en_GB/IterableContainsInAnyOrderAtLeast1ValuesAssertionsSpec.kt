package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.include
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction3

class IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsShortcutPair(),
        getContainsNullableShortcutPair(),
        "◆ ",
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() = "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderValues" to Companion::containsValues

        private fun containsValues(
            plant: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) plant.contains.inAnyOrder.atLeast(1).value(a)
            else plant.contains.inAnyOrder.atLeast(1).values(a, *aX)

        fun getContainsNullablePair() =
            "$contains.$inAnyOrder.$atLeast(1).$inAnyOrderValues" to Companion::containsNullableValues

        private fun containsNullableValues(
            plant: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) plant.contains.inAnyOrder.atLeast(1).value(a)
            else plant.contains.inAnyOrder.atLeast(1).values(a, *aX)


        private val containsFun: KFunction3<Expect<Iterable<Double>>, Double, Array<out Double>, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::contains

        fun getContainsShortcutPair() = containsFun.name to Companion::containsValuesShortcut

        private fun containsValuesShortcut(
            plant: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) plant.contains(a)
            else plant.contains(a, *aX)


        private val containsNullableFun: KFunction3<Expect<Iterable<Double?>>, Double, Array<out Double?>, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::contains

        fun getContainsNullableShortcutPair() = containsNullableFun.name to Companion::containsNullableValuesShortcut

        private fun containsNullableValuesShortcut(
            plant: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) plant.contains(a)
            else plant.contains(a, *aX)

    }
}
