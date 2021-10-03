package ch.tutteli.atrium.logic.creating.basic.contains.checkers

/**
 * Represents a check that an expected search criterion is contained exactly [times] in the search input.
 */
interface ExactlyChecker : WithTimesChecker {

    /**
     * The function which should be used instead of [exactlyCall] when [times] is zero.
     */
    val nameContainsNotFun: String

    /**
     * The function call which was used and should not be used if [times] is zero.
     */
    val exactlyCall: (Int) -> String
}
