package ch.tutteli.atrium.logic.creating.charsequence.contains.checkers

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains

/**
 * Represents a check that an expected search criterion is not contained in the search input.
 */
interface NotChecker :
    ch.tutteli.atrium.logic.creating.basic.contains.checkers.NotChecker,
    CharSequenceContains.Checker
