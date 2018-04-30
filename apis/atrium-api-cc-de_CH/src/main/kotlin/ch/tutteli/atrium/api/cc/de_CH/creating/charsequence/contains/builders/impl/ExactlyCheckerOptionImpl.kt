package ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.ExactlyCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.genau
import ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.ExactlyCheckerOptionBase
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * Represents the builder of a `contains exactly` check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains exactly` check within the fluent API of a sophisticated
 *   `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
@Deprecated("Do not rely on this type, will be made internal with 1.0.0", ReplaceWith("ExactlyCheckerBuilder"))
open class ExactlyCheckerOptionImpl<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    containsBuilder: CharSequenceContains.Builder<T, S>
) : ExactlyCheckerOptionBase<T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "${containsBuilder::genau.name}($it)" }
), ExactlyCheckerOption<T, S>
