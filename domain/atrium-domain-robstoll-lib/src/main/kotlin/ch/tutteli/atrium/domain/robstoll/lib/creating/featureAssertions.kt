package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.evalOnce
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.AssertionPlantWithCommonFields
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

fun <T : Any, TProperty : Any> _property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, name: Translatable): AssertionPlant<TProperty> {
    val provider = subjectProvider.evalOnce()
    return _property(plant, provider, provider, name)
}

fun <T : Any, TProperty : Any?> _property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, name: Translatable): AssertionPlantNullable<TProperty> {
    val provider = subjectProvider.evalOnce()
    return _property(plant, provider, provider, name)
}


fun <T : Any, TProperty : Any> _property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, representationProvider: () -> Any?, name: Translatable): AssertionPlant<TProperty>
    = coreFactory.newReportingPlant(plant.createCommonFieldsForFeatureFactory(name, representationProvider, subjectProvider))

fun <T : Any, TProperty : Any?> _property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, representationProvider: () -> Any?, name: Translatable): AssertionPlantNullable<TProperty>
    = coreFactory.newReportingPlantNullable(plant.createCommonFieldsForFeatureFactory(name, representationProvider, subjectProvider))

private fun <T : Any, TFeature : Any?> AssertionPlant<T>.createCommonFieldsForFeatureFactory(featureName: Translatable, representationProvider: () -> Any?, subjectProvider: () -> TFeature)
    = AssertionPlantWithCommonFields.CommonFields(featureName, subjectProvider, representationProvider, coreFactory.newFeatureAssertionChecker(this), RawString.NULL)


//Arg0
fun <T : Any, R : Any> _returnValueOf0(plant: AssertionPlant<T>, method: () -> R, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, method)

fun <T : Any, R : Any> _returnValueOf0(plant: AssertionPlant<T>, method: () -> R, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, representationProvider, method)


fun <T : Any, R : Any?> _returnValueOf0(plant: AssertionPlant<T>, method: () -> R, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, method)

fun <T : Any, R : Any?> _returnValueOf0(plant: AssertionPlant<T>, method: () -> R, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, representationProvider, method)


//Arg1
fun <T : Any, T1, R : Any> _returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, { method(arg1) }, arg1)

fun <T : Any, T1, R : Any> _returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, representationProvider, { method(arg1) }, arg1)


fun <T : Any, T1, R : Any?> _returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, { method(arg1) }, arg1)

fun <T : Any, T1, R : Any?> _returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, representationProvider, { method(arg1) }, arg1)

//Arg2
fun <T : Any, T1, T2, R : Any> _returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, { method(arg1, arg2) }, arg1, arg2)

fun <T : Any, T1, T2, R : Any> _returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, representationProvider, { method(arg1, arg2) }, arg1, arg2)


fun <T : Any, T1, T2, R : Any?> _returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, { method(arg1, arg2) }, arg1, arg2)

fun <T : Any, T1, T2, R : Any?> _returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, representationProvider, { method(arg1, arg2) }, arg1, arg2)

//Arg3
fun <T : Any, T1, T2, T3, R : Any> _returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, { method(arg1, arg2, arg3) }, arg1, arg2, arg3)

fun <T : Any, T1, T2, T3, R : Any> _returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, representationProvider, { method(arg1, arg2, arg3) }, arg1, arg2, arg3)


fun <T : Any, T1, T2, T3, R : Any?> _returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, { method(arg1, arg2, arg3) }, arg1, arg2, arg3)

fun <T : Any, T1, T2, T3, R : Any?> _returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, representationProvider, { method(arg1, arg2, arg3) }, arg1, arg2, arg3)

//Arg4
fun <T : Any, T1, T2, T3, T4, R : Any> _returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, { method(arg1, arg2, arg3, arg4) }, arg1, arg2, arg3, arg4)

fun <T : Any, T1, T2, T3, T4, R : Any> _returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, representationProvider, { method(arg1, arg2, arg3, arg4) }, arg1, arg2, arg3, arg4)


fun <T : Any, T1, T2, T3, T4, R : Any?> _returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, { method(arg1, arg2, arg3, arg4) }, arg1, arg2, arg3, arg4)

fun <T : Any, T1, T2, T3, T4, R : Any?> _returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, representationProvider, { method(arg1, arg2, arg3, arg4) }, arg1, arg2, arg3, arg4)

//Arg5
fun <T : Any, T1, T2, T3, T4, T5, R : Any> _returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, { method(arg1, arg2, arg3, arg4, arg5) }, arg1, arg2, arg3, arg4, arg5)

fun <T : Any, T1, T2, T3, T4, T5, R : Any> _returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    = plant.createPlantForMethod(name, representationProvider, { method(arg1, arg2, arg3, arg4, arg5) }, arg1, arg2, arg3, arg4, arg5)


fun <T : Any, T1, T2, T3, T4, T5, R : Any?> _returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, { method(arg1, arg2, arg3, arg4, arg5) }, arg1, arg2, arg3, arg4, arg5)

fun <T : Any, T1, T2, T3, T4, T5, R : Any?> _returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
    = plant.createPlantForMethodNullable(name, representationProvider, { method(arg1, arg2, arg3, arg4, arg5) }, arg1, arg2, arg3, arg4, arg5)



private fun <T : Any, R : Any> AssertionPlant<T>.createPlantForMethod(name: String, subjectProvider: () -> R, vararg arguments: Any?): AssertionPlant<R> {
    val provider = subjectProvider.evalOnce()
    return createPlantForMethod(name, provider as () -> Any?, provider, *arguments)
}
private fun <T : Any, R : Any> AssertionPlant<T>.createPlantForMethod(name: String, representationProvider: () -> Any?, subjectProvider: () -> R, vararg arguments: Any?)
    = coreFactory.newReportingPlant(createCommonFieldsForFeatureFactory(name, representationProvider, subjectProvider, arguments))


private fun <T : Any, R : Any?> AssertionPlant<T>.createPlantForMethodNullable(name: String, subjectProvider: () -> R, vararg arguments: Any?): AssertionPlantNullable<R> {
    val provider = subjectProvider.evalOnce()
    return createPlantForMethodNullable(name, provider as () -> Any?, provider, *arguments)
}

private fun <T : Any, R : Any?> AssertionPlant<T>.createPlantForMethodNullable(name: String, representationProvider: () -> Any?, subjectProvider: () -> R, vararg arguments: Any?)
    = coreFactory.newReportingPlantNullable(createCommonFieldsForFeatureFactory(name, representationProvider, subjectProvider, arguments))

private fun <T : Any, R : Any?> AssertionPlant<T>.createCommonFieldsForFeatureFactory(name: String, representationProvider: () -> Any?, subjectProvider: () -> R, arguments: Array<out Any?>)
    = AssertionPlantWithCommonFields.CommonFields(
        Untranslatable(coreFactory.newMethodCallFormatter().format(name, arguments)),
        subjectProvider,
        representationProvider,
        coreFactory.newFeatureAssertionChecker(this),
        RawString.NULL
    )
