package ch.tutteli.atrium.domain.creating.any.typetransformation.creators

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

/**
 * The access point to an implementation of [AnyTypeTransformationAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val anyTypeTransformationAssertions by lazy { loadSingleService(AnyTypeTransformationAssertions::class) }


/**
 * Defines the minimum set of assertion functions representing a type transformation,
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyTypeTransformationAssertions {
    fun <T : Any> isNotNull(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        assertionCreator: AssertionPlant<T>.() -> Unit
    )

    fun <TSub : Any> isA(
        plant: AssertionPlant<Any>,
        subType: KClass<TSub>,
        assertionCreator: AssertionPlant<TSub>.() -> Unit
    )

    fun <T : Any, TSub : T> downCast(
        description: Translatable,
        subType: KClass<TSub>,
        subjectPlant: BaseAssertionPlant<T?, *>,
        assertionCreator: AssertionPlant<TSub>.() -> Unit,
        failureHandler: AnyTypeTransformation.FailureHandler<T, TSub>
    )

    fun <S : Any, T : Any> transform(
        parameterObject: AnyTypeTransformation.ParameterObject<S, T>,
        canBeTransformed: (S) -> Boolean,
        transform: (S) -> T,
        failureHandler: AnyTypeTransformation.FailureHandler<S, T>
    )
}
