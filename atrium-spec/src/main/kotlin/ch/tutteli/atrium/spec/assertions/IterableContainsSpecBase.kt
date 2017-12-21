package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.exactly
import ch.tutteli.atrium.api.cc.en_UK.regex
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec

abstract class IterableContainsSpecBase(spec: Spec.() -> Unit) : Spek(spec) {

    companion object {
        val oneToSeven = listOf(1.0, 2.0, 4.0, 4.0, 5.0, 3.0, 5.0, 6.0, 4.0, 7.0)

        val containsInAnyOrder = String.format(DescriptionIterableAssertion.IN_ANY_ORDER.getDefault(), DescriptionIterableAssertion.CONTAINS.getDefault())
        val containsInAnyOrderOnly = String.format(DescriptionIterableAssertion.IN_ANY_ORDER_ONLY.getDefault(), DescriptionIterableAssertion.CONTAINS.getDefault())
        val containsInOrderOnly = String.format(DescriptionIterableAssertion.IN_ORDER_ONLY.getDefault(), DescriptionIterableAssertion.CONTAINS.getDefault())
        val numberOfOccurrences = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES.getDefault()
        val additionalEntries = DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES.getDefault()
        val mismatches = DescriptionIterableAssertion.WARNING_MISMATCHES.getDefault()
        val mismatchesAdditionalEntries = DescriptionIterableAssertion.WARNING_MISMATCHES_ADDITIONAL_ENTRIES.getDefault()

        val atLeast = DescriptionIterableAssertion.AT_LEAST.getDefault()
        val atMost = DescriptionIterableAssertion.AT_MOST.getDefault()

        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = System.getProperty("line.separator")!!

        fun AssertionPlant<CharSequence>.containsSize(actual:Int, expected: Int)
            = contains.exactly(1).regex("size: $actual[^:]+: $expected")
    }
}
