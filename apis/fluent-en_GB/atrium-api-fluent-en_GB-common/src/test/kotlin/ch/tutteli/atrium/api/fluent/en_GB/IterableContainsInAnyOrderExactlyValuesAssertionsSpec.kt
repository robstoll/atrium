package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class IterableContainsInAnyOrderExactlyValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderExactlyValuesAssertionsSpec(
        AssertionVerbFactory,
        getExactlyTriple(),
        getContainsNotPair(),
        "◆ "
    ) {

    companion object : IterableContainsSpecBase() {

        private fun getExactlyTriple() =
            { what: String, times: String -> "$contains $what $exactly $times" } to
                ("$contains.$inAnyOrder.$exactly" to Companion::containsExactly)

        private fun containsExactly(
            plant: Expect<Iterable<Double>>,
            exactly: Int,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) plant.contains.inAnyOrder.exactly(exactly).value(a)
            else plant.contains.inAnyOrder.exactly(exactly).values(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use $containsNot instead of $exactly($times)"

    }
}
