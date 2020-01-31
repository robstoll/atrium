@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders.NotCheckerOptionImpl
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder as DeprecatedBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour as DeprecatedIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour as DeprecatedNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour as DeprecatedNotSearchBehaviour

/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.en_GB.ignoringCase"))
val <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>.ignoringCase
    get() : CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
    = AssertImpl.charSequence.contains.searchBehaviours.ignoringCase(this)

@Deprecated("Use the extension fun `ignoringCase` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.ignoringCase"))
fun <T : CharSequence> getIgnoringCase(builder: DeprecatedBuilder<T, NoOpSearchBehaviour>)
    : DeprecatedBuilder<T, IgnoringCaseSearchBehaviour>
    = DeprecatedBuilder(builder.subjectProvider, builder.ignoringCase.searchBehaviour)


/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.ignoringCase"))
val <T : CharSequence> NotCheckerOption<T, NotSearchBehaviour>.ignoringCase
    get() : NotCheckerOption<T, IgnoringCaseSearchBehaviour>
    = NotCheckerOptionImpl(containsBuilder.ignoringCase)

@Deprecated("Use the extension fun `ignoringCase` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.ignoringCase"))
fun <T : CharSequence> getIgnoringCase(builder: DeprecatedNotCheckerBuilder<T, NotSearchBehaviour>)
    : DeprecatedNotCheckerBuilder<T, IgnoringCaseSearchBehaviour>
    = DeprecatedNotCheckerBuilder(builder.containsBuilder.ignoringCase)
