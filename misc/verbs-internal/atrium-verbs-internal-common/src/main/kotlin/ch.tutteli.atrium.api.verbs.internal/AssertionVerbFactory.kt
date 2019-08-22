package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.specs.AssertionVerbFactory

/**
 * Only required if you implement a custom component (for instance an own [Reporter], [ObjectFormatter] etc.)
 * or an own assertion function API (e.g., atrium-api-cc-en_GB in a different language)
 * and you want to reuse a specification from atrium-spec to test your custom component against it.
 */
object AssertionVerbFactory : AssertionVerbFactory {
    override fun <T> check(subject: T) = expect(subject)
    override fun checkException(act: () -> Unit) = expect(act)
}
