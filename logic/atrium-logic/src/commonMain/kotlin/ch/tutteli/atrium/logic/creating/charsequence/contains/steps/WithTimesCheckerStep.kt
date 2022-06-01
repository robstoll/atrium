package ch.tutteli.atrium.logic.creating.charsequence.contains.steps

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains

/**
 * A [CharSequenceContains.CheckerStep] which is used to choose
 * a [CharSequenceContains.Checker] which is based on a number of `times`.
 */
interface WithTimesCheckerStep<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerStep<T, S>

/**
 * A [CharSequenceContains.CheckerStepLogic] which is used to choose
 * a [CharSequenceContains.Checker] which is based on a number of [times].
 */
interface WithTimesCheckerStepLogic<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerStepLogic<T, S> {
    val times: Int
}

/**
 * Sole purpose of this interface is to hide [CharSequenceContains.CheckerStepLogic] from newcomers which
 * usually don't have to deal with this type and to keep the API clean.
 *
 * Moreover, this keeps the API clean and does not pollute it with things like `times`, `containsBuilder` etc.
 *
 * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
 */
interface WithTimesCheckerStepInternal<T : CharSequence, out S : CharSequenceContains.SearchBehaviour> :
    WithTimesCheckerStep<T, S>, WithTimesCheckerStepLogic<T, S>,
    CharSequenceContains.CheckerStepInternal<T, S>
