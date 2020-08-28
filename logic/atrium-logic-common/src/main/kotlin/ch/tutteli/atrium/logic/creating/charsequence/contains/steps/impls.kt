package ch.tutteli.atrium.logic.creating.charsequence.contains.steps

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.*
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.ExactlyChecker
import ch.tutteli.atrium.logic.creating.charsequence.contains.checkers.impl.DefaultExactlyChecker
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.impl.IgnoringCaseSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl.*

fun <T : CharSequence, S : SearchBehaviour> BuilderLogic<T, S>.atLeastCheckerOption(
    times: Int,
    nameContainsNotFun: String,
    atLeastCall: (Int) -> String
): AtLeastCheckerOption<T, S> = GenericTimesCheckerOption(
    times,
    this,
    listOf(atLeastChecker(container, times, nameContainsNotFun, atLeastCall))
)

fun <T : CharSequence, S : SearchBehaviour> WithTimesCheckerOptionLogic<T, S>.butAtMostCheckerOption(
    times: Int,
    nameContainsNotFun: String,
    atLeastButAtMostCall: (Int, Int) -> String,
    atLeastCall: (Int) -> String,
    butAtMostCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    atMostCall: (Int) -> String
): ButAtMostCheckerOption<T, S> = ButAtMostCheckerOptionImpl(
    times,
    this,
    nameContainsNotFun,
    atLeastButAtMostCall,
    atLeastCall,
    butAtMostCall,
    exactlyCall,
    atMostCall,
    containsBuilder
)

fun <T : CharSequence, S : SearchBehaviour> BuilderLogic<T, S>.atMostCheckerOption(
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String,
    atLeastCall: (Int) -> String,
    exactlyCall: (Int) -> String
): AtMostCheckerOption<T, S> = AtMostCheckerOptionImpl(
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
fun <T : CharSequence, S : SearchBehaviour> BuilderLogic<T, S>.exactlyCheckerOption(
    times: Int,
    nameContainsNotFun: String,
    exactlyCall: (Int) -> String
): ExactlyCheckerOption<T, S> = GenericTimesCheckerOption(
    times,
    this,
    listOf(container.getImpl(ExactlyChecker::class) { DefaultExactlyChecker(times, nameContainsNotFun, exactlyCall) })
)

fun <T : CharSequence, S : SearchBehaviour> BuilderLogic<T, S>.notOrAtMostCheckerOption(
    times: Int,
    nameContainsNotFun: String,
    notOrAtMostCall: (Int) -> String
): NotOrAtMostCheckerOption<T, S> = NotOrAtMostCheckerOptionImpl(
    times,
    nameContainsNotFun,
    notOrAtMostCall,
    this
)

fun <T : CharSequence, S : SearchBehaviour> BuilderLogic<T, S>.notCheckerOption(): NotCheckerOption<T, S> =
    NotCheckerOptionImpl(this)

val <T : CharSequence, S : NoOpSearchBehaviour> BuilderLogic<T, S>.ignoringCase: Builder<T, IgnoringCaseSearchBehaviour>
    get() = BuilderImpl(container, IgnoringCaseSearchBehaviourImpl(searchBehaviour))

