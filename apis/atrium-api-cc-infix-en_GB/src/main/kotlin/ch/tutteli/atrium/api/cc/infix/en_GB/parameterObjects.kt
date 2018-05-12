package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.kbox.glue

/**
 * Parameter object to express `Translatable, vararg Translatable` in the infix-api.
 */
class DefaultTranslationsOf(val expected: Translatable, vararg val otherExpected: Translatable) {
    fun toList(): List<Translatable> = expected glue otherExpected
}

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
 * Parameter object to express `Group<T>, Group<T>, vararg Group<T>` in the infix-api.
 */
class Order<T>(
    val firstGroup: Group<T>,
    val secondGroup: Group<T>,
    vararg val otherExpectedGroups: Group<T>
)

/**
 * Parameter object to express `String, vararg String` in the infix-api.
 */
class RegexPatterns(val pattern: String, vararg val otherPatterns: String) {
    fun toList(): List<String> = pattern glue otherPatterns
}

/**
 * Parameter object to express a [Group] with a single element
 */
class Value<out T>(val expected: T): Group<T>{
    override fun toList(): List<T> = listOf(expected)
}

/**
 * Parameter object to express `T, vararg T` in the infix-api.
 */
class Values<out T>(val expected: T, vararg val otherExpected: T): Group<T> {
    override fun toList(): List<T> = expected glue otherExpected
}
