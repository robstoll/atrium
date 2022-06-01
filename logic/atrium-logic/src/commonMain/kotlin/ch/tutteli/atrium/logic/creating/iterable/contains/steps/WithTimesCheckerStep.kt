package ch.tutteli.atrium.logic.creating.iterable.contains.steps

import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * A [IterableLikeContains.CheckerStep] which is used to choose
 * a [IterableLikeContains.Checker] which is based on a number of `times`.
 */
interface WithTimesCheckerStep<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>
    : IterableLikeContains.CheckerStep<E, T, S>

/**
 * A [IterableLikeContains.CheckerStepLogic] which is used to choose
 * a [IterableLikeContains.Checker] which is based on a number of [times].
 */
interface WithTimesCheckerStepLogic<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>
    : IterableLikeContains.CheckerStepLogic<E, T, S> {
    val times: Int
}

/**
 * Sole purpose of this interface is to hide [IterableLikeContains.CheckerStepLogic] from newcomers which
 * usually don't have to deal with this type and to keep the API clean.
 *
 * Moreover, this keeps the API clean and does not pollute it with things like `times`, `containsBuilder` etc.
 *
 * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
 */
interface WithTimesCheckerStepInternal<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour> :
    WithTimesCheckerStep<E, T, S>, WithTimesCheckerStepLogic<E, T, S>,
    IterableLikeContains.CheckerStepInternal<E, T, S>
