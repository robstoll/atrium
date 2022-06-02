package ch.tutteli.atrium.logic.creating.basic.contains.checkers

/**
 * Represents a check that an expected search criterion is contained at least [times] in the search input.
 */
interface AtLeastChecker : WithTimesChecker {

    /**
     * The function which should be used instead of [atLeastCall] when [times] is zero.
     */
    val nameContainsNotFun: String

    /**
     * The function which was used and should not be used if [times] is zero.
     */
    val atLeastCall: (Int) -> String
}
