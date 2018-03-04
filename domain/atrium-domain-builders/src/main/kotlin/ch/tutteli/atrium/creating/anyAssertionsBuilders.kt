@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.creating.any.typetransformation.creators.AnyTypeTransformationAssertions
import ch.tutteli.atrium.creating.any.typetransformation.creators.IAnyTypeTransformationAssertions
import ch.tutteli.atrium.creating.any.typetransformation.failurehandlers.FailureHandlerFactory
import ch.tutteli.atrium.creating.any.typetransformation.failurehandlers.IFailureHandlerFactory
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
     * Delegates to [FailureHandlerFactory].
     */
    inline val failureHandlers get () = FailureHandlerFactoryBuilder
}

object FailureHandlerFactoryBuilder : IFailureHandlerFactory {

    override fun <S : Any, T : Any> newExplanatory()
        = FailureHandlerFactory.newExplanatory<S, T>()

    override fun <S : Any, T : Any> newExplanatoryWithHint(showHint: () -> Boolean, failureHintFactory: () -> Assertion)
        = FailureHandlerFactory.newExplanatoryWithHint<S, T>(showHint, failureHintFactory)

}
