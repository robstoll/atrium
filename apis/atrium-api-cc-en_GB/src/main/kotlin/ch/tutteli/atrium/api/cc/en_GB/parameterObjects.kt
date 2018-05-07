package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.kbox.glue

/**
 * Parameter object to express a [Group] with a single identification lambda.
 */
class Entry<in T : Any, out A : ((Assert<T>) -> Unit)?>(val assertionCreator: A): Group<A>{
    override fun toList(): List<A> = listOf(assertionCreator)
}

/**
 * Parameter object to express `((Assert<T>) -> Unit)?, vararg ((Assert<T>) -> Unit)?` in the infix-api.
 */
class Entries<in T : Any, out A : ((Assert<T>) -> Unit)?>(
    val assertionCreator: A,
    vararg val otherAssertionCreators: A
): Group<A> {
    override fun toList(): List<A> = assertionCreator glue otherAssertionCreators
}

/**
 * Represents a [Group] with a single value.
 */
data class Value<T>(val expected: T): Group<T> {
    override fun toList() = listOf(expected)
}

/**
 * Represents a [Group] of multiple values.
 */
class Values<T>(val expected: T,  vararg val otherExpected: T): Group<T>{
    override fun toList() = listOf(expected, *otherExpected)
}
