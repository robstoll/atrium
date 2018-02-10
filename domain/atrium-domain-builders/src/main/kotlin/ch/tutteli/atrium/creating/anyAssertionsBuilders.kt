@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.creating.any.typetransformation.creators.AnyTypeTransformationAssertions
import ch.tutteli.atrium.creating.any.typetransformation.creators.IAnyTypeTransformationAssertions
import ch.tutteli.atrium.creating.any.typetransformation.failurehandlers.AnyTypeTransformationFailureHandlers
import ch.tutteli.atrium.creating.any.typetransformation.failurehandlers.IAnyTypeTransformationFailureHandlers
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

object AnyAssertionsBuilder : IAnyAssertions {
    override inline fun <T : Any> toBe(plant: AssertionPlant<T>, expected: T)
        = AnyAssertions.toBe(plant, expected)

    override inline fun <T : Any> notToBe(plant: AssertionPlant<T>, expected: T)
        = AnyAssertions.notToBe(plant, expected)

    override inline fun <T : Any> isSame(plant: AssertionPlant<T>, expected: T)
        = AnyAssertions.isSame(plant, expected)

    override inline fun <T : Any> isNotSame(plant: AssertionPlant<T>, expected: T)
        = AnyAssertions.isNotSame(plant, expected)

    override inline fun <T> isNull(plant: AssertionPlantNullable<T>)
        = AnyAssertions.isNull(plant)

    /**
     * Delegates to [AnyTypeTransformationAssertions].
     */
    inline val typeTransformation get() = AnyTypeTransformationAssertionsBuilder
}


object AnyTypeTransformationAssertionsBuilder: IAnyTypeTransformationAssertions {

    override fun <T : Any> isNotNull(plant: AssertionPlantNullable<T?>, type: KClass<T>, assertionCreator: AssertionPlant<T>.() -> Unit)
        = AnyTypeTransformationAssertions.isNotNull(plant, type, assertionCreator)

    override fun <TSub : Any> isA(plant: AssertionPlant<Any>, subType: KClass<TSub>, assertionCreator: AssertionPlant<TSub>.() -> Unit)
        = AnyTypeTransformationAssertions.isA(plant, subType, assertionCreator)

    override fun <T : Any, TSub : T> downCast(description: Translatable, subType: KClass<TSub>, subjectPlant: BaseAssertionPlant<T?, *>, assertionCreator: AssertionPlant<TSub>.() -> Unit, failureHandler: AnyTypeTransformation.FailureHandler<T, TSub>)
        = AnyTypeTransformationAssertions.downCast(description, subType, subjectPlant, assertionCreator, failureHandler)

    override fun <S : Any, T : Any> transform(parameterObject: AnyTypeTransformation.ParameterObject<S, T>, canBeTransformed: (S) -> Boolean, transform: (S) -> T, failureHandler: AnyTypeTransformation.FailureHandler<S, T>)
        = AnyTypeTransformationAssertions.transform(parameterObject, canBeTransformed, transform, failureHandler)

    /**
     * Delegates to [AnyTypeTransformationFailureHandlers].
     */
    inline val failureHandlers get () = AnyTypeTransformationFailureHandlersBuilder
}

object AnyTypeTransformationFailureHandlersBuilder : IAnyTypeTransformationFailureHandlers {

    override fun <S : Any, T : Any> newExplanatory()
        = AnyTypeTransformationFailureHandlers.newExplanatory<S, T>()

    override fun <S : Any, T : Any> newExplanatoryWithHint(showHint: () -> Boolean, failureHintFactory: () -> AssertionGroup)
        = AnyTypeTransformationFailureHandlers.newExplanatoryWithHint<S, T>(showHint, failureHintFactory)

}
