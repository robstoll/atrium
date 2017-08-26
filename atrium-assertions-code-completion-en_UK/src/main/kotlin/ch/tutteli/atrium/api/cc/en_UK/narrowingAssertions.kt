package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions._isA
import ch.tutteli.atrium.assertions._isNotNull
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable

/**
 * Makes the assertion that [IAssertionPlant.subject] is not null.
 *
 * @return This plant to support a fluent API.
 */
inline fun <reified T : Any> IAssertionPlantNullable<T?>.isNotNull(): IAssertionPlant<T>
    = _isNotNull(this)

/**
 * Makes the assertion that [IAssertionPlant.subject] is not null and if so, uses [createAssertions]
 * which could create further assertions which are lazily evaluated at the end.
 *
 * @return This plant to support a fluent API.
 */
inline fun <reified T : Any> IAssertionPlantNullable<T?>.isNotNull(noinline createAssertions: IAssertionPlant<T>.() -> Unit) : IAssertionPlant<T>
    = _isNotNull(this, createAssertions)

/**
 * Makes the assertion that [IAssertionPlant.subject] *is a* [TSub] (the same type or a sub-type).
 *
 * @return This plant to support a fluent API.
 */
inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(): IAssertionPlant<TSub>
    = _isA(this)

/**
 * Makes the assertion that [IAssertionPlant.subject] *is a* [TSub] (the same type or a sub-type) and if so,
 * uses [createAssertions] which could create further assertions which are lazily evaluated at the end.
 *
 * @return This plant to support a fluent API.
 */
inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(noinline createAssertions: IAssertionPlant<TSub>.() -> Unit): IAssertionPlant<TSub>
    = _isA(this, createAssertions)
