package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.AssertionPlantWithCommonFields
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.*

fun <T : Any, TProperty : Any> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(property))

fun <T : Any, TProperty : Any> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>, assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(property))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, TProperty : Any?> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(property))

private fun <T : Any, TFeature : Any?> AssertionPlant<T>.createCommonFieldsForFeatureFactory(feature: KProperty0<TFeature>)
    = AssertionPlantWithCommonFields.CommonFields(Untranslatable(feature.name), feature.get(), AtriumFactory.newFeatureAssertionChecker(this), RawString.NULL)


//Arg0
fun <T : Any, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method))

fun <T : Any, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method))


//Arg1
fun <T : Any, T1 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1))

fun <T : Any, T1 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1))


//Arg2
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2))

fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2))


//Arg3
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))


//Arg4
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))


//Arg5
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))

private fun <T : Any, TReturnValue : Any?> AssertionPlant<T>.createCommonFieldsForFeatureFactory(method: KFunction<TReturnValue>, vararg arguments: Any?) =
    AssertionPlantWithCommonFields.CommonFields(
        Untranslatable(AtriumFactory.newMethodCallFormatter().format(method, arguments)),
        method.call(*arguments),
        AtriumFactory.newFeatureAssertionChecker(this),
        RawString.NULL)
