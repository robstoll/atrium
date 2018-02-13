package ch.tutteli.atrium.api.cc.infix.en_UK.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilderBase

/**
 *  Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 *   `contains` assertion for [CharSequence].
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 */
@Deprecated("use the builder from the package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder"))
open class CharSequenceContainsNotCheckerBuilder<out T : CharSequence, out S : SearchBehaviour>(
    containsBuilder: CharSequenceContainsBuilder<T, S>
) : CharSequenceContainsNotCheckerBuilderBase<T, S>(containsBuilder)

