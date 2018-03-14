package ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 *  Represents the *deprecated* builder of a `contains not at all` check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 *   `contains` assertion for [CharSequence].
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
@Deprecated(
    "use the builder from the package creating, will be removed with 1.0.0",
    ReplaceWith(
        "NotCheckerOption",
        "ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders.NotCheckerOption"
    )
)
open class CharSequenceContainsNotCheckerBuilder<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    containsBuilder: CharSequenceContains.Builder<T, S>
) : ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders.NotCheckerOptionImpl<T, S>(
    containsBuilder
), CharSequenceContainsCheckerBuilder<T, S>

