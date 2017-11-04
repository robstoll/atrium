package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.createAssertionsAndCheckThem
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.*

fun <T : Any, TProperty : Any> _property(plant: IAssertionPlant<T>, property: KProperty0<TProperty>): IAssertionPlant<TProperty>
    = AtriumFactory.newReportingPlantCheckImmediately(plant.createCommonFieldsForFeatureFactory(property))

fun <T : Any, TProperty : Any> _property(plant: IAssertionPlant<T>, property: KProperty0<TProperty>, createAssertions: IAssertionPlant<TProperty>.() -> Unit): IAssertionPlant<TProperty>
    = AtriumFactory.newReportingPlantCheckLazily(plant.createCommonFieldsForFeatureFactory(property))
    .createAssertionsAndCheckThem(createAssertions)

fun <T : Any, TProperty : Any?> _property(plant: IAssertionPlant<T>, property: KProperty0<TProperty>): IAssertionPlantNullable<TProperty>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(property))

private fun <T : Any, TFeature : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(feature: KProperty0<TFeature>)
    = IAssertionPlantWithCommonFields.CommonFields(Untranslatable(feature.name), feature.get(), AtriumFactory.newFeatureAssertionChecker(this))


//Arg0
fun <T : Any, TReturnValue: Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction0<TReturnValue>) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckImmediately(plant.createCommonFieldsForFeatureFactory(method))

fun <T : Any, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction0<TReturnValue>, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckLazily(plant.createCommonFieldsForFeatureFactory(method))
    .createAssertionsAndCheckThem(createAssertions)

fun <T : Any, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>,method: KFunction0<TReturnValue>): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method))


//Arg1
fun <T : Any, T1: Any?, TReturnValue: Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckImmediately(plant.createCommonFieldsForFeatureFactory(method, arg1))

fun <T : Any, T1: Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckLazily(plant.createCommonFieldsForFeatureFactory(method, arg1))
    .createAssertionsAndCheckThem(createAssertions)

fun <T : Any, T1: Any?, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1))


//Arg2
fun <T : Any, T1: Any?, T2: Any?, TReturnValue: Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckImmediately(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2))

fun <T : Any, T1: Any?, T2: Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckLazily(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2))
    .createAssertionsAndCheckThem(createAssertions)

fun <T : Any, T1: Any?, T2: Any?, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2))


//Arg3
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, TReturnValue: Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction3<T1, T2, T3,  TReturnValue>, arg1: T1, arg2: T2, arg3: T3) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckImmediately(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))

fun <T : Any, T1: Any?, T2: Any?, T3: Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckLazily(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))
    .createAssertionsAndCheckThem(createAssertions)

fun <T : Any, T1: Any?, T2: Any?, T3: Any?, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))


//Arg4
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, TReturnValue: Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckImmediately(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))

fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckLazily(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))
    .createAssertionsAndCheckThem(createAssertions)

fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))


//Arg5
fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, T5: Any?, TReturnValue: Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5) : IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckImmediately(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))

fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, T5: Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, createAssertions: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlantCheckLazily(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))
    .createAssertionsAndCheckThem(createAssertions)

fun <T : Any, T1: Any?, T2: Any?, T3: Any?, T4: Any?, T5: Any?, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))

private fun <T : Any, TReturnValue : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(method: KFunction<TReturnValue>, vararg arguments: Any?) =
    IAssertionPlantWithCommonFields.CommonFields(
        Untranslatable(AtriumFactory.newMethodCallFormatter().format(method, arguments)),
        method.call(*arguments),
        AtriumFactory.newFeatureAssertionChecker(this))
