package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import kotlin.reflect.*

/**
 * Creates an [IAssertionPlant] for the given [property] which eventually adds [IAssertionGroup]s with a
 * [IFeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, TProperty : Any> IAssertionPlant<T>.property(property: KProperty0<TProperty>): IAssertionPlant<TProperty>
    = _property(this, property)

/**
 * Creates an [IAssertionPlant] for the given [property] which eventually adds [IAssertionGroup]s with a
 * [IFeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant --
 * starting with a group consisting of the [IAssertion]s created by the [assertionCreator] lambda.
 *
 * @return An [IAssertionPlant] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, TProperty : Any> IAssertionPlant<T>.property(property: KProperty0<TProperty>, assertionCreator: IAssertionPlant<TProperty>.() -> Unit): IAssertionPlant<TProperty>
    = _property(this, property, assertionCreator)


/**
 * Creates an [IAssertionPlantNullable] for the given [property] which eventually adds [IAssertionGroup]s with a
 * [IFeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [IAssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, TProperty : Any?> IAssertionPlant<T>.property(property: KProperty0<TProperty>): IAssertionPlantNullable<TProperty>
    = _property(this, property)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method], which eventually adds [IAssertionGroup]s
 * with a [IFeatureAssertionGroupType], containing the assertions created for the return value, to the current plant.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction0<TReturnValue>): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method], which eventually adds
 * [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [IAssertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction0<TReturnValue>, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, assertionCreator)

/**
 * Creates an [IAssertionPlantNullable], for the value returned by calling [method], which eventually adds
 * [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [IAssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction0<TReturnValue>): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [IAssertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, assertionCreator)

/**
 * Creates an [IAssertionPlantNullable], for the value returned by calling [method] with [arg1], which eventually adds
 * [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [IAssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [IAssertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, assertionCreator)

/**
 * Creates an [IAssertionPlantNullable], for the value returned by calling [method] with [arg1] and [arg2], which
 * eventually adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [IAssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2)


/**
 * Creates an [IAssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [IAssertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, assertionCreator)

/**
 * Creates an [IAssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [IAssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [IAssertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, assertionCreator)

/**
 * Creates an [IAssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3]
 * and [arg4], which eventually adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [IAssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, arg5)

/**
 * Creates an [IAssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant -- starting with a group consisting of the [IAssertion]s created
 * by the [assertionCreator] lambda.
 *
 * @return An [IAssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [IAssertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> IAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

/**
 * Creates an [IAssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3],
 * [arg4] and [arg5], which eventually adds [IAssertionGroup]s with a [IFeatureAssertionGroupType], containing the
 * assertions created for the return value, to the current plant.
 *
 * @return An [IAssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> IAssertionPlant<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): IAssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, arg5)


