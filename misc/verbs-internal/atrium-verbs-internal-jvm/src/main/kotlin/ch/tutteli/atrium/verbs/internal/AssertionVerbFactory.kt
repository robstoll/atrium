package ch.tutteli.atrium.verbs.internal

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Only required if you implement a custom component (for instance an own [Reporter], [ObjectFormatter] etc.)
 * or an own assertion function API (e.g., atrium-api-cc-en_GB in a different language)
 * and you want to reuse a specification from atrium-spec to test your custom component against it.
 */
object AssertionVerbFactory : ch.tutteli.atrium.spec.AssertionVerbFactory {

    override fun <T : Any> checkImmediately(subject: T) = assert(subject)
    override fun <T : Any> checkLazily(subject: T, assertionCreator: Assert<T>.() -> Unit): AssertionPlant<T>
        = assert(subject, assertionCreator)

    override fun <T> checkNullable(subject: T) = assert(subject)
    override fun checkException(act: () -> Unit) = expect(act)
}
