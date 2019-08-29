@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.creating.any.typetransformation.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.domain.creating.any.typetransformation.creators.AnyTypeTransformationAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators._downCast
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators._isA
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators._isNotNull
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators._typeTransformation
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass


@Deprecated("use _isA or _changeSubject; will be removed with 1.0.0")
class AnyTypeTransformationAssertionsImpl : AnyTypeTransformationAssertions {

    override fun <T : Any> isNotNull(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        assertionCreator: AssertionPlant<T>.() -> Unit
    ) {
        @Suppress("DEPRECATION")
        _isNotNull(plant, type, assertionCreator)
    }

    override fun <TSub : Any> isA(
        plant: AssertionPlant<Any>,
        subType: KClass<TSub>,
        assertionCreator: AssertionPlant<TSub>.() -> Unit
    ) {
        _isA(plant, subType, assertionCreator)
    }

    override fun <T : Any, TSub : T> downCast(
        description: Translatable,
        subType: KClass<TSub>,
        subjectPlant: BaseAssertionPlant<T?, *>,
        assertionCreator: AssertionPlant<TSub>.() -> Unit,
        failureHandler: AnyTypeTransformation.FailureHandler<T, TSub>
    ) {
        _downCast(description, subType, subjectPlant, assertionCreator, failureHandler)
    }

    override fun <S : Any, T : Any> transform(
        parameterObject: AnyTypeTransformation.ParameterObject<S, T>,
        canBeTransformed: (S) -> Boolean,
        transform: (S) -> T,
        failureHandler: AnyTypeTransformation.FailureHandler<S, T>
    ) {
        _typeTransformation(parameterObject, canBeTransformed, transform, failureHandler)
    }
}
