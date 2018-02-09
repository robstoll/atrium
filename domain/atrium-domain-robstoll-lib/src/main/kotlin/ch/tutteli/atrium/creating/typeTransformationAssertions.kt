package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.any.typetransformation.DownCaster
import ch.tutteli.atrium.creating.any.typetransformation.ExplanatoryTypeTransformationFailureHandler
import ch.tutteli.atrium.creating.any.typetransformation.TypeTransformer
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion.IS_A
import kotlin.reflect.KClass

fun <T : Any> _isNotNull(plant: AssertionPlantNullable<T?>, type: KClass<T>, assertionCreator: AssertionPlant<T>.() -> Unit) {
    _downCast(IS_A, type, plant, assertionCreator)
}

fun <TSub : Any> _isA(plant: AssertionPlant<Any>, subType: KClass<TSub>, assertionCreator: AssertionPlant<TSub>.() -> Unit) {
    _downCast(IS_A, subType, plant, assertionCreator)
}

fun <T : Any, TSub : T> _downCast(
    description: Translatable,
    subType: KClass<TSub>,
    subjectPlant: BaseAssertionPlant<T?, *>,
    assertionCreator: AssertionPlant<TSub>.() -> Unit
) {
    DownCaster<T, TSub>(ExplanatoryTypeTransformationFailureHandler())
        .downCast(description, subType, subjectPlant, assertionCreator)
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
    TypeTransformer<T, TSub>(ExplanatoryTypeTransformationFailureHandler()).transform(
        description, representation, subjectPlant, assertionCreator,
        warningTransformationFailed, canBeTransformed, transform
    )
}
