package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers.ExplanatoryFailureHandler
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion.IS_A
import kotlin.reflect.KClass

fun <T : Any> _isNotNull(
    plant: AssertionPlantNullable<T?>,
    type: KClass<T>,
    assertionCreator: AssertionPlant<T>.() -> Unit
) {
    _downCast(IS_A, type, plant, assertionCreator, ExplanatoryFailureHandler())
}

fun <TSub : Any> _isA(
    plant: AssertionPlant<Any>,
    subType: KClass<TSub>,
    assertionCreator: AssertionPlant<TSub>.() -> Unit
) {
    _downCast(IS_A, subType, plant, assertionCreator, ExplanatoryFailureHandler())
}

fun <T : Any, TSub : T> _downCast(
    description: Translatable,
    subType: KClass<TSub>,
    subjectPlant: BaseAssertionPlant<T?, *>,
    assertionCreator: AssertionPlant<TSub>.() -> Unit,
    failureHandler: AnyTypeTransformation.FailureHandler<T, TSub>
) {
    DownCastAssertionCreator<T, TSub>()
        .downCast(description, subType, subjectPlant, assertionCreator, failureHandler)
}

fun <S : Any, T : Any> _typeTransformation(
    parameterObject: AnyTypeTransformation.ParameterObject<S, T>,
    canBeTransformed: (S) -> Boolean,
    transform: (S) -> T,
    failureHandler: AnyTypeTransformation.FailureHandler<S, T>
) {
    TypeTransformationAssertionCreator<S, T>()
        .create(parameterObject, canBeTransformed, transform, failureHandler)
}
