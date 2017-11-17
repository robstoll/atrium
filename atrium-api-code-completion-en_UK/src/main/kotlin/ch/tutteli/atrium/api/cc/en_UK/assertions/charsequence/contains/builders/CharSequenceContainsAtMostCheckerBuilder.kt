package ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.atLeast
import ch.tutteli.atrium.api.cc.en_UK.atMost
import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.api.cc.en_UK.exactly
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsAtMostCheckerBuilderBase
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder

/**
 * Represents the builder of a `contains at least once but at most` check within the fluent API of a
 * sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains at least once but at most` check within the fluent API of a
 *              sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 */
open class CharSequenceContainsAtMostCheckerBuilder<T : CharSequence, D : IDecorator>(
    times: Int,
    containsBuilder: CharSequenceContainsBuilder<T, D>
) : CharSequenceContainsAtMostCheckerBuilderBase<T, D>(
    times,
    containsBuilder,
    containsBuilder.plant::containsNot.name,
    containsBuilder::atMost.name,
    containsBuilder::atLeast.name,
    containsBuilder::exactly.name
)
