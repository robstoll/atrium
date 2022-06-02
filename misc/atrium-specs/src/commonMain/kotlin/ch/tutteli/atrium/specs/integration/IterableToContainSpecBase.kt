package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.notToContain
import ch.tutteli.atrium.api.fluent.en_GB.regex
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionExpectation
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe

abstract class IterableToContainSpecBase(spec: Root.() -> Unit) : Spek(spec) {

    companion object {
        val oneToFour = { sequenceOf(1.0, 2.0, 3.0, 4.0, 4.0).constrainOnce().asIterable() }
        val oneToEleven = (1..11).map { it.toDouble() }.asIterable()
        val oneToSeven = { sequenceOf(1.0, 2.0, 4.0, 4.0, 5.0, 3.0, 5.0, 6.0, 4.0, 7.0).constrainOnce().asIterable() }
        val oneToSevenNullable =
            { sequenceOf(1.0, null, 4.0, 4.0, 5.0, null, 5.0, 6.0, 4.0, 7.0).constrainOnce().asIterable() }

        val toContainInAnyOrder = DescriptionIterableLikeExpectation.IN_ANY_ORDER.getDefault().format(
            DescriptionIterableLikeExpectation.TO_CONTAIN.getDefault()
        )
        val toContainInAnyOrderOnly = DescriptionIterableLikeExpectation.IN_ANY_ORDER_ONLY.getDefault().format(
            DescriptionIterableLikeExpectation.TO_CONTAIN.getDefault()
        )
        val toContainInOrderOnly = DescriptionIterableLikeExpectation.IN_ORDER_ONLY.getDefault().format(
            DescriptionIterableLikeExpectation.TO_CONTAIN.getDefault()
        )
        val toContainInOrderOnlyGrouped = DescriptionIterableLikeExpectation.IN_ORDER_ONLY_GROUPED.getDefault().format(
            DescriptionIterableLikeExpectation.TO_CONTAIN.getDefault()
        )
        val numberOfSuchElements = DescriptionIterableLikeExpectation.NUMBER_OF_SUCH_ELEMENTS.getDefault()
        val additionalElements = DescriptionIterableLikeExpectation.WARNING_ADDITIONAL_ELEMENTS.getDefault()
        val mismatches = DescriptionIterableLikeExpectation.WARNING_MISMATCHES.getDefault()
        val mismatchesAdditionalElements =
            DescriptionIterableLikeExpectation.WARNING_MISMATCHES_ADDITIONAL_ELEMENTS.getDefault()
        val sizeExceeded = DescriptionIterableLikeExpectation.SIZE_EXCEEDED.getDefault()
        val anElementWhichEquals = DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_EQUALS.getDefault()
        val toHaveDescr = DescriptionBasic.TO_HAVE.getDefault()
        val aNextElement = DescriptionIterableLikeExpectation.A_NEXT_ELEMENT.getDefault()
        val notToContainDescr = DescriptionIterableLikeExpectation.NOT_TO_CONTAIN.getDefault()
        val noSuchValueDescr = DescriptionIterableLikeExpectation.ELEMENT_NOT_FOUND.getDefault()

        val sizeDescr = DescriptionCollectionExpectation.SIZE.getDefault()
        val atLeastDescr = DescriptionIterableLikeExpectation.AT_LEAST.getDefault()
        val atMostDescr = DescriptionIterableLikeExpectation.AT_MOST.getDefault()

        val fluentEmpty = { sequenceOf<Double>().constrainOnce().asIterable() }
        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = lineSeparator

        val emptyInOrderOnlyReportOptions: InOrderOnlyReportingOptions.() -> Unit = {}
        val emptyInAnyOrderOnlyReportOptions: InAnyOrderOnlyReportingOptions.() -> Unit = {}

        fun Expect<String>.toContainSize(actual: Int, expected: Int) =
            toContain.exactly(1).regex("${DescriptionCollectionExpectation.SIZE.getDefault()}: $actual[^:]+: $expected")

        fun Expect<String>.notToContainElement(index: Int, expected: Double): Expect<String> =
            notToContain.regex("\\Q${elementWithIndex(index)}: ${expected}\\E.*$separator")

        fun Suite.describeFun(spec: SpecPair<*>, body: Suite.() -> Unit) = describeFun(spec.name, body)
        private fun Suite.describeFun(funName: String, body: Suite.() -> Unit) = context("fun `$funName`", body = body)

        fun Root.nullableCases(describePrefix: String, body: Suite.() -> Unit) {
            describe("$describePrefix nullable cases", body = body)
        }

        fun elementWithIndex(index: Int) =
            DescriptionIterableLikeExpectation.ELEMENT_WITH_INDEX.getDefault().format(index)

        fun index(index: Int) = DescriptionIterableLikeExpectation.INDEX.getDefault().format(index)
        fun index(fromIndex: Int, toIndex: Int) =
            DescriptionIterableLikeExpectation.INDEX_FROM_TO.getDefault().format(fromIndex, toIndex)

        fun <F> Root.nonNullableCases(
            describePrefix: String,
            nonNullableFun: SpecPair<F>,
            nullableFun: Any,
            vararg otherFun: Any = arrayOf(),
            action: Suite.(F) -> Unit
        ) {
            describe("$describePrefix non-nullable cases") {
                @Suppress("UNCHECKED_CAST")
                val functions: List<SpecPair<F>> = uncheckedToNonNullable(nonNullableFun, nullableFun) +
                    (otherFun.toList() as List<SpecPair<F>>)

                functions.forEach { (name, funArr) ->
                    describeFun(name) {
                        action(funArr)
                    }
                }
            }
        }
    }
}
