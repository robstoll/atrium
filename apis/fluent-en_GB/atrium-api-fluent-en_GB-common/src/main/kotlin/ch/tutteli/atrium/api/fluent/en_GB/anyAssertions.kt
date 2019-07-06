@file:JvmMultifileClass
@file:JvmName("AnyAssertionsKt")
package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.Reporter
import kotlin.js.JsName
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Expects that the subject of the assertion is (equal to) [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T: Any> Expect<T>.toBe(expected: T)
    = addAssertion(ExpectImpl.any.toBe(this, expected))

/**
 * Expects that the subject of the assertion is (equal to) [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("toBeNullable")
@JsName("toBeNullable")
inline fun <reified T : Any> Expect<T?>.toBe(expected: T?) : Expect<T?>
    = addAssertion(ExpectImpl.any.toBeNullable(this, T::class, expected))

/**
 * Expects that the subject of the assertion is not (equal to) [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T> Expect<T>.notToBe(expected: T)
    = addAssertion(ExpectImpl.any.notToBe(this, expected))

/**
 * Expects that the subject of the assertion is the same instance as [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T> Expect<T>.isSameAs(expected: T)
    = addAssertion(ExpectImpl.any.isSame(this, expected))

/**
 * Expects that the subject of the assertion is not the same instance as [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T> Expect<T>.isNotSameAs(expected: T)
    = addAssertion(ExpectImpl.any.isNotSame(this, expected))

/**
 * Expects that the subject of the assertion is either `null` in case [assertionCreatorOrNull]
 * is `null` or is not `null` and holds all assertions [assertionCreatorOrNull] might create.
 *
 * Depending on the implementation, it is not much more than a shortcut for
 * ```kotlin
 * if (assertionCreatorOrNull == null) toBe(null)
 * else notToBeNull(assertionCreatorOrNull)
 * ```
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
inline fun <reified T : Any> Expect<T?>.toBeNullIfNullGivenElse(noinline assertionCreatorOrNull: (Expect<T>.() -> Unit)?) =
    addAssertion(ExpectImpl.any.toBeNullIfNullGivenElse(this, T::class, assertionCreatorOrNull))

/**
 * Can be used to separate single assertions.
 *
 * For instance `expect(1).isLessThan(2).and.isGreaterThan(0)` creates
 * two assertions (not one assertion with two sub-assertions) - the first asserts that 1 is less than 2 and the second
 * asserts that 1 is greater than 0. If the first assertion fails, then usually (depending on the configured
 * [AssertionChecker]) the second assertion is not evaluated.
 *
 * @return This assertion container to support a fluent API.
 */
inline val <T> Expect<T>.and: Expect<T> get() = this

/**
 * Can be used to create a group of sub assertions when using the fluent API.
 *
 * For instance `assert(1).isLessThan(3).and { isEven(); isGreaterThan(1) }` creates
 * two assertions where the second one consists of two sub-assertions. In case the first assertion holds, then the
 * second one is evaluated as a whole. Meaning, even though 1 is not even, it still evaluates that 1 is greater than 1.
 * Hence the reporting might (depending on the configured [Reporter]) contain both failing sub-assertions.
 *
 * @return This plant to support a fluent API.
 */
infix fun <T> Expect<T>.and(assertionCreator: Expect<T>.() -> Unit)
    = addAssertionsCreatedBy(assertionCreator)
