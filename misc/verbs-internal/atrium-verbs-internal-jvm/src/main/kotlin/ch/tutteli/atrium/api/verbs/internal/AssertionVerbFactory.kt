package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Reporter

/**
 * Only required if you implement a custom component (for instance an own [Reporter], [ObjectFormatter] etc.)
 * or an own assertion function API (e.g., atrium-api-cc-en_GB in a different language)
 * and you want to reuse a specification from atrium-spec to test your custom component against it.
 */
object AssertionVerbFactory : ch.tutteli.atrium.spec.verbs.AssertionVerbFactory {
    override fun <T> checkImmediately(subject: T) = assert(subject)
    override fun <T> checkLazily(subject: T, assertionCreator: Expect<T>.() -> Unit) = assert(subject, assertionCreator)
    override fun checkException(act: () -> Unit) = expect(act)
}
