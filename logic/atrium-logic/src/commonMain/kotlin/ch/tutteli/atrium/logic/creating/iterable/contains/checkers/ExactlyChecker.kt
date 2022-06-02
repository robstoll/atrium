package ch.tutteli.atrium.logic.creating.iterable.contains.checkers

import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains

/**
 * Represents a check that an expected search criterion is contained exactly [times] in the search input.
 */
interface ExactlyChecker :
    ch.tutteli.atrium.logic.creating.basic.contains.checkers.ExactlyChecker,
    IterableLikeContains.Checker
