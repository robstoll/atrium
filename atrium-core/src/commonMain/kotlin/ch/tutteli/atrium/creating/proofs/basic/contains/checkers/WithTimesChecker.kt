package ch.tutteli.atrium.creating.proofs.basic.contains.checkers

import ch.tutteli.atrium.creating.proofs.basic.contains.ToContain

/**
 * Represents a checker which is based on the number of [times] a search criterion is found within the search input.
 *
 * @since 1.3.0
 */
interface WithTimesChecker : ToContain.Checker {
    /**
     * The number which the check uses to compare against the actual number of times an expected search criterion
     * is found in the search input.
     *
     * @since 1.3.0
     */
    val times: Int
}
