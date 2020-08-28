package ch.tutteli.atrium.logic.creating.basic.contains.checkers

import ch.tutteli.atrium.logic.creating.basic.contains.Contains

/**
 * Represents a checker which is based the number of [times] a search criterion is found within the search input.
 */
interface WithTimesChecker : Contains.Checker {
    /**
     * The number which the check uses to compare against the actual number of times an expected search criterion
     * is found in the search input.
     */
    val times: Int
}
