package ch.tutteli.atrium.creating.proofs.basic.contains.checkers

/**
 * Represents a check that an expected search criterion is contained exactly [times] in the search input.
 *
 * @since 1.3.0
 */
interface ExactlyChecker : WithTimesChecker {

    /**
     * The function which should be used instead of [exactlyCall] when [times] is zero.
     *
     * @since 1.3.0
     */
    val nameNotToContainFun: String

    /**
     * The function call which was used and should not be used if [times] is zero.
     *
     * @since 1.3.0
     */
    val exactlyCall: (Int) -> String
}
