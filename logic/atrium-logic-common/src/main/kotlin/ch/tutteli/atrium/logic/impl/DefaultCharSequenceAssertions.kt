package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.CharSequenceAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.*

class DefaultCharSequenceAssertions : CharSequenceAssertions {
    override fun <T : CharSequence> startsWith(container: AssertionContainer<T>, expected: CharSequence): Assertion =
        container.createDescriptiveAssertion(STARTS_WITH, expected) { it.startsWith(expected) }

    override fun <T : CharSequence> startsNotWith(container: AssertionContainer<T>, expected: CharSequence): Assertion =
        container.createDescriptiveAssertion(STARTS_NOT_WITH, expected) { !it.startsWith(expected) }

    override fun <T : CharSequence> endsWith(container: AssertionContainer<T>, expected: CharSequence): Assertion =
        container.createDescriptiveAssertion(ENDS_WITH, expected) { it.endsWith(expected) }

    override fun <T : CharSequence> endsNotWith(container: AssertionContainer<T>, expected: CharSequence): Assertion =
        container.createDescriptiveAssertion(ENDS_NOT_WITH, expected) { !it.endsWith(expected) }

    override fun <T : CharSequence> isEmpty(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(IS, EMPTY) { it.isEmpty() }

    override fun <T : CharSequence> isNotEmpty(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(IS_NOT, EMPTY) { !it.isEmpty() }

    override fun <T : CharSequence> isNotBlank(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(IS_NOT, BLANK) { it.isNotBlank() }

    override fun <T : CharSequence> matches(container: AssertionContainer<T>, expected: Regex): Assertion =
        container.createDescriptiveAssertion(MATCHES, expected) { it.matches(expected) }

    override fun <T : CharSequence> mismatches(container: AssertionContainer<T>, expected: Regex): Assertion =
        container.createDescriptiveAssertion(MISMATCHES, expected) { !it.matches(expected) }
}
