package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.ignoringCase
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.notCheckerStep
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Defines that the v behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> EntryPointStep<T, NoOpSearchBehaviour>.ignoringCase: EntryPointStep<T, IgnoringCaseSearchBehaviour>
    get() = _logic.ignoringCase

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @return The newly created builder.
 */
//TODO if we change containsNot to contains.not then it would make sense to remove this with 1.0.0 and
// only keep the above so that we have contains.ignoringCase.not -- seems like a better fit
// as we don't have to re-create the containsBuilder but use NotChecker only as checker as all other checkers
val <T : CharSequence> NotCheckerStep<T, NotSearchBehaviour>.ignoringCase: NotCheckerStep<T, IgnoringCaseSearchBehaviour>
    get() = _logic.entryPointStepLogic.ignoringCase._logic.notCheckerStep()
