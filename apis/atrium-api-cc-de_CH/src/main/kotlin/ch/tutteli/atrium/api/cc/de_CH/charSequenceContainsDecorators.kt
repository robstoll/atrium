package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder as DeprecatedBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour as DeprecatedIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour as DeprecatedNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour as DeprecatedNotSearchBehaviour

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>.ignoriereGrossKleinschreibung
    get() : CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
    = AssertImpl.charSequence.contains.searchBehaviours.ignoringCase(this)

@Deprecated("Use the extension fun `ignoriereGrossKleinschreibung` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.ignoriereGrossKleinschreibung"))
fun <T : CharSequence> getIgnoriereGrossKleinschreibung(builder: DeprecatedBuilder<T, NoOpSearchBehaviour>)
    : DeprecatedBuilder<T, IgnoringCaseSearchBehaviour>
    = DeprecatedBuilder(builder.plant, builder.ignoriereGrossKleinschreibung.searchBehaviour)


/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> NotCheckerOption<T, NotSearchBehaviour>.ignoriereGrossKleinschreibung
    get() : NotCheckerOption<T, IgnoringCaseSearchBehaviour>
    = NotCheckerOptionImpl(containsBuilder.ignoriereGrossKleinschreibung)

@Deprecated("Use the extension fun `ignoriereGrossKleinschreibung` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.ignoriereGrossKleinschreibung"))
fun <T : CharSequence> getIgnoriereGrossKleinschreibung(builder: DeprecatedNotCheckerBuilder<T, NotSearchBehaviour>)
    : DeprecatedNotCheckerBuilder<T, IgnoringCaseSearchBehaviour>
    = DeprecatedNotCheckerBuilder(builder.containsBuilder.ignoriereGrossKleinschreibung)
