package ch.tutteli.atrium.creating.proofs.basic.contains.checkers

/**
 * Represents a check that an expected search criterion is contained at least [times] in the search input.
 *
 * @since 1.3.0
 */
interface AtLeastChecker : WithTimesChecker {

    /**
     * The function which should be used instead of [atLeastCall] when [times] is zero.
     *
     * @since 1.3.0
     */
    val nameNotToContainFun: String

    /**
     * The function which was used and should not be used if [times] is zero.
     *
     * @since 1.3.0
     */
    val atLeastCall: (Int) -> String
}
