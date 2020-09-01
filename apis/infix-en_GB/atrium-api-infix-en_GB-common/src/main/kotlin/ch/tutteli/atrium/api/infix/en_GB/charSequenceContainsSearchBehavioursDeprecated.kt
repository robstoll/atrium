//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
@file:JvmMultifileClass
@file:JvmName("CharSequenceContainsSearchBehavioursKt")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>.ignoring(
    @Suppress("UNUSED_PARAMETER") case: case
): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour> =
    ExpectImpl.charSequence.contains.searchBehaviours.ignoringCase(this)

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> NotCheckerOption<T, NotSearchBehaviour>.ignoring(
    @Suppress("UNUSED_PARAMETER") case: case
): NotCheckerOption<T, IgnoringCaseSearchBehaviour> =
    NotCheckerOptionImpl(containsBuilder ignoring case)
