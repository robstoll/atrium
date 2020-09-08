package ch.tutteli.atrium.logic.creating.iterable.contains.checkers

import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains

/**
 * Represents a check that an expected search criterion is contained at least [times] in the search input.
 */
interface AtLeastChecker :
    ch.tutteli.atrium.logic.creating.basic.contains.checkers.AtLeastChecker,
    IterableLikeContains.Checker
