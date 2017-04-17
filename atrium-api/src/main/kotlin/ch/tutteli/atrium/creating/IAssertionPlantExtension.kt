package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

/**
 * Uses `this` plant as receiver of the given [createAssertions] function and
 * then calls [IAssertionPlant.checkAssertions].
 *
 * @return This plant to support a fluent-style API.
 *
 * @throws AssertionError Might throw an [AssertionError] in case a created [IAssertion] does not hold.
 */
inline fun <T : Any> IAssertionPlant<T>.createAssertionsAndCheckThem(createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T> {
    createAssertions()
    checkAssertions()
    return this
}
