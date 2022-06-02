package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation
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
        val anElementWhichNeedsDescr = DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_NEEDS.getDefault()
        val toBeLessThanDescr = DescriptionComparableExpectation.TO_BE_LESS_THAN.getDefault()
        val toBeGreaterThanDescr = DescriptionComparableExpectation.TO_BE_GREATER_THAN.getDefault()
        fun <T> mismatchedIndex(index: Int, value: T) : String {
            val indexDescr = DescriptionIterableLikeExpectation.INDEX.getDefault().format(index)
            return "$indexDescr: ${value.toString()}"
        }
        val noSuchElementDescr = DescriptionIterableLikeExpectation.ELEMENT_NOT_FOUND.getDefault()

        fun index(index: Int) = DescriptionIterableLikeExpectation.INDEX.getDefault().format(index)

        //@formatter:off
        val afterExplanatoryIndent = "$indentRootBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint"
        val afterExplanatory = "$afterExplanatoryIndent\\Q$explanatoryBulletPoint\\E"
        val afterMismatchedWarning = "$afterExplanatoryIndent$indentWarningBulletPoint\\Q$listBulletPoint\\E"
        val hasANextElement = "\\Q$rootBulletPoint\\E$toHaveDescr: $aNextElement"
        //@formatter:on

    }
}
