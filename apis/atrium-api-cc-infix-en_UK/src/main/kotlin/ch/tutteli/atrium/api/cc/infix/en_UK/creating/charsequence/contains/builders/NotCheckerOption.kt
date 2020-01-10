@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0*/)
package ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders

import ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.NotCheckerOptionBase
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.SearchBehaviour

/**
 * Represents the extension point for another option after a `contains not at all`-check within
 * a sophisticated `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders.NotCheckerOption"))
interface NotCheckerOption<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerOption<T, S>

/**
 *  Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 *   `contains` assertion for [CharSequence].
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
@Deprecated("Do not rely on this type; will be made internal with 0.10.0", ReplaceWith("NotCheckerOption"))
open class NotCheckerOptionImpl<out T : CharSequence, out S : SearchBehaviour>(
    containsBuilder: CharSequenceContains.Builder<T, S>
) : NotCheckerOptionBase<T, S>(containsBuilder), NotCheckerOption<T, S>

