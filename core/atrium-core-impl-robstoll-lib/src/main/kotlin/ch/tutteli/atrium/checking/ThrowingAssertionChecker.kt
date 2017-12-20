package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * An [IAssertionChecker] which throws [AssertionError]s in case an assertion fails
 * and uses the given [reporter] for reporting.
 *
 * @property reporter Will be used for reporting.
 *
 * @constructor An [IAssertionChecker] which throws [AssertionError]s in case an assertion fails
 *              and uses the given [reporter] for reporting.
 * @param reporter Will be used for reporting.
 */
class ThrowingAssertionChecker(private val reporter: Reporter) : IAssertionChecker {

    /**
     * Creates an [IAssertionGroup] -- based on the given [assertionVerb], [subject] and [assertions] --
     * formats it for reporting using the [reporter] and checks whether it holds.
     *
     * @param assertionVerb I used as [IAssertionGroup.name].
     * @param subject Is used as [IAssertionGroup.subject].
     * @param assertions Is used as [IAssertionGroup.assertions].
     *
     * @throws AssertionError In case the created [IAssertionGroup] does not hold.
     */
    override fun check(assertionVerb: Translatable, subject: Any, assertions: List<IAssertion>) {
        val assertionGroup = AssertionGroup(RootAssertionGroupType, assertionVerb, subject, assertions)
        val sb = StringBuilder()
        reporter.format(assertionGroup, sb)
        if (!assertionGroup.holds()) {
            throw AssertionError(sb.toString())
        }
    }

}
