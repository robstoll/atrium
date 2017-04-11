package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.createAssertionsAndCheckThem
import kotlin.reflect.KProperty0

inline fun <reified T : Any> IAssertionPlantNullable<T?>.isNotNull()
    = AtriumFactory.newDownCastBuilder("is not", commonFields)
    .cast()

inline fun <reified T : Any> IAssertionPlantNullable<T?>.isNotNull(noinline createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newDownCastBuilder("is not", commonFields)
    .withLazyAssertions(createAssertions)
    .cast()

/**
 * Creates an `is a` assertion, which holds if the [IAssertionPlant.subject] is the same or a subtype of the expected type [TSub].
 */
inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(): IAssertionPlant<TSub>
    = AtriumFactory.newDownCastBuilder<TSub, Any>("is type or sub-type of", commonFields)
    .cast()

/**
 * Creates an `is a` assertion, which holds if the [IAssertionPlant.subject] is the same or a subtype of the expected type [TSub].
 */
inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(noinline createAssertions: IAssertionPlant<TSub>.() -> Unit): IAssertionPlant<TSub>
    = AtriumFactory.newDownCastBuilder<TSub, Any>("is type or sub-type of", commonFields)
    .withLazyAssertions(createAssertions)
    .cast()

/**
 * Allows to define assertions which are immediately evaluated (see [IAtriumFactory.newCheckImmediately]) for a specific [feature].
 */
fun <T : Any, TFeature : Any> IAssertionPlant<T>.its(feature: KProperty0<TFeature>): IAssertionPlant<TFeature>
    = AtriumFactory.newCheckImmediately(createCommonFieldsForFeatureFactory(feature))

/**
 * Allows to define assertions which are lazily evaluated (see [IAtriumFactory.newCheckLazily]) for a specific [feature].
 */
fun <T : Any, TFeature : Any> IAssertionPlant<T>.its(feature: KProperty0<TFeature>, createAssertions: IAssertionPlant<TFeature>.() -> Unit): IAssertionPlant<TFeature> {
    val featurePlant = AtriumFactory.newCheckLazily(createCommonFieldsForFeatureFactory(feature))
    return AtriumFactory.createAssertionsAndCheckThem(featurePlant, createAssertions)
}

/**
 * Allows to define assertions for a specific [feature] which is nullable (see [IAtriumFactory.newNullable]).
 */
fun <T : Any, TFeature : Any?> IAssertionPlant<T>.its(feature: KProperty0<TFeature>): IAssertionPlantNullable<TFeature>
    = AtriumFactory.newNullable(createCommonFieldsForFeatureFactory(feature))

private fun <T : Any, TFeature : Any?> IAssertionPlant<T>.createCommonFieldsForFeatureFactory(feature: KProperty0<TFeature>)
    = IAssertionPlantWithCommonFields.CommonFields(feature.name, feature.get(), AtriumFactory.newFeatureAssertionChecker(this))
