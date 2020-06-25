package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

interface CharSequenceAssertions {
    //TODO add with 0.14.0
//    fun <T : CharSequence> containsBuilder(container: AssertionContainer<T>): CharSequenceContains.Builder<T, NoOpSearchBehaviour>
//    fun <T : CharSequence> containsNotBuilder(container: AssertionContainer<T>): CharSequenceContains.Builder<T, NotSearchBehaviour>

    fun <T : CharSequence> startsWith(container: AssertionContainer<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> startsNotWith(container: AssertionContainer<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> endsWith(container: AssertionContainer<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> endsNotWith(container: AssertionContainer<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> isEmpty(container: AssertionContainer<T>): Assertion
    fun <T : CharSequence> isNotEmpty(container: AssertionContainer<T>): Assertion
    fun <T : CharSequence> isNotBlank(container: AssertionContainer<T>): Assertion

    fun <T : CharSequence> matches(container: AssertionContainer<T>, expected: Regex): Assertion
    fun <T : CharSequence> mismatches(container: AssertionContainer<T>, expected: Regex): Assertion
}
