package ch.tutteli.atrium.logic.creating.basic.contains.checkers

/**
 * Represents a check that an expected search criterion is contained at most [times] in the search input.
 */
interface AtMostChecker : WithTimesChecker {

    /**
     * The function which should be used instead of [atMostCall] when [times] is zero.
     */
    val nameContainsNotFun: String

    /**
     * The function which was used and should not be used if [times] is zero.
     */
    val atMostCall: (Int) -> String
}
