@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.infix.en_UK.atLeast
import ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.AtLeastCheckerOptionBase
import ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.WithTimesCheckerOption
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.SearchBehaviour

/**
 * Represents the extension point for another option after a `contains at least`-check within a sophisticated
 * `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders.AtLeastCheckerOption"))
interface AtLeastCheckerOption<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : WithTimesCheckerOption<T, S>

/**
 * Represents the builder of a `contains at least`-check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains at least` check within the fluent API of a sophisticated
 *   `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
@Deprecated("Do not rely on this type; will be made internal with 1.0.0", ReplaceWith("AtLeastCheckerOption"))
open class AtLeastCheckerOptionImpl<out T : CharSequence, out S : SearchBehaviour>(
    times: Int,
    containsBuilder: CharSequenceContains.Builder<T, S>
) : AtLeastCheckerOptionBase<T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "`${containsBuilder::atLeast.name} $it`" }
), AtLeastCheckerOption<T, S>

