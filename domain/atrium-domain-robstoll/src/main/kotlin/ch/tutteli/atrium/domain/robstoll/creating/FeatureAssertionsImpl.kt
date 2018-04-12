package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.FeatureAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import kotlin.reflect.*

/**
 * Robstoll's implementation of [FeatureAssertions].
 */
class FeatureAssertionsImpl : FeatureAssertions {

    override fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, name: String, property: () -> TProperty): AssertionPlant<TProperty>
        = _property(plant, name, property)

    override fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, name: String, property: () -> TProperty): AssertionPlantNullable<TProperty>
        = _property(plant, name, property)

    //Arg0
    override fun <T : Any, TReturnValue : Any> returnValueOf0(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue): AssertionPlant<TReturnValue>
        = _returnValueOf0(plant, name, method)

    override fun <T : Any, TReturnValue : Any?> returnValueOf0(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue): AssertionPlantNullable<TReturnValue>
        = _returnValueOf0(plant, name, method)

    //Arg1
    override fun <T : Any, T1 : Any?, TReturnValue : Any> returnValueOf1(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1): AssertionPlant<TReturnValue>
        = _returnValueOf1(plant, name, method, arg1)

    override fun <T : Any, T1 : Any?, TReturnValue : Any?> returnValueOf1(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1): AssertionPlantNullable<TReturnValue>
        = _returnValueOf1(plant, name, method, arg1)

    //Arg2
    override fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> returnValueOf2(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
        = _returnValueOf2(plant, name, method, arg1, arg2)

    override fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> returnValueOf2(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
        = _returnValueOf2(plant, name, method, arg1, arg2)

    //Arg3
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> returnValueOf3(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
        = _returnValueOf3(plant, name, method, arg1, arg2, arg3)
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> returnValueOf3(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
        = _returnValueOf3(plant, name, method, arg1, arg2, arg3)

    //Arg4
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> returnValueOf4(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
        = _returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)

    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> returnValueOf4(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
        = _returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)

    //Arg5
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> returnValueOf5(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
        = _returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)

    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> returnValueOf5(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
        = _returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)
}
