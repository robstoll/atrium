package ch.tutteli.atrium.logic.creating.iterable.contains.checkers

import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains

/**
 * Represents a check that an expected search criterion is contained at most [times] in the search input.
 */
interface AtMostChecker :
    ch.tutteli.atrium.logic.creating.basic.contains.checkers.AtMostChecker,
    IterableLikeContains.Checker
