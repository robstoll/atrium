package ch.tutteli.atrium.logic.creating.iterablelike.contains.checkers

import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains

/**
 * Represents a check that an expected search criterion is not contained in the search input.
 */
interface NotChecker :
    ch.tutteli.atrium.logic.creating.basic.contains.checkers.NotChecker,
    IterableLikeContains.Checker
