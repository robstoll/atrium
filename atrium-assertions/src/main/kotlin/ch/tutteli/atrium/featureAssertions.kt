package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.createAssertionsAndCheckThem
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.kbox.appendToStringBuilder
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3
import kotlin.reflect.KFunction4
import kotlin.reflect.KFunction5
import kotlin.reflect.KProperty0

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the given [property] as
 * [subject][IAssertionPlant.subject].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, TProperty : Any> IAssertionPlant<T>.property(property: KProperty0<TProperty>): IAssertionPlant<TProperty>
    = AtriumFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(property))

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
    = AtriumFactory.newCheckLazily(createCommonFieldsForFeatureFactory(property))
    .createAssertionsAndCheckThem(createAssertions)


/**
 * Creates an [IAssertionPlantNullable] using the given [property] as [subject][IAssertionPlantNullable.subject].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, TProperty : Any?> IAssertionPlant<T>.property(property: KProperty0<TProperty>): IAssertionPlantNullable<TProperty>
    = AtriumFactory.newNullable(createCommonFieldsForFeatureFactory(property))

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the given [feature] as
 * [subject][IAssertionPlant.subject].
 *
 * Delegates to [property].
 *
 * @return An [IAssertionPlant] for the given [feature], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, TFeature : Any> IAssertionPlant<T>.its(feature: KProperty0<TFeature>): IAssertionPlant<TFeature>
    = property(feature)

/**
 * Creates an [IAssertionPlant] which lazily evaluates [IAssertion]s using the given [feature] as
 * [subject][IAssertionPlant.subject].
 *
 * Delegates to [property], more details are given there.
 *
 * @return An [IAssertionPlant] for the given [feature], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [createAssertions]) does not hold.
 *
 * @see [IAtriumFactory.newCheckLazily]
 */
fun <T : Any, TFeature : Any> IAssertionPlant<T>.its(feature: KProperty0<TFeature>, createAssertions: IAssertionPlant<TFeature>.() -> Unit)
    = property(feature, createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the given [feature] as [subject][IAssertionPlantNullable.subject].
 *
 * Delegates to [property].
 *
 * @return An [IAssertionPlant] for the given [feature], using an [AtriumFactory.newNullable].
 */
fun <T : Any, TFeature : Any?> IAssertionPlant<T>.its(feature: KProperty0<TFeature>)
    = property(feature)

private fun <T : Any, TFeature : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(feature: KProperty0<TFeature>)
    = IAssertionPlantWithCommonFields.CommonFields(Untranslatable(feature.name), feature.get(), AtriumFactory.newFeatureAssertionChecker(this))


/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, TReturnValue: Any> IAssertionPlant<T>.returnValueOf(method: KFunction0<TReturnValue>) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(method))

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
fun <T : Any, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction0<TReturnValue>, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckLazily(createCommonFieldsForFeatureFactory(method))
    .createAssertionsAndCheckThem(createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction0<TReturnValue>): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newNullable(createCommonFieldsForFeatureFactory(method))

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, T1: Any?, TReturnValue: Any> IAssertionPlant<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(method, arg1))

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
fun <T : Any, T1: Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckLazily(createCommonFieldsForFeatureFactory(method, arg1))
    .createAssertionsAndCheckThem(createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject] with [arg1].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, T1: Any?, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newNullable(createCommonFieldsForFeatureFactory(method, arg1))

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1] and [arg2].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, T1: Any?, T2: Any?, TReturnValue: Any> IAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(method, arg1, arg2))

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
fun <T : Any, T1: Any?, T2: Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckLazily(createCommonFieldsForFeatureFactory(method, arg1, arg2))
    .createAssertionsAndCheckThem(createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject] with [arg1] and [arg2].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, T1: Any?, T2: Any?, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newNullable(createCommonFieldsForFeatureFactory(method, arg1, arg2))


/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1], [arg2] and [arg3].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, TReturnValue: Any> IAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3,  TReturnValue>, arg1: T1, arg2: T2, arg3: T3) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))

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
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckLazily(createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))
    .createAssertionsAndCheckThem(createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject] with [arg1], [arg2] and [arg3].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newNullable(createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1], [arg2], [arg3] and [arg4].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, TReturnValue: Any> IAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))

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
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckLazily(createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))
    .createAssertionsAndCheckThem(createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject] with [arg1], [arg2], [arg3] and [arg4].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newNullable(createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))

/**
 * Creates an [IAssertionPlant] which immediately evaluates [IAssertion]s using the value returned by calling
 * [method] of the [subject][IAssertionPlant.subject] with [arg1], [arg2], [arg3], [arg4] and [arg5].
 *
 * @return An [IAssertionPlant] for the given [method], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @See IAtriumFactory.newCheckImmediately
 */
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, T5: Any?, TReturnValue: Any> IAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))

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
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, T5: Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newCheckLazily(createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))
    .createAssertionsAndCheckThem(createAssertions)

/**
 * Creates an [IAssertionPlantNullable] using the value returned by calling [method]
 * of the [subject][IAssertionPlant.subject] with [arg1], [arg2], [arg3], [arg4] and [arg5].
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newNullable].
 */
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, T5: Any?, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newNullable(createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))


private fun <T : Any, TReturnValue : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(method: KFunction<TReturnValue>, vararg arguments: Any?) =
    IAssertionPlantWithCommonFields.CommonFields(
        Untranslatable(createFeatureNameForMethod(method, *arguments)),
        method.call(*arguments),
        AtriumFactory.newFeatureAssertionChecker(this))

private fun createFeatureNameForMethod(method: KFunction<*>, vararg arguments: Any?): () -> String = {
    val sb = StringBuilder(method.name).append("(")
    arguments.asList().appendToStringBuilder(sb, ", ") { it, sb ->
        sb.append(it?.toString() ?: RawString.NULL.string)
    }
    sb.append(")").toString()
}
