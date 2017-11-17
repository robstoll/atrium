package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.*

fun <T : Any, TProperty : Any> _property(plant: IAssertionPlant<T>, property: KProperty0<TProperty>): IAssertionPlant<TProperty>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(property))

fun <T : Any, TProperty : Any> _property(plant: IAssertionPlant<T>, property: KProperty0<TProperty>, assertionCreator: IAssertionPlant<TProperty>.() -> Unit): IAssertionPlant<TProperty>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(property))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, TProperty : Any?> _property(plant: IAssertionPlant<T>, property: KProperty0<TProperty>): IAssertionPlantNullable<TProperty>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(property))

private fun <T : Any, TFeature : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(feature: KProperty0<TFeature>)
    = IAssertionPlantWithCommonFields.CommonFields(Untranslatable(feature.name), feature.get(), AtriumFactory.newFeatureAssertionChecker(this), RawString.NULL)


//Arg0
fun <T : Any, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction0<TReturnValue>): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method))

fun <T : Any, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction0<TReturnValue>, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction0<TReturnValue>): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method))


//Arg1
fun <T : Any, T1 : Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1))

fun <T : Any, T1 : Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1))


//Arg2
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2))

fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2))


//Arg3
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3))


//Arg4
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4))


//Arg5
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: IAssertionPlant<TReturnValue>.() -> Unit): IAssertionPlant<TReturnValue>
    = AtriumFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))
    .addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> _returnValueOf(plant: IAssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): IAssertionPlantNullable<TReturnValue>
    = AtriumFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(method, arg1, arg2, arg3, arg4, arg5))

private fun <T : Any, TReturnValue : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(method: KFunction<TReturnValue>, vararg arguments: Any?) =
    IAssertionPlantWithCommonFields.CommonFields(
        Untranslatable(AtriumFactory.newMethodCallFormatter().format(method, arguments)),
        method.call(*arguments),
        AtriumFactory.newFeatureAssertionChecker(this),
        RawString.NULL)
