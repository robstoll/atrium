package ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.infix.en_UK.atLeast
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.builders.AtLeastCheckerBuilderBase
import ch.tutteli.atrium.creating.charsequence.contains.builders.WithTimesCheckerBuilder

/**
 * Represents the extension point for another option after a `contains at least`-check within a sophisticated
 * `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface AtLeastCheckerBuilder<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : WithTimesCheckerBuilder<T, S>

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
@Deprecated("Do not rely on this type, will be made internal with 1.0.0", ReplaceWith("AtLeastCheckerBuilder"))
open class AtLeastCheckerBuilderImpl<out T : CharSequence, out S : SearchBehaviour>(
    times: Int,
    containsBuilder: CharSequenceContains.Builder<T, S>
) : AtLeastCheckerBuilderBase<T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "`${containsBuilder::atLeast.name} $it`" }
), AtLeastCheckerBuilder<T, S>

