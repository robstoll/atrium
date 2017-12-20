package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.reporting.IReporter

/**
 * Makes the assertion that [IAssertionPlant.subject] is (equals) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).toBe(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Any> Assert<T>.toBe(expected: T): IAssertionPlant<T>
    = addAssertion(_toBe(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is not (does not equal) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).notToBe(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Any> Assert<T>.notToBe(expected: T): IAssertionPlant<T>
    = addAssertion(_notToBe(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).isSame(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Any> Assert<T>.isSame(expected: T): IAssertionPlant<T>
    = addAssertion(_isSame(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is not the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).isNotSame(1.0)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Any> Assert<T>.isNotSame(expected: T): IAssertionPlant<T>
    = addAssertion(_isNotSame(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] is `null`.
 *
 * @return Does not support a fluent API because: what else would you want to assert about `null` anyway?
 *
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Any?> IAssertionPlantNullable<T>.isNull() {
    addAssertion(_isNull(this))
}

/**
 * Can be used to separate assertions when using the fluent API.
 *
 * For instance `assert(1).isLessThan(2).and.isGreaterThan(0)` creates
 * two assertions (not one assertion with two sub-assertions) - the first asserts that 1 is less than 2 and a second
 * asserts that 1 is greater than 0. If the first assertion fails, then usually (depending on the configured
 * [IAssertionChecker]) the second assertion is not evaluated.
 *
 * @return This plant to support a fluent API.
 */
val <T : Any> IAssertionPlant<T>.and: IAssertionPlant<T> get() = this

/**
 * Can be used to create a group of sub assertions when using the fluent API.
 *
 * For instance `assert(1).isLessThan(3).and { isEven(); isGreaterThan(1) }` creates
 * two assertions where the second one consists of two sub-assertions. In case the first assertion holds, then the
 * second one is evaluated as a whole. Meaning, even though 1 is not even, it still evaluates that 1 is greater than 1.
 * Hence the reporting might (depending on the configured [IReporter]) contain both failing sub-assertions.
 *
 * @return This plant to support a fluent API.
 */
infix fun <T : Any> IAssertionPlant<T>.and(assertionCreator: IAssertionPlant<T>.() -> Unit)
    = addAssertionsCreatedBy(assertionCreator)
