package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.kbox.glue

/**
 * Parameter object to express a [Group] with a single identification lambda.
 */
class Eintrag<in T : Any, out A : ((Assert<T>) -> Unit)?>(val assertionCreator: A): Group<A>{
    override fun toList(): List<A> = listOf(assertionCreator)
}

/**
 * Parameter object to express `((Assert<T>) -> Unit)?, vararg ((Assert<T>) -> Unit)?` in the infix-api.
 */
class Eintraege<in T : Any, out A : ((Assert<T>) -> Unit)?>(
    val assertionCreator: A,
    vararg val otherAssertionCreators: A
): Group<A> {
    override fun toList(): List<A> = assertionCreator glue otherAssertionCreators
}

/**
 * Represents a [Group] with a single value.
 */
data class Wert<T>(val expected: T): Group<T> {
    override fun toList() = listOf(expected)
}

/**
 * Represents a [Group] of multiple values.
 */
class Werte<T>(private val expected: T,  vararg val otherExpected: T): Group<T>{
    override fun toList() = listOf(expected, *otherExpected)
}
