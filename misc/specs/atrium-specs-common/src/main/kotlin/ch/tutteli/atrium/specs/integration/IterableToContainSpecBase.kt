package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.regex
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionExpectation
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe

abstract class IterableToContainSpecBase(spec: Root.() -> Unit) : Spek(spec) {

    companion object {
        val oneToFour = { sequenceOf(1.0, 2.0, 3.0, 4.0, 4.0).constrainOnce().asIterable() }
        val oneToSeven = { sequenceOf(1.0, 2.0, 4.0, 4.0, 5.0, 3.0, 5.0, 6.0, 4.0, 7.0).constrainOnce().asIterable() }
        val oneToSevenNullable =
            { sequenceOf(1.0, null, 4.0, 4.0, 5.0, null, 5.0, 6.0, 4.0, 7.0).constrainOnce().asIterable() }

        val toContainInAnyOrder = String.format(
            DescriptionIterableAssertion.IN_ANY_ORDER.getDefault(),
            DescriptionIterableAssertion.CONTAINS.getDefault()
        )
        val toContainInAnyOrderOnly = String.format(
            DescriptionIterableAssertion.IN_ANY_ORDER_ONLY.getDefault(),
            DescriptionIterableAssertion.CONTAINS.getDefault()
        )
        val toContainInOrderOnly = String.format(
            DescriptionIterableAssertion.IN_ORDER_ONLY.getDefault(),
            DescriptionIterableAssertion.CONTAINS.getDefault()
        )
        val toContainInOrderOnlyGrouped = String.format(
            DescriptionIterableAssertion.IN_ORDER_ONLY_GROUPED.getDefault(),
            DescriptionIterableAssertion.CONTAINS.getDefault()
        )
        val numberOfOccurrences = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES.getDefault()
        val additionalElements = DescriptionIterableAssertion.WARNING_ADDITIONAL_ELEMENTS.getDefault()
        val mismatches = DescriptionIterableAssertion.WARNING_MISMATCHES.getDefault()
        val mismatchesAdditionalElements = DescriptionIterableAssertion.WARNING_MISMATCHES_ADDITIONAL_ELEMENTS.getDefault()
        val sizeExceeded = DescriptionIterableAssertion.SIZE_EXCEEDED.getDefault()
        val anElementWhichIs = DescriptionIterableAssertion.AN_ELEMENT_WHICH_EQUALS.getDefault()
        val toHaveDescr = DescriptionBasic.TO_HAVE.getDefault()
        val nextElement = DescriptionIterableAssertion.NEXT_ELEMENT.getDefault()
        val notToContainDescr = DescriptionIterableAssertion.CONTAINS_NOT.getDefault()
        val noSuchValueDescr = DescriptionIterableAssertion.ELEMENT_NOT_FOUND.getDefault()

        val sizeDescr = DescriptionCollectionExpectation.SIZE.getDefault()
        val atLeastDescr = DescriptionIterableAssertion.AT_LEAST.getDefault()
        val atMostDescr = DescriptionIterableAssertion.AT_MOST.getDefault()

        val fluentEmpty = { sequenceOf<Double>().constrainOnce().asIterable() }
        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = lineSeparator

        val emptyInOrderOnlyReportOptions : InOrderOnlyReportingOptions.() -> Unit = {}

        fun Expect<String>.toContainSize(actual: Int, expected: Int) =
            toContain.exactly(1).regex("${DescriptionCollectionExpectation.SIZE.getDefault()}: $actual[^:]+: $expected")

        fun Suite.describeFun(spec: SpecPair<*>, body: Suite.() -> Unit) = describeFun(spec.name, body)
        private fun Suite.describeFun(funName: String, body: Suite.() -> Unit) = context("fun `$funName`", body = body)

        fun Root.nullableCases(describePrefix: String, body: Suite.() -> Unit) {
            describe("$describePrefix nullable cases", body = body)
        }

        fun elementWithIndex(index: Int) = DescriptionIterableAssertion.ELEMENT_WITH_INDEX.getDefault().format(index)

        fun index(index: Int) = DescriptionIterableAssertion.INDEX.getDefault().format(index)
        fun index(fromIndex: Int, toIndex: Int) =
            DescriptionIterableAssertion.INDEX_FROM_TO.getDefault().format(fromIndex, toIndex)

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
