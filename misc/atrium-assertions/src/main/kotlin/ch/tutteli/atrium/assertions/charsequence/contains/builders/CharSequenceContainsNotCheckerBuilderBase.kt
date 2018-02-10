package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.checkers.CharSequenceContainsNotChecker

/**
 * The base class for builders which create a `contains not` check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create a `contains at least` check within the fluent API of a
 *   sophisticated `contains` assertion for [CharSequence].
 */
@Deprecated("use the abstract class from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilderBase"))
abstract class CharSequenceContainsNotCheckerBuilderBase<out T : CharSequence, out S : SearchBehaviour>(
    containsBuilder: CharSequenceContainsBuilder<T, S>
) : CharSequenceContainsCheckerBuilder<T, S>(containsBuilder) {

    override val checkers = listOf(CharSequenceContainsNotChecker())
}

