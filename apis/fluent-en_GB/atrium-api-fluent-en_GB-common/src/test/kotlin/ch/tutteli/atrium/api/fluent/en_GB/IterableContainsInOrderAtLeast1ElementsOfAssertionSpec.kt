package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import kotlin.reflect.KFunction3

class IterableContainsInOrderAtLeast1ElementsOfAssertionSpec : Spek({
    include(BuilderSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.IterableContainsInOrderAtLeast1ValuesAssertionsSpec  (
        getContainsPair(),
        getContainsNullablePair(),
        "◆ ",
        "[Atrium][Builder] "
    )

    companion object : IterableContainsSpecBase() {
        fun getContainsPair() = "$contains.$inOrder.$elementsOf" to Companion::containsInOrderOnlyValues

        private fun containsInOrderOnlyValues(
            plant: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> = plant.contains.inOrder.only.elementsOf(listOf(a, *aX))

        fun getContainsNullablePair() =
            "$contains.$inOrder.$elementsOf" to Companion::containsInOrderOnlyNullableValues

        private fun containsInOrderOnlyNullableValues(
            plant: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> = plant.contains.inOrder.only.elementsOf(listOf(a, *aX))

    }
}

