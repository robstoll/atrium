package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerOption

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [CharSequence] type.
 */
interface CharSequenceAssertions {

    fun <T : CharSequence> containsBuilder(
        container: AssertionContainer<T>
    ): CharSequenceContains.Builder<T, NoOpSearchBehaviour>

    fun <T : CharSequence> containsNotCheckerOption(
        container: AssertionContainer<T>
    ): NotCheckerOption<T, NotSearchBehaviour>

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
