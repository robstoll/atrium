package ch.tutteli.atrium.logic.creating.charsequence.contains.steps

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.*
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.ExactlyChecker
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl.DefaultExactlyChecker
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl.IgnoringCaseSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl.*

fun <T : CharSequence, S : SearchBehaviour> EntryPointStepLogic<T, S>.atLeastCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
): AtLeastCheckerStep<T, S> = GenericTimesCheckerStep(
    times,
    this,
    listOf(atLeastChecker(container, times, nameContainsNotFun, atLeastCall))
)

fun <T : CharSequence, S : SearchBehaviour> WithTimesCheckerStepLogic<T, S>.butAtMostCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    atLeastButAtMostCall: (Int, Int) -> String,
    atLeastCall: (Int) -> String,
    butAtMostCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    atMostCall: (Int) -> String
): ButAtMostCheckerStep<T, S> = ButAtMostCheckerStepImpl(
    times,
    this,
    nameContainsNotFun,
    atLeastButAtMostCall,
    atLeastCall,
    butAtMostCall,
    exactlyCall,
    atMostCall,
    entryPointStepLogic
)

fun <T : CharSequence, S : SearchBehaviour> EntryPointStepLogic<T, S>.atMostCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String,
    atLeastCall: (Int) -> String,
    exactlyCall: (Int) -> String
): AtMostCheckerStep<T, S> = AtMostCheckerStepImpl(
    times,
    nameContainsNotFun,
    atMostCall,
    atLeastCall,
    exactlyCall,
    this
)


@Suppress(
    // unchecked ok as the SearchBehaviour is only relevant during compile time (defines extension point)
    "UNCHECKED_CAST",
    // OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2
    "DEPRECATION"
)
@UseExperimental(ExperimentalNewExpectTypes::class)
fun <T : CharSequence, S : SearchBehaviour> EntryPointStepLogic<T, S>.exactlyCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    exactlyCall: (Int) -> String
): ExactlyCheckerStep<T, S> = GenericTimesCheckerStep(
    times,
    this,
    listOf(container.getImpl(ExactlyChecker::class) { DefaultExactlyChecker(times, nameContainsNotFun, exactlyCall) })
)

fun <T : CharSequence, S : SearchBehaviour> EntryPointStepLogic<T, S>.notOrAtMostCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    notOrAtMostCall: (Int) -> String
): NotOrAtMostCheckerStep<T, S> = NotOrAtMostCheckerStepImpl(
    times,
    nameContainsNotFun,
    notOrAtMostCall,
    this
)

fun <T : CharSequence, S : SearchBehaviour> EntryPointStepLogic<T, S>.notCheckerStep(): NotCheckerStep<T, S> =
    NotCheckerStepImpl(this)

val <T : CharSequence, S : NoOpSearchBehaviour> EntryPointStepLogic<T, S>.ignoringCase: EntryPointStep<T, IgnoringCaseSearchBehaviour>
    get() = EntryPointStepImpl(container, IgnoringCaseSearchBehaviourImpl(searchBehaviour))

