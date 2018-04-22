package ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.creating

import ch.tutteli.atrium.domain.builders.utils.Group

/**
 * Parameter object to express `Group<T>, Group<T>, vararg Group<T>` in the infix-api.
 */
class Order<T>(
    val firstGroup: Group<T>,
    val secondGroup: Group<T>,
    vararg val otherExpectedGroups: Group<T>
)
