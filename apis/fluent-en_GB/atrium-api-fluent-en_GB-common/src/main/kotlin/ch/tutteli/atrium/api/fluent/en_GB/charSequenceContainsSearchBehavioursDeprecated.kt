//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
@file:JvmMultifileClass
@file:JvmName("CharSequenceContainsSearchBehavioursKt")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Defines that the v behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>.ignoringCase: CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
    get() : CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour> =
        ExpectImpl.charSequence.contains.searchBehaviours.ignoringCase(this)

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> NotCheckerOption<T, NotSearchBehaviour>.ignoringCase: NotCheckerOption<T, IgnoringCaseSearchBehaviour>
    get() : NotCheckerOption<T, IgnoringCaseSearchBehaviour> =
        NotCheckerOptionImpl(containsBuilder.ignoringCase)
