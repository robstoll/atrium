package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * A [CharSequenceToContain.CheckerStep] which is used to choose
 * a [CharSequenceToContain.Checker] which is based on a number of `times`.
 *
 * @since 1.3.0
 */
interface WithTimesCheckerStep<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>
    : CharSequenceToContain.CheckerStep<T, S>

/**
 * A [CharSequenceToContain.CheckerStepCore] which is used to choose
 * a [CharSequenceToContain.Checker] which is based on a number of [times].
 *
 * @since 1.3.0
 */
interface WithTimesCheckerStepCore<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>
    : CharSequenceToContain.CheckerStepCore<T, S> {
    val times: Int
}

/**
 * Sole purpose of this interface is to hide [CharSequenceToContain.CheckerStepCore] from newcomers which
 * usually don't have to deal with this type and to keep the API clean.
 *
 * Moreover, this keeps the API clean and does not pollute it with things like `times`, `containsBuilder` etc.
 *
 * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
 *
 * @since 1.3.0
 */
interface WithTimesCheckerStepInternal<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour> :
    WithTimesCheckerStep<T, S>, WithTimesCheckerStepCore<T, S>,
    CharSequenceToContain.CheckerStepInternal<T, S>
