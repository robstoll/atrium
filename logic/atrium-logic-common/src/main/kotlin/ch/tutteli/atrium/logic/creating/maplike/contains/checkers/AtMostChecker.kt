package ch.tutteli.atrium.logic.creating.maplike.contains.checkers

import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains

/**
 * Represents a check that an expected search criterion is contained at most [times] in the search input.
 */
interface AtMostChecker :
    ch.tutteli.atrium.logic.creating.basic.contains.checkers.AtMostChecker,
    MapLikeContains.Checker
