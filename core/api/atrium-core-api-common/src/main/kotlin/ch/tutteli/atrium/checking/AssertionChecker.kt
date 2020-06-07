package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Checks given [Assertion]s and reports if one of them fails.
 */
@Deprecated("Do no longer use AssertionCheckers, use a specialised Expect instead; e.g. DelegatingExpect, FeatureExpect; will be removed with 1.0.0")
interface AssertionChecker {

    /**
     * Checks given [assertions] and reports if one of them fails (does not hold).
     *
     * @param assertionVerb The assertion verb which will be used in reporting.
     * @param representation The representation of the subject for which the [assertions]
     *   have been created.
     * @param assertions The [assertions] which shall be checked.
     *
     * @throws AssertionError An implementation is allowed to throw [AssertionError] if an assertion fails.
     */
    fun check(assertionVerb: Translatable, representation: Any?, assertions: List<Assertion>)

    @Deprecated(
        "Use the overload which expects a representation instead of a representationProvider, use LazyRepresentation if needed; will be removed with 1.0.0",
        ReplaceWith("check(assertionVerb, LazyRepresentation(representationProvider), assertions)")
    )
    fun check(assertionVerb: Translatable, representationProvider: () -> Any, assertions: List<Assertion>) =
        check(assertionVerb, LazyRepresentation(representationProvider), assertions)
}
