package ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * Represents a check that an expected search criterion is not contained in the search input.
 *
 * @since 1.3.0
 */
interface NotChecker :
    ch.tutteli.atrium.creating.proofs.basic.contains.checkers.NotChecker,
    CharSequenceToContain.Checker
