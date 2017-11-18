package ch.tutteli.atrium.assertions.charsequence.contains.checkers

import ch.tutteli.atrium.assertions.base.contains.checkers.ContainsChecker
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains

abstract class CharSequenceContainsChecker(
    times: Int,
    nameFunToUse: String,
    nameFunUsed: String
) : ContainsChecker(times, nameFunToUse, nameFunUsed), ICharSequenceContains.IChecker
