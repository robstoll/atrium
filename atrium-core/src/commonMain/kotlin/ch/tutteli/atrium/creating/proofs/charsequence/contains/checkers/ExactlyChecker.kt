package ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * Represents a check that an expected search criterion is contained exactly [times] in the search input.
 *
 * @since 1.3.0
 */
interface ExactlyChecker :
    ch.tutteli.atrium.creating.proofs.basic.contains.checkers.ExactlyChecker,
    CharSequenceToContain.Checker
