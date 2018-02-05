@file:JvmName("DeprecatedTypeTransformationAssertions")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

inline fun <reified T : Any> _isNotNull(plant: AssertionPlantNullable<T?>, noinline assertionCreator: AssertionPlant<T>.() -> Unit) {
    TypeTransformationAssertions.isNotNull(plant, T::class, assertionCreator)
}

inline fun <reified TSub : Any> _isA(plant: AssertionPlant<Any>, noinline assertionCreator: AssertionPlant<TSub>.() -> Unit) {
    TypeTransformationAssertions.isA(plant, TSub::class, assertionCreator)
}

fun <T : Any, TSub : T> _downCast(
    description: Translatable,
    subType: KClass<TSub>,
    subjectPlant: BaseAssertionPlant<T?, *>,
    assertionCreator: AssertionPlant<TSub>.() -> Unit
) {
    TypeTransformationAssertions.downCast(description, subType, subjectPlant, assertionCreator)
}

fun <T : Any, TSub : Any> _typeTransformation(
    description: Translatable,
    representation: Any,
    subjectPlant: BaseAssertionPlant<T?, *>,
    assertionCreator: AssertionPlant<TSub>.() -> Unit,
    warningTransformationFailed: Translatable,
    canBeTransformed: (T) -> Boolean,
    transform: (T) -> TSub
) {
    TypeTransformationAssertions.typeTransformation(description, representation, subjectPlant, assertionCreator, warningTransformationFailed, canBeTransformed, transform)
}
