package ch.tutteli.atrium.logic.creating.charsequence.contains.checkers

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains

/**
 * Represents a check that an expected search criterion is contained exactly [times] in the search input.
 */
interface ExactlyChecker :
    ch.tutteli.atrium.logic.creating.basic.contains.checkers.ExactlyChecker,
    CharSequenceContains.Checker
