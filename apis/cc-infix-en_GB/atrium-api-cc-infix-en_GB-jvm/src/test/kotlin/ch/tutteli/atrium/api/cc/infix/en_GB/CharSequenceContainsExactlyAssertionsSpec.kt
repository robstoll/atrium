// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.case
import ch.tutteli.atrium.api.infix.en_GB.contains
import ch.tutteli.atrium.api.infix.en_GB.exactly
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asExpect

//TODO remove with 1.0.0, no need to migrate to Spek 2
class CharSequenceContainsExactlyAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsExactlyAssertionsSpec(
    AssertionVerbFactory,
    getExactlyTriple(),
    getExactlyIgnoringCaseTriple(),
    getContainsNotPair(),
    "◆ ", "⚬ "
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getExactlyTriple() = Triple(
            "$toContain $exactly",
            { what: String, times: String -> "$toContain $what $exactly $times" },
            Companion::containsExactly
        )

        private fun containsExactly(plant: Assert<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>)
            = (plant.asExpect().contains(o)).exactly(exactly) the Values(a, *aX)

        private fun getExactlyIgnoringCaseTriple() = Triple(
            "$toContain $ignoringCase $exactly",
            { what: String, times: String -> "$toContain $ignoringCase $what $exactly $times" },
            Companion::containsExactlyIgnoringCase
        )

        private fun containsExactlyIgnoringCase(plant: Assert<CharSequence>, exactly: Int, a: Any, aX: Array<out Any>)
            = plant.asExpect().contains(o) ignoring case exactly exactly the Values(a, *aX)


        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use $containsNotValues instead of `$exactly $times`"

    }
}
