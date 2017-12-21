package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.InvisibleAssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * An [AssertionChecker] useful for narrowing assertion functions which have to create an own [AssertionPlant]
 * but want to add the created assertions to the original plant (the [subjectPlant]) of the narrowed subject.
 *
 * @param T The type of the [subject][AssertionPlant.subject] of the given [subjectPlant].
 *
 * @property subjectPlant The plant which holds the assertions of the subject.
 *
 * @constructor An [AssertionChecker] useful for narrowing assertion functions which have to create an own [AssertionPlant]
 * but want to add the created assertions to the original plant of the narrowed [AssertionPlant.subject].
 * @param subjectPlant The plant which holds the assertions of the subject.
 */
class DelegatingAssertionChecker<out T : Any?>(private val subjectPlant: BaseAssertionPlant<T, *>) : AssertionChecker {

    /**
     * [Adds][AssertionPlant.addAssertion] the given [assertions] (wrapped into an [InvisibleAssertionGroup]) to the
     * original plant of the subject (the [subjectPlant]).
     *
     * @param assertionVerb Is ignored.
     * @param subject Is ignored.
     * @param assertions The assertions which shall be added to the original plant of the subject (the [subjectPlant]).
     *
     * @throws AssertionError Might throw an [AssertionError] in case one of the given [assertions] does not hold.
     */
    override fun check(assertionVerb: Translatable, subject: Any, assertions: List<Assertion>) {
        subjectPlant.addAssertion(AssertionGroup.Builder.invisible.create(assertions))
    }
}
