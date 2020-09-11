package ch.tutteli.atrium.logic.creating.iterable.contains.steps

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.*
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.ExactlyChecker
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.impl.DefaultExactlyChecker
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl.*
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl.*
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

fun <E, T : IterableLike, S : SearchBehaviour> EntryPointStepLogic<E, T, S>.atLeastCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
): AtLeastCheckerStep<E, T, S> = GenericTimesCheckerStep(
    times,
    this,
    listOf(atLeastChecker(container, times, nameContainsNotFun, atLeastCall))
)

fun <E, T : IterableLike, S : SearchBehaviour> WithTimesCheckerStepLogic<E, T, S>.butAtMostCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    atLeastButAtMostCall: (Int, Int) -> String,
    atLeastCall: (Int) -> String,
    butAtMostCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    atMostCall: (Int) -> String
): ButAtMostCheckerStep<E, T, S> = ButAtMostCheckerStepImpl(
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

fun <E, T : IterableLike, S : SearchBehaviour> EntryPointStepLogic<E, T, S>.atMostCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String,
    atLeastCall: (Int) -> String,
    exactlyCall: (Int) -> String
): AtMostCheckerStep<E, T, S> = AtMostCheckerStepImpl(
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
fun <E, T : IterableLike, S : SearchBehaviour> EntryPointStepLogic<E, T, S>.exactlyCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    exactlyCall: (Int) -> String
): ExactlyCheckerStep<E, T, S> = GenericTimesCheckerStep(
    times,
    this,
    listOf(container.getImpl(ExactlyChecker::class) { DefaultExactlyChecker(times, nameContainsNotFun, exactlyCall) })
)

fun <E, T : IterableLike, S : SearchBehaviour> EntryPointStepLogic<E, T, S>.notOrAtMostCheckerStep(
    times: Int,
    nameContainsNotFun: String,
    notOrAtMostCall: (Int) -> String
): NotOrAtMostCheckerStep<E, T, S> = NotOrAtMostCheckerStepImpl(
    times,
    nameContainsNotFun,
    notOrAtMostCall,
    this
)

fun <E, T : IterableLike, S : SearchBehaviour> EntryPointStepLogic<E, T, S>.notCheckerStep(): NotCheckerStep<E, T, S> =
    NotCheckerStepImpl(this)

val <E, T : IterableLike> EntryPointStepLogic<E, T, NoOpSearchBehaviour>.inAnyOrder: EntryPointStep<E, T, InAnyOrderSearchBehaviour>
    get() = withSearchBehaviour(InAnyOrderSearchBehaviourImpl())

val <E, T : IterableLike> EntryPointStepLogic<E, T, InAnyOrderSearchBehaviour>.butOnly: EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>
    get() = withSearchBehaviour(InAnyOrderOnlySearchBehaviourImpl())

val <E, T : IterableLike> EntryPointStepLogic<E, T, NoOpSearchBehaviour>.inOrder: EntryPointStep<E, T, InOrderSearchBehaviour>
    get() = withSearchBehaviour(InOrderSearchBehaviourImpl())

val <E, T : IterableLike> EntryPointStepLogic<E, T, InOrderSearchBehaviour>.andOnly: EntryPointStep<E, T, InOrderOnlySearchBehaviour>
    get() = withSearchBehaviour(InOrderOnlySearchBehaviourImpl())

val <E, T : IterableLike> EntryPointStepLogic<E, T, InOrderOnlySearchBehaviour>.grouped: EntryPointStep<E, T, InOrderOnlyGroupedSearchBehaviour>
    get() = withSearchBehaviour(InOrderOnlyGroupedSearchBehaviourImpl())

val <E, T : IterableLike> EntryPointStepLogic<E, T, InOrderOnlyGroupedSearchBehaviour>.within: EntryPointStep<E, T, InOrderOnlyGroupedWithinSearchBehaviour>
    get() = withSearchBehaviour(InOrderOnlyGroupedWithinSearchBehaviourImpl())
