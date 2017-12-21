package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import kotlin.reflect.*

/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlant] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = _property(this, property)

/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant --
 * starting with a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty0<TProperty>, assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = _property(this, property, assertionCreator)


/**
 * Creates an [AssertionPlantNullable] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, TProperty : Any?> Assert<T>.property(property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = _property(this, property)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds [AssertionGroup]s
 * with a [FeatureAssertionGroupType], containing the assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction0<TReturnValue>): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction0<TReturnValue>, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction0<TReturnValue>): AssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1] and [arg2], which
 * eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3]
 * and [arg4], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, arg5)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created
 * by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method],
 * using an [AtriumFactory.newFeatureAssertionChecker].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *         (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3],
 * [arg4] and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the
 * assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property], using an [AtriumFactory.newFeatureAssertionChecker].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = _returnValueOf(this, method, arg1, arg2, arg3, arg4, arg5)


