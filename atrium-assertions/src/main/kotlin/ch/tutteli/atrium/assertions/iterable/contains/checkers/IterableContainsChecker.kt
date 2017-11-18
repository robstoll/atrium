package ch.tutteli.atrium.assertions.iterable.contains.checkers

import ch.tutteli.atrium.assertions.base.contains.checkers.ContainsChecker
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains

abstract class IterableContainsChecker(
    times: Int,
    nameFunToUse: String,
    nameFunUsed: String
) : ContainsChecker(times, nameFunToUse, nameFunUsed), IIterableContains.IChecker
