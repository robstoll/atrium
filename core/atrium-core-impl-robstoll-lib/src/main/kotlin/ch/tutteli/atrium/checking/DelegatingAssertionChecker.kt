package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.InvisibleAssertionGroup
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IBaseAssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * An [IAssertionChecker] useful for narrowing assertion functions which have to create an own [IAssertionPlant]
 * but want to add the created assertions to the original plant (the [subjectPlant]) of the narrowed subject.
 *
 * @param T The type of the [subject][IAssertionPlant.subject] of the given [subjectPlant].
 *
 * @property subjectPlant The plant which holds the assertions of the subject.
 *
 * @constructor An [IAssertionChecker] useful for narrowing assertion functions which have to create an own [IAssertionPlant]
 * but want to add the created assertions to the original plant of the narrowed [IAssertionPlant.subject].
 * @param subjectPlant The plant which holds the assertions of the subject.
 */
class DelegatingAssertionChecker<out T : Any?>(private val subjectPlant: IBaseAssertionPlant<T, *>) : IAssertionChecker {

    /**
     * [Adds][IAssertionPlant.addAssertion] the given [assertions] (wrapped into an [InvisibleAssertionGroup]) to the
     * original plant of the subject (the [subjectPlant]).
     *
     * @param assertionVerb Is ignored.
     * @param subject Is ignored.
     * @param assertions The assertions which shall be added to the original plant of the subject (the [subjectPlant]).
     *
     * @throws AssertionError Might throw an [AssertionError] in case one of the given [assertions] does not hold.
     */
    override fun check(assertionVerb: Translatable, subject: Any, assertions: List<IAssertion>) {
        subjectPlant.addAssertion(InvisibleAssertionGroup(assertions))
    }
}
