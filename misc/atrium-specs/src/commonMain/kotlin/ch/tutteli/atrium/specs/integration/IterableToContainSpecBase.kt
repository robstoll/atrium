package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.notToContain
import ch.tutteli.atrium.api.fluent.en_GB.regex
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.specs.lineSeparator
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.uncheckedToNonNullable
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionBasic
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCollectionProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.specs.f
import ch.tutteli.atrium.specs.g
import ch.tutteli.atrium.specs.x
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

        val toContainInAnyOrder = DescriptionIterableLikeProof.IN_ANY_ORDER.string.format(
            DescriptionIterableLikeProof.TO_CONTAIN.string
        )
        val toContainInAnyOrderOnly = DescriptionIterableLikeProof.IN_ANY_ORDER_ONLY.string.format(
            DescriptionIterableLikeProof.TO_CONTAIN.string
        )
        val toContainInOrderOnly = DescriptionIterableLikeProof.IN_ORDER_ONLY.string.format(
            DescriptionIterableLikeProof.TO_CONTAIN.string
        )
        val toContainInOrderOnlyGrouped = DescriptionIterableLikeProof.IN_ORDER_ONLY_GROUPED.string.format(
            DescriptionIterableLikeProof.TO_CONTAIN.string
        )
        val numberOfSuchElements = DescriptionIterableLikeProof.NUMBER_OF_SUCH_ELEMENTS.string
        val additionalElements = DescriptionIterableLikeProof.WARNING_ADDITIONAL_ELEMENTS.string
        val mismatches = DescriptionIterableLikeProof.WARNING_MISMATCHES.string
        val mismatchesAdditionalElements =
            DescriptionIterableLikeProof.WARNING_MISMATCHES_ADDITIONAL_ELEMENTS.string
        val sizeExceeded = DescriptionIterableLikeProof.SIZE_EXCEEDED.string
        val anElementWhichEquals = DescriptionIterableLikeProof.AN_ELEMENT_WHICH_EQUALS.string
        val toHaveDescr = DescriptionBasic.TO_HAVE.string
        val aNextElement = DescriptionIterableLikeProof.A_NEXT_ELEMENT.string
        val notToContainDescr = DescriptionIterableLikeProof.NOT_TO_CONTAIN.string
        val noSuchValueDescr = DescriptionIterableLikeProof.ELEMENT_NOT_FOUND.string

        val sizeDescr = DescriptionCollectionProof.SIZE.string
        val atLeastDescr = DescriptionIterableLikeProof.AT_LEAST.string
        val atMostDescr = DescriptionIterableLikeProof.AT_MOST.string

        val fluentEmpty = { sequenceOf<Double>().constrainOnce().asIterable() }
        val illegalArgumentException = IllegalArgumentException::class.simpleName

        val emptyInOrderOnlyReportOptions: InOrderOnlyReportingOptions.() -> Unit = {}
        val emptyInAnyOrderOnlyReportOptions: InAnyOrderOnlyReportingOptions.() -> Unit = {}

        fun Expect<String>.toContainSize(actual: Int, expected: Int) =
            toContain.exactly(1).regex("$g$f${DescriptionCollectionProof.SIZE.string}\\s+: $actual$lineSeparator\\s+$x${DescriptionAnyProof.TO_EQUAL.string}\\s+: $expected")

        fun Expect<String>.notToContainElement(index: Int, expected: Double): Expect<String> =
            notToContain.regex("\\Q${elementWithIndex(index)} : ${expected}\\E.*$lineSeparator")

        fun Suite.describeFun(spec: SpecPair<*>, body: Suite.() -> Unit) = describeFun(spec.name, body)
        private fun Suite.describeFun(funName: String, body: Suite.() -> Unit) = context("fun `$funName`", body = body)

        fun Root.nullableCases(describePrefix: String, body: Suite.() -> Unit) {
            describe("$describePrefix nullable cases", body = body)
        }

        fun elementWithIndex(index: Int) =
            DescriptionIterableLikeProof.ELEMENT_WITH_INDEX.string.format(index)

        fun index(index: Int) = DescriptionIterableLikeProof.INDEX.string.format(index)
        fun index(fromIndex: Int, toIndex: Int) =
            DescriptionIterableLikeProof.INDEX_FROM_TO.string.format(fromIndex, toIndex)

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
