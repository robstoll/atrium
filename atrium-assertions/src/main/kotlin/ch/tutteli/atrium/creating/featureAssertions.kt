@file:JvmName("DeprecatedFeatureAssertions")
package ch.tutteli.atrium.creating

import kotlin.reflect.*

@Deprecated("use FeatureAssertions.property instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.property(plant, property)"))
fun <T : Any, TProperty : Any> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = FeatureAssertions.property(plant, property)

@Deprecated("use FeatureAssertions.property instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.property(plant, property, assertionCreator)"))
fun <T : Any, TProperty : Any> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>, assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = FeatureAssertions.property(plant, property, assertionCreator)

@Deprecated("use FeatureAssertions.property instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.property(plant, property)"))
fun <T : Any, TProperty : Any?> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = FeatureAssertions.property(plant, property)

//Arg0
@Deprecated("use FeatureAssertions.returnValueOf0 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf0(plant, method)"))
fun <T : Any, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf0(plant, method)

@Deprecated("use FeatureAssertions.returnValueOf0 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf0(plant, name, method)"))
fun <T : Any, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf0(plant, name, method)

@Deprecated("use FeatureAssertions.returnValueOf0 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf0(plant, method, assertionCreator)"))
fun <T : Any, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf0(plant, method, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf0 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf0(plant, name, method, assertionCreator)"))
fun <T : Any, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf0(plant, name, method, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf0 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf0(plant, method)"))
fun <T : Any, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf0(plant, method)

@Deprecated("use FeatureAssertions.returnValueOf0 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf0(plant, name, method)"))
fun <T : Any, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf0(plant, name, method)


//Arg1
@Deprecated("use FeatureAssertions.returnValueOf1 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf1(plant, method, arg1)"))
fun <T : Any, T1 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf1(plant, method, arg1)

@Deprecated("use FeatureAssertions.returnValueOf1 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf1(plant, name, method, arg1)"))
fun <T : Any, T1 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf1(plant, name, method, arg1)

@Deprecated("use FeatureAssertions.returnValueOf1 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf1(plant, method, arg1, assertionCreator)"))
fun <T : Any, T1 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf1(plant, method, arg1, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf1 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf1(plant, name, method, arg1, assertionCreator)"))
fun <T : Any, T1 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf1(plant, name, method, arg1, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf1 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf1(plant, method, arg1)"))
fun <T : Any, T1 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf1(plant, method, arg1)

@Deprecated("use FeatureAssertions.returnValueOf1 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf1(plant, name, method, arg1)"))
fun <T : Any, T1 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf1(plant, name, method, arg1)


//Arg2
@Deprecated("use FeatureAssertions.returnValueOf2 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf2(plant, method, arg1, arg2)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf2(plant, method, arg1, arg2)

@Deprecated("use FeatureAssertions.returnValueOf2 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf2(plant, name, method, arg1, arg2)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf2(plant, name, method, arg1, arg2)

@Deprecated("use FeatureAssertions.returnValueOf2 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf2(plant, method, arg1, arg2, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf2(plant, method, arg1, arg2, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf2 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf2(plant, name, method, arg1, arg2, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf2(plant, name, method, arg1, arg2, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf2 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf2(plant, method, arg1, arg2)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf2(plant, method, arg1, arg2)

@Deprecated("use FeatureAssertions.returnValueOf2 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf2(plant, name, method, arg1, arg2)"))
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf2(plant, name, method, arg1, arg2)


//Arg3
@Deprecated("use FeatureAssertions.returnValueOf3 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf3(plant, method, arg1, arg2, arg3)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf3(plant, method, arg1, arg2, arg3)

@Deprecated("use FeatureAssertions.returnValueOf3 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf3(plant, name, method, arg1, arg2, arg3)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf3(plant, name, method, arg1, arg2, arg3)

@Deprecated("use FeatureAssertions.returnValueOf3 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf3(plant, method, arg1, arg2, arg3, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf3(plant, method, arg1, arg2, arg3, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf3 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf3(plant, name, method, arg1, arg2, arg3, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf3(plant, name, method, arg1, arg2, arg3, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf3 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf3(plant, method, arg1, arg2, arg3)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf3(plant, method, arg1, arg2, arg3)

@Deprecated("use FeatureAssertions.returnValueOf3 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf3(plant, name, method, arg1, arg2, arg3)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf3(plant, name, method, arg1, arg2, arg3)


//Arg4
@Deprecated("use FeatureAssertions.returnValueOf4 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf4(plant, method, arg1, arg2, arg3, arg4)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf4(plant, method, arg1, arg2, arg3, arg4)

@Deprecated("use FeatureAssertions.returnValueOf4 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)

@Deprecated("use FeatureAssertions.returnValueOf4 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf4(plant, method, arg1, arg2, arg3, arg4, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf4(plant, method, arg1, arg2, arg3, arg4, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf4 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf4 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf4(plant, method, arg1, arg2, arg3, arg4)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf4(plant, method, arg1, arg2, arg3, arg4)

@Deprecated("use FeatureAssertions.returnValueOf4 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf4(plant, name, method, arg1, arg2, arg3, arg4)


//Arg5
@Deprecated("use FeatureAssertions.returnValueOf5 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5)

@Deprecated("use FeatureAssertions.returnValueOf5 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)

@Deprecated("use FeatureAssertions.returnValueOf5 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf5 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = FeatureAssertions.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

@Deprecated("use FeatureAssertions.returnValueOf5 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5)

@Deprecated("use FeatureAssertions.returnValueOf5 instead, will be removed with 1.0.0", ReplaceWith("FeatureAssertions.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)"))
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = FeatureAssertions.returnValueOf5(plant, name, method, arg1, arg2, arg3, arg4, arg5)
