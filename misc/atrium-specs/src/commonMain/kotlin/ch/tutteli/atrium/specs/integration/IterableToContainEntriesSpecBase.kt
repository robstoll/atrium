package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionIterableLikeProof
import ch.tutteli.atrium.specs.*
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
        val anElementWhichNeedsDescr = DescriptionIterableLikeProof.AN_ELEMENT_WHICH_NEEDS.string
        val toBeLessThanDescr = DescriptionComparableProof.TO_BE_LESS_THAN.string
        val toBeGreaterThanDescr = DescriptionComparableProof.TO_BE_GREATER_THAN.string
        fun <T> mismatchedIndex(index: Int, value: T) : String {
            val indexDescr = DescriptionIterableLikeProof.INDEX.string.format(index)
            //TODO 1.3.0 should be "$indexDescr :
            return "$indexDescr\\s+: ${value.toString()}"
        }
        val noSuchElementDescr = DescriptionIterableLikeProof.ELEMENT_NOT_FOUND.string

        fun index(index: Int) = DescriptionIterableLikeProof.INDEX.string.format(index)

        //@formatter:off
        val afterExplanatoryIndent = "$indentRoot$indentList$indentSuccess"
        val afterExplanatory = "$afterExplanatoryIndent\\Q$explanatoryBulletPoint\\E"
        val afterMismatchedWarning = "$afterExplanatoryIndent$indentBb\\Q$listBulletPoint\\E"
        //@formatter:on

    }
}
