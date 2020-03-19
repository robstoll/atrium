package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.regex
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe

abstract class IterableContainsSpecBase(spec: Root.() -> Unit) : Spek(spec) {

    companion object {
        val oneToFour = { sequenceOf(1.0, 2.0, 3.0, 4.0, 4.0).constrainOnce().asIterable() }
        val oneToSeven = { sequenceOf(1.0, 2.0, 4.0, 4.0, 5.0, 3.0, 5.0, 6.0, 4.0, 7.0).constrainOnce().asIterable() }
        val oneToSevenNullable = { sequenceOf(1.0, null, 4.0, 4.0, 5.0, null, 5.0, 6.0, 4.0, 7.0).constrainOnce().asIterable() }

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

        val fluentEmpty = { sequenceOf<Double>().constrainOnce().asIterable() }
        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = lineSeperator

        fun Expect<String>.containsSize(actual: Int, expected: Int) =
            contains.exactly(1).regex("size: $actual[^:]+: $expected")

        fun Suite.describeFun(spec: SpecPair<*>, body: Suite.() -> Unit) = describeFun(spec.name, body)
        private fun Suite.describeFun(funName: String, body: Suite.() -> Unit) = context("fun `$funName`", body = body)

        fun Root.nullableCases(describePrefix: String, body: Suite.() -> Unit) {
            describe("$describePrefix nullable cases", body = body)
        }

        fun <F> Root.nonNullableCases(
            describePrefix: String,
            nonNullableFun: SpecPair<F>,
            nullableFun: Any,
            action: Suite.(F) -> Unit
        ) {
            describe("$describePrefix non-nullable cases") {
                val functions = uncheckedToNonNullable(nonNullableFun, nullableFun)
                functions.forEach { (name, funArr) ->
                    describeFun(name) {
                        action(funArr)
                    }
                }
            }
        }
    }
}
