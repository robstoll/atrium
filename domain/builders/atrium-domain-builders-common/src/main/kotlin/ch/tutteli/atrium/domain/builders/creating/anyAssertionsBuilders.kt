@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.creating.AnyAssertions
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.domain.creating.any.typetransformation.creators.AnyTypeTransformationAssertions
import ch.tutteli.atrium.domain.creating.any.typetransformation.creators.anyTypeTransformationAssertions
import ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers.FailureHandlerFactory
import ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers.failureHandlerFactory
import ch.tutteli.atrium.domain.creating.anyAssertions
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.*
import kotlin.reflect.KClass

/**
 * Delegates inter alia to the implementation of [AnyAssertions].
 * In detail, it implements [AnyAssertions] by delegating to [anyAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object AnyAssertionsBuilder : AnyAssertions {
    override inline fun <T : Any> toBe(subjectProvider: SubjectProvider<T>, expected: T)
        = anyAssertions.toBe(subjectProvider, expected)

    override inline fun <T> toBeNull(plant: SubjectProvider<T>)
        = anyAssertions.toBeNull(plant)

    override inline fun <T : Any> notToBeNull(
        assertionContainer: Expect<T?>,
        type: KClass<T>,
        noinline assertionCreator: Expect<T>.() -> Unit
    ) = anyAssertions.notToBeNull(assertionContainer, type, assertionCreator)

    override inline fun <T : Any> toBeNullable(
        assertionContainer: Expect<T?>,
        type: KClass<T>,
        expectedOrNull: T?
    ) = anyAssertions.toBeNullable(assertionContainer, type, expectedOrNull)


    override inline fun <T : Any> notToBe(plant: AssertionPlant<T>, expected: T)
        = anyAssertions.notToBe(plant, expected)

    override inline fun <T : Any> isSame(plant: AssertionPlant<T>, expected: T)
        = anyAssertions.isSame(plant, expected)

    override inline fun <T : Any> isNotSame(plant: AssertionPlant<T>, expected: T)
        = anyAssertions.isNotSame(plant, expected)

    override inline fun <T : Any> isNullable(plant: AssertionPlantNullable<T?>, type: KClass<T>, expectedOrNull: T?)
        = anyAssertions.isNullable(plant, type, expectedOrNull)

    override inline fun <T : Any> isNotNull(plant: AssertionPlantNullable<T?>, type: KClass<T>, noinline assertionCreator: AssertionPlant<T>.() -> Unit)
        = anyAssertions.isNotNull(plant, type, assertionCreator)

    override inline fun <T : Any> isNotNullBut(plant: AssertionPlantNullable<T?>, type: KClass<T>, expected: T)
        = anyAssertions.isNotNullBut(plant, type, expected)

    override inline fun <T : Any> isNullIfNullGivenElse(plant: AssertionPlantNullable<T?>, type: KClass<T>, noinline assertionCreatorOrNull: (AssertionPlant<T>.() -> Unit)?)
        = anyAssertions.isNullIfNullGivenElse(plant, type, assertionCreatorOrNull)


    /**
     * Returns [AnyTypeTransformationAssertionsBuilder]
     * which inter alia delegates to the implementation of [AnyTypeTransformationAssertions].
     */
    inline val typeTransformation get() = AnyTypeTransformationAssertionsBuilder
}

/**
 * Delegates inter alia to the implementation of [AnyTypeTransformationAssertions].
 * In detail, it implements [AnyTypeTransformationAssertions] by delegating to [anyTypeTransformationAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
//TODO #89 get rid of this class, use SubjectChanger to do the same, maybe provide still a helper for downCast
object AnyTypeTransformationAssertionsBuilder: AnyTypeTransformationAssertions {

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override inline fun <T : Any> isNotNull(plant: AssertionPlantNullable<T?>, type: KClass<T>, noinline assertionCreator: AssertionPlant<T>.() -> Unit)
        = anyTypeTransformationAssertions.isNotNull(plant, type, assertionCreator)

    override inline fun <TSub : Any> isA(plant: AssertionPlant<Any>, subType: KClass<TSub>, noinline assertionCreator: AssertionPlant<TSub>.() -> Unit)
        = anyTypeTransformationAssertions.isA(plant, subType, assertionCreator)

    override inline fun <T : Any, TSub : T> downCast(description: Translatable, subType: KClass<TSub>, subjectPlant: BaseAssertionPlant<T?, *>, noinline assertionCreator: AssertionPlant<TSub>.() -> Unit, failureHandler: AnyTypeTransformation.FailureHandler<T, TSub>)
        = anyTypeTransformationAssertions.downCast(description, subType, subjectPlant, assertionCreator, failureHandler)

    override inline fun <S : Any, T : Any> transform(parameterObject: AnyTypeTransformation.ParameterObject<S, T>, noinline canBeTransformed: (S) -> Boolean, noinline transform: (S) -> T, failureHandler: AnyTypeTransformation.FailureHandler<S, T>)
        = anyTypeTransformationAssertions.transform(parameterObject, canBeTransformed, transform, failureHandler)

    /**
     * Returns [AnyTypeTransformationFailureHandlerFactoryBuilder]
     * which inter alia delegates to the implementation of [FailureHandlerFactory].
     */
    inline val failureHandlers get () = AnyTypeTransformationFailureHandlerFactoryBuilder
}

/**
 * Delegates inter alia to the implementation of [FailureHandlerFactory].
 * In detail, it implements [FailureHandlerFactory] by delegating to [failureHandlerFactory]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object AnyTypeTransformationFailureHandlerFactoryBuilder : FailureHandlerFactory {

    override inline fun <S : Any, T : Any> newExplanatory()
        = failureHandlerFactory.newExplanatory<S, T>()

    override inline fun <S : Any, T : Any> newExplanatoryWithHint(noinline showHint: () -> Boolean, noinline failureHintFactory: () -> Assertion)
        = failureHandlerFactory.newExplanatoryWithHint<S, T>(showHint, failureHintFactory)

}
