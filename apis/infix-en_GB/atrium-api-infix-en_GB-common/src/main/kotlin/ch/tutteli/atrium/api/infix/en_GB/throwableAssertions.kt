package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Expects that the property [Throwable.message] of the subject of the assertion is not null,
 * creates an [Expect] for it and returns it.
 *
 * @return The newly created [Expect] for the property [Throwable.message] of the subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
val <T : Throwable> Expect<T>.message: Expect<String>
    get() = it feature Throwable::message notToBeNull o

/**
 * Expects that the property [Throwable.message] of the subject of the assertion is not null and
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Throwable> Expect<T>.message(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    it feature of(Throwable::message) { it notToBeNull assertionCreator }

/**
 * Expects that the property [Throwable.message] of the subject of the assertion is not null and contains
 * [expected]'s [toString] representation using a non disjoint search.
 **
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed
 * (this function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Throwable> Expect<T>.messageContains(expected: Any): Expect<T> =
    this messageContains values(expected)

/**
 * Expects that the property [Throwable.message] of the subject of the assertion is not null and contains
 * [values]'s [toString] representation using a non disjoint search.
 **
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed
 * (this function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @param values The values which are expected to be contained within [Throwable.message]
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Throwable> Expect<T>.messageContains(values: Values<Any>): Expect<T> =
    message { contains(values) }

/**
 * Expects that the property [Throwable.cause] of the subject *is a* [TExpected] (the same type or a sub-type),
 * creates an [Expect] of the [TExpected] type for it and returns it.
 *
 * @return The newly created [Expect] for the property [Throwable.cause] of the subject of the assertion
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
inline fun <reified TExpected : Throwable> Expect<out Throwable>.cause(): Expect<TExpected> =
    ExpectImpl.throwable.cause(this, TExpected::class).getExpectOfFeature()

/**
 *
 * Expects that the property [Throwable.cause] of the subject *is a* [TExpected] (the same type or a sub-type) and
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * Notice, in contrast to other assertion functions which expect an [assertionCreator], this function returns not
 * [Expect] of the initial type, which was some type `T `, but an [Expect] of the specified type [TExpected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
inline infix fun <reified TExpected : Throwable> Expect<out Throwable>.cause(
    noinline assertionCreator: Expect<TExpected>.() -> Unit
): Expect<TExpected> =
    ExpectImpl.throwable.cause(this, TExpected::class).addToFeature(assertionCreator)
