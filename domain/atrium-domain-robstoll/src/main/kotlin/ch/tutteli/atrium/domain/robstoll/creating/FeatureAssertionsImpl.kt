package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.creating.FeatureAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Robstoll's implementation of [FeatureAssertions].
 */
class FeatureAssertionsImpl : FeatureAssertions {

    override fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, name: Translatable): AssertionPlant<TProperty>
        = _property(plant, subjectProvider, name)
    override fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, representationProvider: () -> Any?, name: Translatable): AssertionPlant<TProperty>
        = _property(plant, subjectProvider, representationProvider, name)

    override fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, name: Translatable): AssertionPlantNullable<TProperty>
        = _property(plant, subjectProvider, name)
    override fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, representationProvider: () -> Any?, name: Translatable): AssertionPlantNullable<TProperty>
        = _property(plant, subjectProvider, representationProvider, name)

    //Arg0
    override fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, method: () -> R, name: String): AssertionPlant<R>
        = _returnValueOf0(plant, method, name)
    override fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, method: () -> R, representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = _returnValueOf0(plant, method, representationProvider, name)

    override fun <T : Any, R : Any?> returnValueOf0(plant: AssertionPlant<T>, method: () -> R, name: String): AssertionPlantNullable<R>
        = _returnValueOf0(plant, method, name)
    override fun <T : Any, R : Any?> returnValueOf0(plant: AssertionPlant<T>, method: () -> R, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = _returnValueOf0(plant, method, representationProvider, name)

    //Arg1
    override fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, name: String): AssertionPlant<R>
        = _returnValueOf1(plant, method, arg1, name)
    override fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = _returnValueOf1(plant, method, arg1, representationProvider, name)

    override fun <T : Any, T1, R : Any?> returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, name: String): AssertionPlantNullable<R>
        = _returnValueOf1(plant, method, arg1, name)
    override fun <T : Any, T1, R : Any?> returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = _returnValueOf1(plant, method, arg1, representationProvider, name)

    //Arg2
    override fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, name: String): AssertionPlant<R>
        = _returnValueOf2(plant, method, arg1, arg2, name)
    override fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = _returnValueOf2(plant, method, arg1, arg2, representationProvider, name)

    override fun <T : Any, T1, T2, R : Any?> returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, name: String): AssertionPlantNullable<R>
        = _returnValueOf2(plant, method, arg1, arg2, name)
    override fun <T : Any, T1, T2, R : Any?> returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = _returnValueOf2(plant, method, arg1, arg2, representationProvider, name)

    //Arg3
    override fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, name: String): AssertionPlant<R>
        = _returnValueOf3(plant, method, arg1, arg2, arg3, name)
    override fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = _returnValueOf3(plant, method, arg1, arg2, arg3, representationProvider, name)

    override fun <T : Any, T1, T2, T3, R : Any?> returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, name: String): AssertionPlantNullable<R>
        = _returnValueOf3(plant, method, arg1, arg2, arg3, name)
    override fun <T : Any, T1, T2, T3, R : Any?> returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = _returnValueOf3(plant, method, arg1, arg2, arg3, representationProvider, name)

    //Arg4
    override fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, name: String): AssertionPlant<R>
        = _returnValueOf4(plant, method, arg1, arg2, arg3, arg4, name)
    override fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = _returnValueOf4(plant, method, arg1, arg2, arg3, arg4, representationProvider, name)

    override fun <T : Any, T1, T2, T3, T4, R : Any?> returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, name: String): AssertionPlantNullable<R>
        = _returnValueOf4(plant, method, arg1, arg2, arg3, arg4, name)
    override fun <T : Any, T1, T2, T3, T4, R : Any?> returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = _returnValueOf4(plant, method, arg1, arg2, arg3, arg4, representationProvider, name)

    //Arg5
    override fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, name: String): AssertionPlant<R>
        = _returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, name)
    override fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, representationProvider: () -> Any?, name: String): AssertionPlant<R>
        = _returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name)

    override fun <T : Any, T1, T2, T3, T4, T5, R : Any?> returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, name: String): AssertionPlantNullable<R>
        = _returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, name)
    override fun <T : Any, T1, T2, T3, T4, T5, R : Any?> returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
        = _returnValueOf5(plant, method, arg1, arg2, arg3, arg4, arg5, representationProvider, name)
}
