@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.FeatureAssertions
import ch.tutteli.atrium.domain.creating.featureAssertions
import java.util.*
import kotlin.reflect.*

/**
 * Delegates inter alia to the implementation of [FeatureAssertions].
 * In detail, it implements [FeatureAssertions] by delegating to [featureAssertions]
 * which in turn delegates to the implementation via [ServiceLoader].
 */
object FeatureAssertionsBuilder : FeatureAssertions {

    inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlant<TProperty>
        = property(plant, property.name, property)

    override inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, name: String, noinline property: () -> TProperty): AssertionPlant<TProperty>
        = featureAssertions.property(plant, name, property)

    inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, property: KProperty0<TProperty>, noinline assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
        = property(plant, property.name, property, assertionCreator)

    inline fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, name: String, noinline property: () -> TProperty, noinline assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
        = property(plant,  name, property).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, TProperty: Any?> property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
        = property(plant, property.name, property)

    override inline fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, name: String, noinline property: () -> TProperty): AssertionPlantNullable<TProperty>
        = featureAssertions.property(plant, name, property)

    //Arg0
    inline fun <T : Any, TReturnValue : Any> returnValueOf0(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlant<TReturnValue>
        = returnValueOf0(plant, method.name, method)

    override inline fun <T : Any, TReturnValue : Any> returnValueOf0(plant: AssertionPlant<T>, name: String, noinline method: () -> TReturnValue): AssertionPlant<TReturnValue>
        = featureAssertions.returnValueOf0(plant, name, method)

    inline fun <T : Any, TReturnValue : Any> returnValueOf0(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf0(plant, method.name, method, assertionCreator)

    inline fun <T : Any, TReturnValue : Any> returnValueOf0(plant: AssertionPlant<T>, name: String, noinline method: () -> TReturnValue, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf0(plant, name, method).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, TReturnValue: Any?> returnValueOf0(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlantNullable<TReturnValue>
        = returnValueOf0(plant, method.name, method)

    override inline fun <T : Any, TReturnValue: Any?> returnValueOf0(plant: AssertionPlant<T>, name: String, noinline method: () -> TReturnValue): AssertionPlantNullable<TReturnValue>
        = featureAssertions.returnValueOf0(plant, name, method)

    //Arg1
    inline fun <T : Any, T1, TReturnValue : Any> returnValueOf1(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlant<TReturnValue>
        = returnValueOf1(plant, method.name, method, arg1)

    override inline fun <T : Any, T1, TReturnValue : Any> returnValueOf1(plant: AssertionPlant<T>, name: String, noinline method: (T1) -> TReturnValue, arg1: T1): AssertionPlant<TReturnValue>
        = featureAssertions.returnValueOf1(plant, name, method, arg1)

    inline fun <T : Any, T1, TReturnValue : Any> returnValueOf1(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf1(plant, method.name, method, arg1, assertionCreator)

    inline fun <T : Any, T1, TReturnValue : Any> returnValueOf1(plant: AssertionPlant<T>, name: String, noinline method: (T1) -> TReturnValue, arg1: T1, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf1(plant, name, method, arg1).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, T1, TReturnValue> returnValueOf1(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlantNullable<TReturnValue>
        = returnValueOf1(plant, method.name, method, arg1)

    override inline fun <T : Any, T1, TReturnValue> returnValueOf1(plant: AssertionPlant<T>, name: String, noinline method: (T1) -> TReturnValue, arg1: T1): AssertionPlantNullable<TReturnValue>
        = featureAssertions.returnValueOf1(plant, name, method, arg1)

    //Arg2
    inline fun <T : Any, T1, T2, TReturnValue : Any> returnValueOf2(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
        = returnValueOf2(plant, method.name, method, arg1, arg2)

    override inline fun <T : Any, T1, T2, TReturnValue : Any> returnValueOf2(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
        = featureAssertions.returnValueOf2(plant, name, method, arg1, arg2)

    inline fun <T : Any, T1, T2, TReturnValue : Any> returnValueOf2(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf2(plant, method.name, method, arg1, arg2, assertionCreator)

    inline fun <T : Any, T1, T2, TReturnValue : Any> returnValueOf2(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf2(plant, name, method, arg1, arg2).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, T1, T2, TReturnValue> returnValueOf2(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
        = returnValueOf2(plant, method.name, method, arg1, arg2)

    override inline fun <T : Any, T1, T2, TReturnValue> returnValueOf2(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
        = featureAssertions.returnValueOf2(plant, name, method, arg1, arg2)

    //Arg3
    inline fun <T : Any, T1, T2, T3, TReturnValue : Any> returnValueOf3(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
        = returnValueOf3(plant, method.name, method, arg1, arg2, arg3)

    override inline fun <T : Any, T1, T2, T3, TReturnValue : Any> returnValueOf3(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
        = featureAssertions.returnValueOf3(plant, name, method, arg1, arg2, arg3)

    inline fun <T : Any, T1, T2, T3, TReturnValue : Any> returnValueOf3(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf3(plant, method.name, method, arg1, arg2, arg3, assertionCreator)

    inline fun <T : Any, T1, T2, T3, TReturnValue : Any> returnValueOf3(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf3(plant, name, method, arg1, arg2, arg3).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, T1, T2, T3, TReturnValue> returnValueOf3(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
        = returnValueOf3(plant, method.name, method, arg1, arg2, arg3)

    override inline fun <T : Any, T1, T2, T3, TReturnValue> returnValueOf3(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
        = featureAssertions.returnValueOf3(plant, name, method, arg1, arg2, arg3)

    //Arg4
    inline fun <T : Any, T1, T2, T3, T4, TReturnValue : Any> returnValueOf4(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
        = returnValueOf4(plant, method.name, method, arg1, arg2, arg3, arg4)

    override inline fun <T : Any, T1, T2, T3, T4, TReturnValue : Any> returnValueOf4(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
        = featureAssertions.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)

    inline fun <T : Any, T1, T2, T3, T4, TReturnValue : Any> returnValueOf4(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf4(plant, method.name, method, arg1, arg2, arg3, arg4, assertionCreator)

    inline fun <T : Any, T1, T2, T3, T4, TReturnValue : Any> returnValueOf4(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, T1, T2, T3, T4, TReturnValue> returnValueOf4(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
        = returnValueOf4(plant, method.name, method, arg1, arg2, arg3, arg4)

    override inline fun <T : Any, T1, T2, T3, T4, TReturnValue> returnValueOf4(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
        = featureAssertions.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)

    //Arg5
    inline fun <T : Any, T1, T2, T3, T4, T5, TReturnValue : Any> returnValueOf5(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
        = returnValueOf5(plant, method.name, method, arg1, arg2, arg3, arg4, arg5)

    override inline fun <T : Any, T1, T2, T3, T4, T5, TReturnValue : Any> returnValueOf5(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
        = featureAssertions.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)

    inline fun <T : Any, T1, T2, T3, T4, T5, TReturnValue : Any> returnValueOf5(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf5(plant, method.name, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

    inline fun <T : Any, T1, T2, T3, T4, T5, TReturnValue : Any> returnValueOf5(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, noinline assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5).addAssertionsCreatedBy(assertionCreator)

    inline fun <T : Any, T1, T2, T3, T4, T5, TReturnValue> returnValueOf5(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
        = returnValueOf5(plant, method.name, method, arg1, arg2, arg3, arg4, arg5)

    override inline fun <T : Any, T1, T2, T3, T4, T5, TReturnValue> returnValueOf5(plant: AssertionPlant<T>, name: String, noinline method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
        = featureAssertions.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)
}
