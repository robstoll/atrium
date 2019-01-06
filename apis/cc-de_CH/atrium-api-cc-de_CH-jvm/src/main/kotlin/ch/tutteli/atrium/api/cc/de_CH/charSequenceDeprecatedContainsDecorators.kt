@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("CharSequenceContainsDecoratorsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder as DeprecatedBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour as DeprecatedIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour as DeprecatedNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour as DeprecatedNotSearchBehaviour

@Deprecated("Use the extension fun `ignoriereGrossKleinschreibung` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.ignoriereGrossKleinschreibung"))
fun <T : CharSequence> getIgnoriereGrossKleinschreibung(builder: DeprecatedBuilder<T, NoOpSearchBehaviour>)
    : DeprecatedBuilder<T, IgnoringCaseSearchBehaviour>
    = DeprecatedBuilder(builder.plant, builder.ignoriereGrossKleinschreibung.searchBehaviour)

@Deprecated("Use the extension fun `ignoriereGrossKleinschreibung` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.ignoriereGrossKleinschreibung"))
fun <T : CharSequence> getIgnoriereGrossKleinschreibung(builder: DeprecatedNotCheckerBuilder<T, NotSearchBehaviour>)
    : DeprecatedNotCheckerBuilder<T, IgnoringCaseSearchBehaviour>
    = DeprecatedNotCheckerBuilder(builder.containsBuilder.ignoriereGrossKleinschreibung)
