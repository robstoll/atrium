package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Checks given [Assertion]s and reports if one of them fails.
 */
interface AssertionChecker {
    /**
     * Checks given [assertions] and reports if one of them fails (does not hold).
     *
     * @param assertionVerb The assertion verb which will be used in error reporting.
     * @param subjectProvider Provides the subject for which the [assertions] have been created.
     * @param assertions The [assertions] which are checked.
     *
     * @throws AssertionError An implementation is allowed to throw [AssertionError] if an assertion fails.
     */
    fun check(assertionVerb: Translatable, subjectProvider: () -> Any, assertions: List<Assertion>)
}
