package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.regex
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.format
import ch.tutteli.atrium.specs.lineSeperator
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe

abstract class IterableContainsSpecBase(spec: Root.() -> Unit) : Spek(spec) {

    companion object {
        val oneToFour = listOf(1.0, 2.0, 3.0, 4.0, 4.0).asIterable()
        val oneToSeven = listOf(1.0, 2.0, 4.0, 4.0, 5.0, 3.0, 5.0, 6.0, 4.0, 7.0).asIterable()
        val oneToSevenNullable = listOf(1.0, null, 4.0, 4.0, 5.0, null, 5.0, 6.0, 4.0, 7.0).asIterable()

        val containsInAnyOrder = String.format(
            DescriptionIterableAssertion.IN_ANY_ORDER.getDefault(),
            DescriptionIterableAssertion.CONTAINS.getDefault()
        )
        val containsInAnyOrderOnly = String.format(
            DescriptionIterableAssertion.IN_ANY_ORDER_ONLY.getDefault(),
            DescriptionIterableAssertion.CONTAINS.getDefault()
        )
        val containsInOrderOnly = String.format(
            DescriptionIterableAssertion.IN_ORDER_ONLY.getDefault(),
            DescriptionIterableAssertion.CONTAINS.getDefault()
        )
        val containsInOrderOnlyGrouped = String.format(
            DescriptionIterableAssertion.IN_ORDER_ONLY_GROUPED.getDefault(),
            DescriptionIterableAssertion.CONTAINS.getDefault()
        )
        val numberOfOccurrences = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES.getDefault()
        val additionalEntries = DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES.getDefault()
        val mismatches = DescriptionIterableAssertion.WARNING_MISMATCHES.getDefault()
        val mismatchesAdditionalEntries =
            DescriptionIterableAssertion.WARNING_MISMATCHES_ADDITIONAL_ENTRIES.getDefault()
        val sizeExceeded = DescriptionIterableAssertion.SIZE_EXCEEDED.getDefault()
        val entryWithIndex = DescriptionIterableAssertion.ENTRY_WITH_INDEX.getDefault()
        val anEntryWhichIs = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS.getDefault()

        val atLeast = DescriptionIterableAssertion.AT_LEAST.getDefault()
        val atMost = DescriptionIterableAssertion.AT_MOST.getDefault()

        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = lineSeperator

        fun Expect<String>.containsSize(actual: Int, expected: Int) =
            contains.exactly(1).regex("size: $actual[^:]+: $expected")

        fun Suite.describeFun(funName: String, body: Suite.() -> Unit) = context("fun `$funName`", body = body)

        fun Root.nullableCases(describePrefix: String, body: Suite.() -> Unit) {
            describe("$describePrefix describe nullable cases", body = body)
        }

        fun Root.nonNullableCases(
            describePrefix: String,
            containsPair: Pair<String, Expect<Iterable<Double>>.(Double, Array<out Double>) -> Expect<Iterable<Double>>>,
            containsNullablePair: Pair<String, Expect<Iterable<Double?>>.(Double?, Array<out Double?>) -> Expect<Iterable<Double?>>>,
            action: Suite.(Expect<Iterable<Double>>.(Double, Array<out Double>) -> Any) -> Unit
        ) {
            describe("$describePrefix describe non-nullable cases") {
                mapOf<String, Expect<Iterable<Double>>.(Double, Array<out Double>) -> Any>(
                    containsPair.first to { a, aX -> containsPair.second(this, a, aX) },
                    containsNullablePair.first to { a, aX ->
                        @Suppress("UNCHECKED_CAST")
                        containsNullablePair.second(this as Expect<Iterable<Double?>>, a, aX)
                    }
                ).forEach { (describe, containsEntriesFunArr) ->
                    describeFun(describe) {
                        action(containsEntriesFunArr)
                    }
                }
            }
        }
    }
}
