package ch.tutteli.atrium.logic.creating.iterablelike.contains.checkers

import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains

/**
 * Represents a check that an expected search criterion is contained at least [times] in the search input.
 */
interface AtLeastChecker :
    ch.tutteli.atrium.logic.creating.basic.contains.checkers.AtLeastChecker,
    IterableLikeContains.Checker
