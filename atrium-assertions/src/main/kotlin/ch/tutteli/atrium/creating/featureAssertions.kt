package ch.tutteli.atrium.creating

import ch.tutteli.atrium.CoreFactory
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.*

fun <T : Any, TProperty : Any> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlant<TProperty>
    = CoreFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(property))

fun <T : Any, TProperty : Any> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>, assertionCreator: AssertionPlant<TProperty>.() -> Unit): AssertionPlant<TProperty>
    = _property(plant, property).addAssertionsCreatedBy(assertionCreator)

fun <T : Any, TProperty : Any?> _property(plant: AssertionPlant<T>, property: KProperty0<TProperty>): AssertionPlantNullable<TProperty>
    = CoreFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(property))

private fun <T : Any, TFeature : Any?> AssertionPlant<T>.createCommonFieldsForFeatureFactory(feature: KProperty0<TFeature>)
    = AssertionPlantWithCommonFields.CommonFields(Untranslatable(feature.name), feature.get(), CoreFactory.newFeatureAssertionChecker(this), RawString.NULL)


//Arg0
fun <T : Any, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method)

fun <T : Any, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue): AssertionPlant<TReturnValue>
    = plant.createPlantForMethod(name, method())

fun <T : Any, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, assertionCreator)

fun <T : Any, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, name, method).addAssertionsCreatedBy(assertionCreator)

fun <T : Any, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction0<TReturnValue>): AssertionPlantNullable<TReturnValue>
    = _method(plant, method.name, method)

fun <T : Any, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: () -> TReturnValue): AssertionPlantNullable<TReturnValue>
    = plant.createPlantForMethod(name, method())


//Arg1
fun <T : Any, T1 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, arg1)

fun <T : Any, T1 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1): AssertionPlant<TReturnValue>
    = plant.createPlantForMethod(name, method(arg1), arg1)

fun <T : Any, T1 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, arg1, assertionCreator)

fun <T : Any, T1 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, name, method, arg1).addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction1<T1, TReturnValue>, arg1: T1): AssertionPlantNullable<TReturnValue>
    = _method(plant, method.name, method, arg1)

fun <T : Any, T1 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1) -> TReturnValue, arg1: T1): AssertionPlantNullable<TReturnValue>
    = plant.createPlantForMethod(name, method(arg1), arg1)


//Arg2
fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2)

fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlant<TReturnValue>
    = plant.createPlantForMethod(name, method(arg1, arg2), arg1, arg2)

fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2, assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, name, method, arg1, arg2).addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction2<T1, T2, TReturnValue>, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2)

fun <T : Any, T1 : Any?, T2 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2) -> TReturnValue, arg1: T1, arg2: T2): AssertionPlantNullable<TReturnValue>
    = plant.createPlantForMethod(name, method(arg1, arg2), arg1, arg2)


//Arg3
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2, arg3)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlant<TReturnValue>
    = plant.createPlantForMethod(name, method(arg1, arg2, arg3), arg1, arg2, arg3)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2, arg3, assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, name, method, arg1, arg2, arg3).addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction3<T1, T2, T3, TReturnValue>, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2, arg3)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3): AssertionPlantNullable<TReturnValue>
    = plant.createPlantForMethod(name, method(arg1, arg2, arg3), arg1, arg2, arg3)


//Arg4
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2, arg3, arg4)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlant<TReturnValue>
    = plant.createPlantForMethod(name, method(arg1, arg2, arg3, arg4), arg1, arg2, arg3, arg4)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2, arg3, arg4, assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, name, method, arg1, arg2, arg3, arg4).addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction4<T1, T2, T3, T4, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2, arg3, arg4)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4): AssertionPlantNullable<TReturnValue>
    = plant.createPlantForMethod(name, method(arg1, arg2, arg3, arg4), arg1, arg2, arg3, arg4)


//Arg5
fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2, arg3, arg4, arg5)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlant<TReturnValue>
    = plant.createPlantForMethod(name, method(arg1, arg2, arg3, arg4, arg5), arg1, arg2, arg3, arg4, arg5)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2, arg3, arg4, arg5, assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, assertionCreator: AssertionPlant<TReturnValue>.() -> Unit): AssertionPlant<TReturnValue>
    = _method(plant, name, method, arg1, arg2, arg3, arg4, arg5).addAssertionsCreatedBy(assertionCreator)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> _returnValueOf(plant: AssertionPlant<T>, method: KFunction5<T1, T2, T3, T4, T5, TReturnValue>, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = _method(plant, method.name, method, arg1, arg2, arg3, arg4, arg5)

fun <T : Any, T1 : Any?, T2 : Any?, T3 : Any?, T4 : Any?, T5 : Any?, TReturnValue : Any?> _method(plant: AssertionPlant<T>, name: String, method: (T1, T2, T3, T4, T5) -> TReturnValue, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5): AssertionPlantNullable<TReturnValue>
    = plant.createPlantForMethod(name, method(arg1, arg2, arg3, arg4, arg5), arg1, arg2, arg3, arg4, arg5)


private fun <T : Any, TReturnValue : Any> AssertionPlant<T>.createPlantForMethod(name: String, value: TReturnValue, vararg arguments: Any?)
    = CoreFactory.newReportingPlant(createCommonFieldsForFeatureFactory(name, value, arguments))

private fun <T : Any, TReturnValue : Any?> AssertionPlant<T>.createPlantForMethod(name: String, value: TReturnValue, vararg arguments: Any?)
    = CoreFactory.newReportingPlantNullable(createCommonFieldsForFeatureFactory(name, value, arguments))

private fun <T : Any, TReturnValue : Any?> AssertionPlant<T>.createCommonFieldsForFeatureFactory(name: String, value: TReturnValue, arguments: Array<out Any?>) =
    AssertionPlantWithCommonFields.CommonFields(
        Untranslatable(CoreFactory.newMethodCallFormatter().format(name, arguments)),
        value,
        CoreFactory.newFeatureAssertionChecker(this),
        RawString.NULL)
