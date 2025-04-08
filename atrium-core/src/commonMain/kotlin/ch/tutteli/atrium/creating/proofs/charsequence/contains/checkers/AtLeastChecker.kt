package ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * Represents a check that an expected search criterion is contained at least [times] in the search input.
 */
interface AtLeastChecker :
    ch.tutteli.atrium.creating.proofs.basic.contains.checkers.AtLeastChecker,
    CharSequenceToContain.Checker
