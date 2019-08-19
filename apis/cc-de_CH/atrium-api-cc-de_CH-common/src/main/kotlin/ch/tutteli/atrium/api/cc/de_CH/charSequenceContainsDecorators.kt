@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("CharSequenceContainsDecoratorsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>.ignoriereGrossKleinschreibung
    get() : CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
    = AssertImpl.charSequence.contains.searchBehaviours.ignoringCase(this)
/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> NotCheckerOption<T, NotSearchBehaviour>.ignoriereGrossKleinschreibung
    get() : NotCheckerOption<T, IgnoringCaseSearchBehaviour>
    = NotCheckerOptionImpl(containsBuilder.ignoriereGrossKleinschreibung)
