package ch.tutteli.atrium.logic.creating.charsequence.contains.steps

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains

/**
 * A [CharSequenceContains.CheckerOption] which is used to choose
 * a [CharSequenceContains.Checker] which is based on a number of `times`.
 */
interface WithTimesCheckerOption<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerOption<T, S>

/**
 * A [CharSequenceContains.CheckerOptionLogic] which is used to choose
 * a [CharSequenceContains.Checker] which is based on a number of [times].
 */
interface WithTimesCheckerOptionLogic<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerOptionLogic<T, S> {
    val times: Int
}

/**
 * Sole purpose of this interface is to hide [CharSequenceContains.CheckerOptionLogic] from newcomers which
 * usually don't have to deal with this type.
 *
 * Moreover, this keeps the API clean and does not pollute it with things like `times`, `containsBuilder` etc.
 *
 * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
 */
interface WithTimesCheckerOptionInternal<T : CharSequence, out S : CharSequenceContains.SearchBehaviour> :
    WithTimesCheckerOption<T, S>, WithTimesCheckerOptionLogic<T, S>,
    CharSequenceContains.CheckerOptionInternal<T, S>
