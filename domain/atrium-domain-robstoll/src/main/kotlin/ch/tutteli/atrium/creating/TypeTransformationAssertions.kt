package ch.tutteli.atrium.creating

import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

/**
 * Robstoll's implementation of [ITypeTransformationAssertions].
 */
object TypeTransformationAssertions : ITypeTransformationAssertions {
    override fun <T : Any> isNotNull(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        assertionCreator: AssertionPlant<T>.() -> Unit
    ): Unit = _isNotNull(plant, type, assertionCreator)


    override fun <TSub : Any> isA(
        plant: AssertionPlant<Any>,
        subType: KClass<TSub>,
        assertionCreator: AssertionPlant<TSub>.() -> Unit
    ): Unit = _isA(plant, subType, assertionCreator)


    override fun <T : Any, TSub : T> downCast(
        description: Translatable,
        subType: KClass<TSub>,
        subjectPlant: BaseAssertionPlant<T?, *>,
        assertionCreator: AssertionPlant<TSub>.() -> Unit
    ): Unit = _downCast(description, subType, subjectPlant, assertionCreator)


    override fun <T : Any, TSub : Any> typeTransformation(
        description: Translatable,
        representation: Any,
        subjectPlant: BaseAssertionPlant<T?, *>,
        assertionCreator: AssertionPlant<TSub>.() -> Unit,
        warningTransformationFailed: Translatable,
        canBeTransformed: (T) -> Boolean,
        transform: (T) -> TSub
    ): Unit = _typeTransformation(
        description,
        representation,
        subjectPlant,
        assertionCreator,
        warningTransformationFailed,
        canBeTransformed,
        transform
    )
}
