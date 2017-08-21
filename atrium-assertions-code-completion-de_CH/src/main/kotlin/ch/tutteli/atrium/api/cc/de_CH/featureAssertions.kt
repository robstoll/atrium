package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions._property
import ch.tutteli.atrium.assertions._returnValueOf
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import kotlin.reflect.*

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the given [property] as
 * [subject][IAssertionPlant.subject].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, TProperty : Any> IAssertionPlant<T>.property(property: KProperty0<TProperty>): IAssertionPlant<TProperty>
    = _property(this, property)

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the given [property] as
 * [subject][IAssertionPlant.subject].
 *
 * The given [createAssertions] function is called after the plant has been created. It could create
 * [IAssertion]s for the given [property] which are lazily evaluated by the newly created [IAssertionPlant]
 * after the call to [createAssertions] is made.
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, TProperty : Any> IAssertionPlant<T>.property(property: KProperty0<TProperty>, createAssertions: IAssertionPlant<TProperty>.() -> Unit): IAssertionPlant<TProperty>
    = _property(this, property, createAssertions)


/**
 * Creates an [IAssertionPlantNullable] using the given [property] as [subject][IAssertionPlantNullable.subject].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, TProperty : Any?> IAssertionPlant<T>.property(property: KProperty0<TProperty>): IAssertionPlantNullable<TProperty>
    = _property(this, property)

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction0<TReturnValue>): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method)

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject].
 *
 * The given [createAssertions] function is called after the plant has been created. It could create
 * [IAssertion]s for the given [property] which are lazily evaluated by the newly created [IAssertionPlant]
 * after the call to [createAssertions] is made.
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction0<TReturnValue>, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, TReturnValue : Any?> IAssertionPlant<T>.rueckgabewertVon(method: KFunction0<TReturnValue>): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method)

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, T1 : Any?, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction1<T1, TReturnValue>, arg1: T1): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1)

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1].
 *
 * The given [createAssertions] function is called after the plant has been created. It could create
 * [IAssertion]s for the given [property] which are lazily evaluated by the newly created [IAssertionPlant]
 * after the call to [createAssertions] is made.
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, T1 : Any?, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction1<T1, TReturnValue>, arg1: T1, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject] with [arg1].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, T1 : Any?, TReturnValue : Any?> IAssertionPlant<T>.rueckgabewertVon(method: KFunction1<T1, TReturnValue>, arg1: T1): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1)

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1] and [arg2].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2)

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1] and [arg2].
 *
 * The given [createAssertions] function is called after the plant has been created. It could create
 * [IAssertion]s for the given [property] which are lazily evaluated by the newly created [IAssertionPlant]
 * after the call to [createAssertions] is made.
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject] with [arg1] and [arg2].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> IAssertionPlant<T>.rueckgabewertVon(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2)


/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1], [arg2] and [arg3].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3)

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1], [arg2] and [arg3].
 *
 * The given [createAssertions] function is called after the plant has been created. It could create
 * [IAssertion]s for the given [property] which are lazily evaluated by the newly created [IAssertionPlant]
 * after the call to [createAssertions] is made.
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject] with [arg1], [arg2] and [arg3].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> IAssertionPlant<T>.rueckgabewertVon(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3)

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1], [arg2], [arg3] and [arg4].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4)

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1], [arg2], [arg3] and [arg4].
 *
 * The given [createAssertions] function is called after the plant has been created. It could create
 * [IAssertion]s for the given [property] which are lazily evaluated by the newly created [IAssertionPlant]
 * after the call to [createAssertions] is made.
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject] with [arg1], [arg2], [arg3] and [arg4].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> IAssertionPlant<T>.rueckgabewertVon(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4)

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1], [arg2], [arg3], [arg4] and [arg5].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, arg5)

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1], [arg2], [arg3], [arg4] and [arg5].
 *
 * The given [createAssertions] function is called after the plant has been created. It could create
 * [IAssertion]s for the given [property] which are lazily evaluated by the newly created [IAssertionPlant]
 * after the call to [createAssertions] is made.
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> IAssertionPlant<T>.rueckgabewertVon(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, arg5, createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject] with [arg1], [arg2], [arg3], [arg4] and [arg5].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> IAssertionPlant<T>.rueckgabewertVon(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, arg5)


