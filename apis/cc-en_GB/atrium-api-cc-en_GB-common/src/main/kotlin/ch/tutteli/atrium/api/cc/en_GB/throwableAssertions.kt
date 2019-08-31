@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown

/**
 *  Makes the assertion that the thrown [Throwable] is of type [TExpected] and holds all assertions the
 * [assertionCreator] might create in addition.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion (a [Throwable] was thrown) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.toThrow<TExpected>().asAssert(assertionCreator)",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.api.fluent.en_GB.toThrow"
    )
)
inline fun <reified TExpected : Throwable> ThrowableThrown.Builder.toThrow(noinline assertionCreator: Assert<TExpected>.() -> Unit) {
        AssertImpl.throwable.thrown.toBe(this, TExpected::class, assertionCreator)
}

/**
 * Makes the assertion that no [Throwable] is thrown at all.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because we assume nothing happens,
 *   so there is nothing we could make assertions on in addition.
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.notToThrow()",
        "ch.tutteli.atrium.api.fluent.en_GB.notToThrow"
    )
)
fun ThrowableThrown.Builder.notToThrow(){
        AssertImpl.throwable.thrown.nothingThrown(this)
}

/**
 * Creates an [AssertionPlantNullable] for the [message][Throwable.message] of the plant's
 * [subject][SubjectProvider.subject] (which is a [Throwable]) and makes the assertion that message [notToBeNull]
 * and uses [assertionCreator] which might create further [Assertion]s which are lazily evaluated at the end.
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion [message][Throwable.message] is not null) holds or not.
 * Define subsequent assertions via the [assertionCreator] lambda.
 *
 * @throws AssertionError Might throw an [AssertionError] in case [message][Throwable.message] is `null`
 *   or if an additionally created [Assertion]s (by calling [assertionCreator]) does not hold.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().message.asAssert(assertionCreator)",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.message"
    )
)
fun <T : Throwable> Assert<T>.message(assertionCreator: Assert<String>.() -> Unit) {
    property(Throwable::message).notToBeNull(assertionCreator)
}

/**
 * Creates the assertion that the [Throwable]'s [message][Throwable.message] is not null (see [message]) contains
 * [expected]'s [toString] representation and the [toString] representation of the [otherExpected] (if given),
 * using a non disjoint search.
 *
 * It is a shortcut for `message { contains.atLeast(1).values(expected, otherExpected) }`
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed
 * (this function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return Notice, that this assertion function cannot provide a fluent API because it depends on whether the first
 *   assertion [message][Throwable.message] is not null) holds or not.
 *
 * @throws AssertionError Might throw an [AssertionError] in case [message][Throwable.message] is `null`
 *   or does not contain [expected] or [otherExpected].
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().messageContains(expected, *otherExpected)",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.messageContains"
    )
)
fun <T : Throwable> Assert<T>.messageContains(expected: Any, vararg otherExpected: Any) {
    message { contains(expected, *otherExpected) }
}
