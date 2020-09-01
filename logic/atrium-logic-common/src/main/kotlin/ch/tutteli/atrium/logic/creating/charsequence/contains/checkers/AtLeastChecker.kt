package ch.tutteli.atrium.logic.creating.charsequence.contains.checkers

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains

/**
 * Represents a check that an expected search criterion is contained at least [times] in the search input.
 */
interface AtLeastChecker :
    ch.tutteli.atrium.logic.creating.basic.contains.checkers.AtLeastChecker,
    CharSequenceContains.Checker
