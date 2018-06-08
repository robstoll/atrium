package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders.NotCheckerOptionImpl
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder

/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.infix.en_GB.ignoring(case)"))
infix fun <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
    = AssertImpl.charSequence.contains.searchBehaviours.ignoringCase(this)

@Deprecated("Use the extension fun `ignoring` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder ignoring case"))
fun <T : CharSequence> ignoring(builder: CharSequenceContainsBuilder<T, NoOpSearchBehaviour>, @Suppress("UNUSED_PARAMETER") case: case): CharSequenceContainsBuilder<T, IgnoringCaseSearchBehaviour>
    = CharSequenceContainsBuilder(builder.plant, (builder ignoring case).searchBehaviour)


/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.infix.en_GB.ignoring(case)"))
infix fun <T : CharSequence> NotCheckerOption<T, NotSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case): NotCheckerOption<T, IgnoringCaseSearchBehaviour>
    = NotCheckerOptionImpl(containsBuilder ignoring case)

@Deprecated("Use the extension fun `ignoring` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder ignoring case"))
fun <T : CharSequence> DeprecatedNotCheckerBuilder<T, NotSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case): DeprecatedNotCheckerBuilder<T, IgnoringCaseSearchBehaviour>
    = DeprecatedNotCheckerBuilder(containsBuilder ignoring case)
