package ch.tutteli.atrium.creating

import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3
import kotlin.reflect.KFunction4
import kotlin.reflect.KFunction5
import kotlin.reflect.KProperty0

/**
 * A dummy implementation of [IFeatureAssertions] which should be replaced by an actual implementation.
 */
object FeatureAssertions : IFeatureAssertions {
    override fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlant<TProperty>
        = throwUnsupportedOperationException()
    override fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, property: KProperty0<TProperty>, assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
        = throwUnsupportedOperationException()
    override fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
        = throwUnsupportedOperationException()

    //Arg0
    override fun <T : Any, TReturnValue : Any> returnValueOf0(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, TReturnValue : Any> returnValueOf0(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, TReturnValue : Any> returnValueOf0(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, TReturnValue : Any> returnValueOf0(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, TReturnValue : Any?> returnValueOf0(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, TReturnValue : Any?> returnValueOf0(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()

    //Arg1
    override fun <T : Any, T1 : Any?, TReturnValue : Any> returnValueOf1(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, TReturnValue : Any> returnValueOf1(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, TReturnValue : Any> returnValueOf1(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, TReturnValue : Any> returnValueOf1(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, TReturnValue : Any?> returnValueOf1(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, TReturnValue : Any?> returnValueOf1(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()

    //Arg2
    override fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> returnValueOf2(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> returnValueOf2(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> returnValueOf2(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> returnValueOf2(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> returnValueOf2(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> returnValueOf2(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()

    //Arg3
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> returnValueOf3(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> returnValueOf3(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> returnValueOf3(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> returnValueOf3(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> returnValueOf3(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()
   override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> returnValueOf3(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()

    //Arg4
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> returnValueOf4(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> returnValueOf4(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> returnValueOf4(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> returnValueOf4(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> returnValueOf4(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> returnValueOf4(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()

    //Arg5
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> returnValueOf5(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> returnValueOf5(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> returnValueOf5(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> returnValueOf5(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> returnValueOf5(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()
    override fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> returnValueOf5(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
        = throwUnsupportedOperationException()
}
