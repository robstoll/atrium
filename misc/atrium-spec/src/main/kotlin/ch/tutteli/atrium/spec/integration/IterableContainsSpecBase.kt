@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.exactly
import ch.tutteli.atrium.api.cc.en_GB.regex
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec
import org.jetbrains.spek.api.dsl.SpecBody

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class IterableContainsSpecBase(spec: Spec.() -> Unit) : Spek(spec) {

    companion object {
        val oneToFour = listOf(1.0, 2.0, 3.0, 4.0, 4.0)
        val oneToSeven = listOf(1.0, 2.0, 4.0, 4.0, 5.0, 3.0, 5.0, 6.0, 4.0, 7.0)
        val oneToSevenNullable = listOf(1.0, null, 4.0, 4.0, 5.0, null, 5.0, 6.0, 4.0, 7.0)

        val containsInAnyOrder = String.format(DescriptionIterableAssertion.IN_ANY_ORDER.getDefault(), DescriptionIterableAssertion.CONTAINS.getDefault())
        val containsInAnyOrderOnly = String.format(DescriptionIterableAssertion.IN_ANY_ORDER_ONLY.getDefault(), DescriptionIterableAssertion.CONTAINS.getDefault())
        val containsInOrderOnly = String.format(DescriptionIterableAssertion.IN_ORDER_ONLY.getDefault(), DescriptionIterableAssertion.CONTAINS.getDefault())
        val containsInOrderOnlyGrouped = String.format(DescriptionIterableAssertion.IN_ORDER_ONLY_GROUPED.getDefault(), DescriptionIterableAssertion.CONTAINS.getDefault())
        val numberOfOccurrences = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES.getDefault()
        val additionalEntries = DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES.getDefault()
        val mismatches = DescriptionIterableAssertion.WARNING_MISMATCHES.getDefault()
        val mismatchesAdditionalEntries = DescriptionIterableAssertion.WARNING_MISMATCHES_ADDITIONAL_ENTRIES.getDefault()
        val sizeExceeded = DescriptionIterableAssertion.SIZE_EXCEEDED.getDefault()
        val entryWithIndex = DescriptionIterableAssertion.ENTRY_WITH_INDEX.getDefault()
        val anEntryWhichIs = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS.getDefault()

        val atLeast = DescriptionIterableAssertion.AT_LEAST.getDefault()
        val atMost = DescriptionIterableAssertion.AT_MOST.getDefault()

        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = System.getProperty("line.separator")!!

        fun Assert<CharSequence>.containsSize(actual: Int, expected: Int)
            = contains.exactly(1).regex("size: $actual[^:]+: $expected")

        fun SpecBody.describeFun(funName: String, body: SpecBody.() -> Unit)
            = group("fun `$funName`", body = body)

        fun SpecBody.nullableCases(describePrefix: String, body: SpecBody.() -> Unit) {
            group("$describePrefix describe nullable cases", body = body)
        }

        fun SpecBody.nonNullableCases(
            describePrefix: String,
            containsPair: Pair<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Assert<Iterable<Double>>>,
            containsNullablePair: Pair<String, Assert<Iterable<Double?>>.(Double?, Array<out Double?>) -> Assert<Iterable<Double?>>>,
            action: SpecBody.(Assert<Iterable<Double>>.(Double, Array<out Double>) -> Any) -> Unit
        ) {
            group("$describePrefix describe non-nullable cases") {
                mapOf<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Any>(
                    containsPair.first to { a, aX -> containsPair.second(this, a, aX) },
                    containsNullablePair.first to { a, aX -> containsNullablePair.second(this, a, aX) }
                ).forEach { (describe, containsEntriesFunArr) ->
                    describeFun(describe) {
                        action(containsEntriesFunArr)
                    }
                }
            }
        }
    }
}
