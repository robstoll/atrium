package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.SingleServiceLoader
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import java.util.*

/**
 * The access point to an implementation of [FeatureAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val featureAssertions by lazy { SingleServiceLoader.load(FeatureAssertions::class.java) }

/**
 * Defines the minimum set of assertion functions -- used to create feature assertions --
 * which an implementation of the domain of Atrium has to provide.
 */
interface FeatureAssertions {

    fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, name: String): AssertionPlant<TProperty>
    fun <T : Any, TProperty : Any> property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, representationProvider: () -> Any?, name: String): AssertionPlant<TProperty>
    fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, name: String): AssertionPlantNullable<TProperty>
    fun <T : Any, TProperty : Any?> property(plant: AssertionPlant<T>, subjectProvider: () -> TProperty, representationProvider: () -> Any?, name: String): AssertionPlantNullable<TProperty>

    //Arg0
    fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, method: () -> R, name: String): AssertionPlant<R>
    fun <T : Any, R : Any> returnValueOf0(plant: AssertionPlant<T>, method: () -> R, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    fun <T : Any, R : Any?> returnValueOf0(plant: AssertionPlant<T>, method: () -> R, name: String): AssertionPlantNullable<R>
    fun <T : Any, R : Any?> returnValueOf0(plant: AssertionPlant<T>, method: () -> R, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>

    //Arg1
    fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, name: String): AssertionPlant<R>
    fun <T : Any, T1, R : Any> returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    fun <T : Any, T1, R : Any?> returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, name: String): AssertionPlantNullable<R>
    fun <T : Any, T1, R : Any?> returnValueOf1(plant: AssertionPlant<T>, method: (T1) -> R, arg1: T1, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>

    //Arg2
    fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, name: String): AssertionPlant<R>
    fun <T : Any, T1, T2, R : Any> returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    fun <T : Any, T1, T2, R : Any?> returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, name: String): AssertionPlantNullable<R>
    fun <T : Any, T1, T2, R : Any?> returnValueOf2(plant: AssertionPlant<T>, method: (T1, T2) -> R, arg1: T1, arg2: T2, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>

    //Arg3
    fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, name: String): AssertionPlant<R>
    fun <T : Any, T1, T2, T3, R : Any> returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    fun <T : Any, T1, T2, T3, R : Any?> returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, name: String): AssertionPlantNullable<R>
    fun <T : Any, T1, T2, T3, R : Any?> returnValueOf3(plant: AssertionPlant<T>, method: (T1, T2, T3) -> R, arg1: T1, arg2: T2, arg3: T3, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>

    //Arg4
    fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, name: String): AssertionPlant<R>
    fun <T : Any, T1, T2, T3, T4, R : Any> returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    fun <T : Any, T1, T2, T3, T4, R : Any?> returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, name: String): AssertionPlantNullable<R>
    fun <T : Any, T1, T2, T3, T4, R : Any?> returnValueOf4(plant: AssertionPlant<T>, method: (T1, T2, T3, T4) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>

    //Arg5
    fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, name: String): AssertionPlant<R>
    fun <T : Any, T1, T2, T3, T4, T5, R : Any> returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, representationProvider: () -> Any?, name: String): AssertionPlant<R>
    fun <T : Any, T1, T2, T3, T4, T5, R : Any?> returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, name: String): AssertionPlantNullable<R>
    fun <T : Any, T1, T2, T3, T4, T5, R : Any?> returnValueOf5(plant: AssertionPlant<T>, method: (T1, T2, T3, T4, T5) -> R, arg1: T1, arg2: T2, arg3: T3, arg4: T4, arg5: T5, representationProvider: () -> Any?, name: String): AssertionPlantNullable<R>
}
