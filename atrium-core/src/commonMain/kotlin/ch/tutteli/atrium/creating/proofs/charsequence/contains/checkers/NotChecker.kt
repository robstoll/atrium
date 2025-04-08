package ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * Represents a check that an expected search criterion is not contained in the search input.
 */
interface NotChecker :
    ch.tutteli.atrium.creating.proofs.basic.contains.checkers.NotChecker,
    CharSequenceToContain.Checker
