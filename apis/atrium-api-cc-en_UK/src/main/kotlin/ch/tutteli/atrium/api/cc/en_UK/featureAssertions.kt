package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import kotlin.reflect.*

/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlant] for the given [property].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.property(property)"))
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property)

/**
 * Creates an [AssertionPlant] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant --
 * starting with a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the given [property].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.property(property, assertionCreator)"))
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty0<TProperty>, assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property, assertionCreator)


/**
 * Creates an [AssertionPlantNullable] for the given [property] which eventually adds [AssertionGroup]s with a
 * [FeatureAssertionGroupType], containing the assertions created for the given [property], to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.property(property)"))
fun <T : Any, TProperty : Any?> Assert<T>.property(property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = AssertImpl.feature.property(this, property)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds [AssertionGroup]s
 * with a [FeatureAssertionGroupType], containing the assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method)"))
fun <T : Any, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction0<TReturnValue>): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf0(this, method)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, assertionCreator)"))
fun <T : Any, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction0<TReturnValue>, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf0(this, method, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method)"))
fun <T : Any, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction0<TReturnValue>): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf0(this, method)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1)"))
fun <T : Any, T1 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, assertionCreator)"))
fun <T : Any, T1 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf1(this, method, arg1, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], which eventually adds
 * [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1)"))
fun <T : Any, T1 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf1(this, method, arg1)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1] and [arg2], which eventually
 * adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for the return value,
 * to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1] and [arg2], which
 * eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf2(this, method, arg1, arg2)


/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2, arg3)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2, arg3, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2] and [arg3],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created for
 * the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2, arg3)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf3(this, method, arg1, arg2, arg3)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2, arg3, arg4)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3] and [arg4],
 * which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions created
 * for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created by the
 * [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2, arg3, arg4, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3]
 * and [arg4], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2, arg3, arg4)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf4(this, method, arg1, arg2, arg3, arg4)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2, arg3, arg4, arg5)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)

/**
 * Creates an [AssertionPlant], for the value returned by calling [method] with [arg1], [arg2], [arg3], [arg4]
 * and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant -- starting with a group consisting of the [Assertion]s created
 * by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value of the given [method].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2, arg3, arg4, arg5, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

/**
 * Creates an [AssertionPlantNullable], for the value returned by calling [method] with [arg1], [arg2], [arg3],
 * [arg4] and [arg5], which eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the
 * assertions created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the given [property].
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.returnValueOf(method, arg1, arg2, arg3, arg4, arg5)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf5(this, method, arg1, arg2, arg3, arg4, arg5)


