package ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * Represents a check that an expected search criterion is contained at most [times] in the search input.
 *
 * @since 1.3.0
 */
interface AtMostChecker :
    ch.tutteli.atrium.creating.proofs.basic.contains.checkers.AtMostChecker,
    CharSequenceToContain.Checker
