package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

/**
 * Expects that the property [Throwable.message] of the subject of the assertion is not null,
 * creates an [Expect] for it and returns it.
 *
 * @return The newly created [Expect] for the property [Throwable.message] of the subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
val <T : Throwable> Expect<T>.message: Expect<String>
    get() = o feature Throwable::message notToBeNull O

/**
 * Expects that the property [Throwable.message] of the subject of the assertion is not null and
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Throwable> Expect<T>.message(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    o feature of(Throwable::message) { o notToBeNull assertionCreator }

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
    this messageContains Values(expected)

/**
 * Expects that the property [Throwable.message] of the subject of the assertion is not null and contains
 * [values]'s [toString] representation using a non disjoint search.
 **
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed
 * (this function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Throwable> Expect<T>.messageContains(values: Values<Any>): Expect<T> =
    message { contains(values) }
