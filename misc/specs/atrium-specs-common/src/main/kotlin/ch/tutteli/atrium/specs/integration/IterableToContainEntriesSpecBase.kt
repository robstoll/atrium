package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
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
        val toBeLessThanDescr = DescriptionComparableExpectation.TO_BE_LESS_THAN.getDefault()
        val toBeGreaterThanDescr = DescriptionComparableExpectation.TO_BE_GREATER_THAN.getDefault()
        fun <T> mismatchedIndex(index: Int, value: T) : String {
            val indexDescr = String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)
            return "$indexDescr: ${value.toString()}"
        }
        val noSuchEntryDescr = DescriptionIterableAssertion.ELEMENT_NOT_FOUND.getDefault()

        fun index(index: Int) = String.format(DescriptionIterableAssertion.INDEX.getDefault(), index)

        //@formatter:off
        val afterExplanatory = "$indentRootBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint\\Q$explanatoryBulletPoint\\E"
        val afterExplanatoryIndent = "$indentRootBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint"
        val afterMismatchedWarning = "$afterExplanatoryIndent$indentWarningBulletPoint\\Q$listBulletPoint\\E"
        val hasANextElement = "\\Q$rootBulletPoint\\E$toHaveDescr: $nextElement"
        //@formatter:on

    }
}
