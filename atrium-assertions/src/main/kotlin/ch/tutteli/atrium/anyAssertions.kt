package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KProperty0

/**
 * Use this function to make the assertion about the existing property [feature]
 * of the [IAssertionPlant.subject] that the property is `true`.
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : Any> IAssertionPlant<T>.genericCheck(feature: KProperty0<Boolean>)
    = createAndAddAssertion("generic check ${feature.name}", true, { feature.get() })

/**
 * Use this function to make the assertion about the existing property [feature]
 * of the [IAssertionPlant.subject] that the property is `false`.
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : Any> IAssertionPlant<T>.genericNotCheck(feature: KProperty0<Boolean>)
    = createAndAddAssertion("generic check ${feature.name}", true, { !feature.get() })

/**
 * Makes the assertion that [IAssertionPlant.subject] is (equals) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).toBe(1.0)`
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : Any> IAssertionPlant<T>.toBe(expected: T)
    = createAndAddAssertion("to be", expected, { subject == expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is not (does not equal) [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).toBe(1.0)`
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : Any> IAssertionPlant<T>.notToBe(expected: T)
    = createAndAddAssertion("not to be", expected, { subject != expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).toBe(1.0)`
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : Any> IAssertionPlant<T>.isSame(expected: T)
    = createAndAddAssertion("is the same as", expected, { subject === expected })

/**
 * Makes the assertion that [IAssertionPlant.subject] is not the same instance as [expected].
 *
 * This method might enforce in the future, that [expected] has to be the same type as [IAssertionPlant.subject].
 * Currently the following is possible: `assert(1).toBe(1.0)`
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : Any> IAssertionPlant<T>.isNotSame(expected: T)
    = createAndAddAssertion("is not the same as", expected, { subject !== expected })
