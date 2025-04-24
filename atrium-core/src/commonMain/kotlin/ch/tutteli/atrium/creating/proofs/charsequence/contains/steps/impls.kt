package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.*
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.ExactlyChecker
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.impl.DefaultExactlyChecker
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.impl.IgnoringCaseSearchBehaviourImpl
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.impl.*

fun <T : CharSequence, S : SearchBehaviour> EntryPointStepCore<T, S>.atLeastCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
): AtLeastCheckerStep<T, S> = GenericTimesCheckerStep(
    times,
    this,
    listOf(atLeastChecker(container, times, nameContainsNotFun, atLeastCall))
)

fun <T : CharSequence, S : SearchBehaviour> WithTimesCheckerStepCore<T, S>.butAtMostCheckerStep(
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
    entryPointStepCore
)

fun <T : CharSequence, S : SearchBehaviour> EntryPointStepCore<T, S>.atMostCheckerStep(
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

@OptIn(ExperimentalNewExpectTypes::class)
fun <T : CharSequence, S : SearchBehaviour> EntryPointStepCore<T, S>.exactlyCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    exactlyCall: (Int) -> String
): ExactlyCheckerStep<T, S> = GenericTimesCheckerStep(
    times,
    this,
    listOf(container.getImpl(ExactlyChecker::class) { DefaultExactlyChecker(times, nameContainsNotFun, exactlyCall) })
)

fun <T : CharSequence, S : SearchBehaviour> EntryPointStepCore<T, S>.notOrAtMostCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    notOrAtMostCall: (Int) -> String
): NotOrAtMostCheckerStep<T, S> = NotOrAtMostCheckerStepImpl(
    times,
    nameContainsNotFun,
    notOrAtMostCall,
    this
)

fun <T : CharSequence, S : SearchBehaviour> EntryPointStepCore<T, S>.notCheckerStep(): NotCheckerStep<T, S> =
    NotCheckerStepImpl(this)

val <T : CharSequence, S : NoOpSearchBehaviour> EntryPointStepCore<T, S>.ignoringCase: EntryPointStep<T, IgnoringCaseSearchBehaviour>
    get() = EntryPointStepImpl(container, IgnoringCaseSearchBehaviourImpl(searchBehaviour))

