package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.dsl.Root

abstract class IterableToContainEntriesSpecBase(
    spec: Root.() -> Unit
) : IterableToContainSpecBase(spec) {
    init {
        toBeLessThanFun = Expect<Double>::toBeLessThan.name
        toBeGreaterThanFun = Expect<Double>::toBeGreaterThan.name
        toEqualFun = fun1<Double, Double>(Expect<Double>::toEqual).name
    }

    companion object {
        var toBeLessThanFun = ""
        var toBeGreaterThanFun = ""
        var toEqualFun = ""
        val anElementWhich = DescriptionIterableAssertion.AN_ELEMENT_WHICH.getDefault()
        val toBeLessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
        val toBeGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()
        val hasDescriptionBasic = DescriptionBasic.HAS.getDefault()
        val nextElement = DescriptionIterableAssertion.NEXT_ELEMENT.getDefault()
        val mismatches = DescriptionIterableAssertion.WARNING_MISMATCHES.getDefault()
        fun <T> mismatchedIndex(index: Int, value: T) : String {
            val index = String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)
            return "$index: ${value.toString()}"
        }

        fun index(index: Int) = String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)

        //@formatter:off
        val featureSuccess = "$indentRootBulletPoint$indentListBulletPoint\\Q$successfulBulletPoint$featureArrow\\E"
        val featureFailing = "$indentRootBulletPoint$indentListBulletPoint\\Q$failingBulletPoint$featureArrow\\E"
        val isAfterFailing = "$indentRootBulletPoint$indentListBulletPoint$indentFailingBulletPoint$indentFeatureArrow\\Q$featureBulletPoint\\E$isDescr"
        val isAfterSuccess = "$indentRootBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow\\Q$featureBulletPoint\\E$isDescr"
        val afterExplanatory = "$indentRootBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint\\Q$explanatoryBulletPoint\\E"
        val afterExplanatoryIndent = "$indentRootBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint"
        val afterMismatchedWarning = "$afterExplanatoryIndent$indentWarningBulletPoint\\Q$listBulletPoint\\E"
        val hasANextElement = "\\Q$rootBulletPoint\\E$hasDescriptionBasic: $nextElement"
        //@formatter:on

    }
}
