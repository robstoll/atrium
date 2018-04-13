package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import kotlin.reflect.*

/**
 * Creates an [AssertionPlant] for the property provided by the given [property].
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the provided property, to the current plant.
 *
 * @return An [AssertionPlant] for the property provided by the given [propertyProvider].
 */
fun <T : Any, TProperty : Any> Assert<T>.property(propertyProvider: () -> KProperty0<TProperty>): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, propertyProvider)

@Deprecated("Use the overload with a propertyProvider, will be removed with 1.0.0", ReplaceWith("property({ property })"))
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property.name, property)


/**
 * Creates an [AssertionPlant] for the property provided by the given [propertyProvider].
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the provided property, to the current plant.
 * It starts of with adding a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the property provided by the given [propertyProvider].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, TProperty : Any> Assert<T>.property(propertyProvider: () -> KProperty0<TProperty>, assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, propertyProvider, assertionCreator)

@Deprecated("Use the overload with a propertyProvider, will be removed with 1.0.0", ReplaceWith("property({ property }, assertionCreator)"))
fun <T : Any, TProperty : Any> Assert<T>.property(property: KProperty0<TProperty>, assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = AssertImpl.feature.property(this, property.name, property, assertionCreator)


/**
 * Creates an [AssertionPlantNullable] for the property provided by the given [propertyProvider].
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the provided property, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the property provided by the given [propertyProvider].
 */
fun <T : Any, TProperty : Any?> Assert<T>.property(propertyProvider: () -> KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = AssertImpl.feature.property(this, propertyProvider)

@Deprecated("Use the overload with a propertyProvider, will be removed with 1.0.0", ReplaceWith("property({ property })"))
fun <T : Any, TProperty : Any?> Assert<T>.property(property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = AssertImpl.feature.property(this, property.name, property)



// arg0 -----------------------------------------

/**
 * Creates an [AssertionPlant] for the value returned by calling the method which [methodProvider] provides.
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, TReturnValue : Any> Assert<T>.returnValueOf(methodProvider: () -> KFunction0<TReturnValue>): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf0(this, methodProvider)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf({ method })"))
fun <T : Any, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction0<TReturnValue>): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf0(this, method.name, method)


/**
 * Creates an [AssertionPlant] for the value returned by calling the method which [methodProvider] provides.
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 * It starts of with adding a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, TReturnValue : Any> Assert<T>.returnValueOf(methodProvider: () -> KFunction0<TReturnValue>, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf0(this, methodProvider, assertionCreator)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf({ method }, assertionCreator)"))
fun <T : Any, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction0<TReturnValue>, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf0(this, method.name, method, assertionCreator)


/**
 * Creates an [AssertionPlantNullable] for the value returned by calling the method which [methodProvider] provides.
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlantNullable] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, TReturnValue : Any?> Assert<T>.returnValueOf(methodProvider: () -> KFunction0<TReturnValue>): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf0(this, methodProvider)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf({ method })"))
fun <T : Any, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction0<TReturnValue>): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf0(this, method.name, method)


// arg1 -----------------------------------------

/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1].
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
 * @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, T1 : Any?, TReturnValue : Any> Assert<T>.returnValueOf1(methodProvider: () -> KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf1(this, methodProvider, arg1)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf1({ method }, arg1)"))
fun <T : Any, T1 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf1(this, method.name, method, arg1)


/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1].
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 * It starts of with adding a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
 * @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, TReturnValue : Any> Assert<T>.returnValueOf1(methodProvider: () -> KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf1(this, methodProvider, arg1, assertionCreator)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf1({ method }, arg1, assertionCreator)"))
fun <T : Any, T1 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf1(this, method.name, method, arg1, assertionCreator)


/**
 * Creates an [AssertionPlantNullable] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1].
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
* @return An [AssertionPlantNullable] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, T1 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf1(methodProvider: () -> KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf1(this, methodProvider, arg1)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf1({ method }, arg1)"))
fun <T : Any, T1 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf1(this, method.name, method, arg1)


// arg2 -----------------------------------------

/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1] and [arg2]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
* @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> Assert<T>.returnValueOf2(methodProvider: () -> KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf2(this, methodProvider, arg1, arg2)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf2({ method }, arg1, arg2)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf2(this, method.name, method, arg1, arg2)


/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1] and [arg2]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 * It starts of with adding a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
* @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> Assert<T>.returnValueOf2(methodProvider: () -> KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf2(this, methodProvider, arg1, arg2, assertionCreator)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf2({ method }, arg1, arg2, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf2(this, method.name, method, arg1, arg2, assertionCreator)


/**
 * Creates an [AssertionPlantNullable] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1] and [arg2]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
* @return An [AssertionPlantNullable] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf2(methodProvider: () -> KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf2(this, methodProvider, arg1, arg2)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf2({ method }, arg1, arg2)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf2(this, method.name, method, arg1, arg2)


// arg3 -----------------------------------------

/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1], [arg2] and [arg3]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
* @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> Assert<T>.returnValueOf3(methodProvider: () -> KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf3(this, methodProvider, arg1, arg2, arg3)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf3({ method }, arg1, arg2, arg3)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf3(this, method.name, method, arg1, arg2, arg3)


/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1], [arg2] and [arg3]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 * It starts of with adding a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
* @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> Assert<T>.returnValueOf3(methodProvider: () -> KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf3(this, methodProvider, arg1, arg2, arg3, assertionCreator)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf3({ method }, arg1, arg2, arg3, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf3(this, method.name, method, arg1, arg2, arg3, assertionCreator)


/**
 * Creates an [AssertionPlantNullable] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1], [arg2] and [arg3]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
* @return An [AssertionPlantNullable] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf3(methodProvider: () -> KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf3(this, methodProvider, arg1, arg2, arg3)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf3({ method }, arg1, arg2, arg3)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf3(this, method.name, method, arg1, arg2, arg3)


// arg4 -----------------------------------------

/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1], [arg2], [arg3] and [arg4]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
* @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> Assert<T>.returnValueOf4(methodProvider: () -> KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf4(this, methodProvider, arg1, arg2, arg3, arg4)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf4({ method }, arg1, arg2, arg3, arg4)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf4(this, method.name, method, arg1, arg2, arg3, arg4)


/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1], [arg2], [arg3] and [arg4]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 * It starts of with adding a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
* @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> Assert<T>.returnValueOf4(methodProvider: () -> KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf4(this, methodProvider, arg1, arg2, arg3, arg4, assertionCreator)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf4({ method }, arg1, arg2, arg3, arg4, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf4(this, method.name, method, arg1, arg2, arg3, arg4, assertionCreator)


/**
 * Creates an [AssertionPlantNullable] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1], [arg2], [arg3] and [arg4]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
* @return An [AssertionPlantNullable] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf4(methodProvider: () -> KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf4(this, methodProvider, arg1, arg2, arg3, arg4)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf4({ method }, arg1, arg2, arg3, arg4)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf4(this, method.name, method, arg1, arg2, arg3, arg4)


// arg5 -----------------------------------------

/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1], [arg2], [arg3], [arg4] and [arg5]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
* @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> Assert<T>.returnValueOf5(methodProvider: () -> KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf5(this, methodProvider, arg1, arg2, arg3, arg4, arg5)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf5({ method }, arg1, arg2, arg3, arg4, arg5)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf5(this, method.name, method, arg1, arg2, arg3, arg4, arg5)


/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1], [arg2], [arg3], [arg4] and [arg5]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 * It starts of with adding a group consisting of the [Assertion]s created by the [assertionCreator] lambda.
 *
* @return An [AssertionPlant] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 *
 * @throws AssertionError Might throw an [AssertionError] if an additionally created [Assertion]
 *   (by calling [assertionCreator]) does not hold.
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> Assert<T>.returnValueOf5(methodProvider: () -> KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf5(this, methodProvider, arg1, arg2, arg3, arg4, arg5, assertionCreator)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf5({ method }, arg1, arg2, arg3, arg4, arg5, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AssertImpl.feature.returnValueOf5(this, method.name, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)


/**
 * Creates an [AssertionPlant] for the value returned by calling the method, which [methodProvider] provides,
 * with [arg1], [arg2], [arg3], [arg4] and [arg5]
 * The plant eventually adds [AssertionGroup]s with a [FeatureAssertionGroupType], containing the assertions
 * created for the return value, to the current plant.
 *
* @return An [AssertionPlantNullable] for the return value resulting from the call to the method provided
 *   by given [methodProvider].
 */
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf5(methodProvider: () -> KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf5(this, methodProvider, arg1, arg2, arg3, arg4, arg5)

@Deprecated("Use the overload with a methodProvider, will be removed with 1.0.0", ReplaceWith("returnValueOf5({ method }, arg1, arg2, arg3, arg4, arg5)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> Assert<T>.returnValueOf(method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = AssertImpl.feature.returnValueOf5(this, method.name, method, arg1, arg2, arg3, arg4, arg5)



