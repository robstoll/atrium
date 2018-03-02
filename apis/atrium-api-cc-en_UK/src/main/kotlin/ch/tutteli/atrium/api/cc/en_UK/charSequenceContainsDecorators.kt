package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders.NotCheckerBuilderImpl
import ch.tutteli.atrium.creating.AssertImpl
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour
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
val <T : CharSequence> CharSequenceContains.Builder<T, CharSequenceContainsNoOpSearchBehaviour>.ignoringCase
    get() : CharSequenceContains.Builder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
    = AssertImpl.charSequence.contains.searchBehaviours.ignoringCase(this)

@Deprecated("use the extension fun `ignoringCase` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.ignoringCase"))
fun <T : CharSequence> getIgnoringCase(builder: DeprecatedBuilder<T, CharSequenceContainsNoOpSearchBehaviour>)
    : DeprecatedBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
    = DeprecatedBuilder(builder.plant, builder.ignoringCase.searchBehaviour)


/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> NotCheckerBuilderImpl<T, CharSequenceContainsNotSearchBehaviour>.ignoringCase
    get() : NotCheckerBuilderImpl<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
    = NotCheckerBuilderImpl(containsBuilder.ignoringCase)

@Deprecated("use the extension fun `ignoringCase` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.ignoringCase"))
fun <T : CharSequence> getIgnoringCase(builder: DeprecatedNotCheckerBuilder<T, CharSequenceContainsNotSearchBehaviour>)
    : DeprecatedNotCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
    = DeprecatedNotCheckerBuilder(builder.containsBuilder.ignoringCase)
