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
     * Notice, this method will change signature with 1.0.0, representationProvider will change to `representation: Any`
     *
     * @param assertionVerb The assertion verb which will be used in reporting.
     * @param representationProvider Provides the representation of the subject for which the [assertions]
     *   have been created.
     * @param assertions The [assertions] which are checked.
     *
     * @throws AssertionError An implementation is allowed to throw [AssertionError] if an assertion fails.
     */
    //TODO replace representationProvider with representation: Any => one can use LazyRepresentation
    fun check(assertionVerb: Translatable, representationProvider: () -> Any, assertions: List<Assertion>)
}
