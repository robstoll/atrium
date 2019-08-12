@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE", "DEPRECATION")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.creating.AnyAssertions
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.domain.creating.any.typetransformation.creators.AnyTypeTransformationAssertions
import ch.tutteli.atrium.domain.creating.any.typetransformation.creators.anyTypeTransformationAssertions
import ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers.FailureHandlerFactory
import ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers.failureHandlerFactory
import ch.tutteli.atrium.domain.creating.anyAssertions
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

/**
 * Delegates inter alia to the implementation of [AnyAssertions].
 * In detail, it implements [AnyAssertions] by delegating to [anyAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object AnyAssertionsBuilder : AnyAssertions {

    override inline fun <T : Any> toBe(subjectProvider: SubjectProvider<T>, expected: T): Assertion =
        anyAssertions.toBe(subjectProvider, expected)

    override inline fun <T> notToBe(subjectProvider: SubjectProvider<T>, expected: T) =
        anyAssertions.notToBe(subjectProvider, expected)

    override inline fun <T> isSame(subjectProvider: SubjectProvider<T>, expected: T) =
        anyAssertions.isSame(subjectProvider, expected)

    override inline fun <T> isNotSame(subjectProvider: SubjectProvider<T>, expected: T) =
        anyAssertions.isNotSame(subjectProvider, expected)

    override inline fun <T> toBeNull(subjectProvider: SubjectProvider<T>) =
        anyAssertions.toBeNull(subjectProvider)

    override inline fun <T : Any> toBeNullable(
        assertionContainer: Expect<T?>,
        type: KClass<T>,
        expectedOrNull: T?
    ) = anyAssertions.toBeNullable(assertionContainer, type, expectedOrNull)

    override inline fun <T : Any> toBeNullIfNullGivenElse(
        assertionContainer: Expect<T?>,
        type: KClass<T>,
        noinline assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ) = anyAssertions.toBeNullIfNullGivenElse(assertionContainer, type, assertionCreatorOrNull)

    override inline fun <T, TSub : Any> isA(assertionContainer: Expect<T>, subType: KClass<TSub>) =
        anyAssertions.isA(assertionContainer, subType)


    // everything below is deprecated functionality and will be removed with 1.0.0

    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect and use toBeNullable; will be removed with 1.0.0")
    override inline fun <T : Any> isNullable(plant: AssertionPlantNullable<T?>, type: KClass<T>, expectedOrNull: T?) =
        anyAssertions.isNullable(plant, type, expectedOrNull)


    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect and use toBeNullable; will be removed with 1.0.0")
    override inline fun <T : Any> isNotNull(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        noinline assertionCreator: AssertionPlant<T>.() -> Unit
    ) = anyAssertions.isNotNull(plant, type, assertionCreator)

    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect and use toBeNullable; will be removed with 1.0.0")
    override inline fun <T : Any> isNotNullBut(plant: AssertionPlantNullable<T?>, type: KClass<T>, expected: T) =
        anyAssertions.isNotNullBut(plant, type, expected)

    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect and use toBeNullable; will be removed with 1.0.0")
    override inline fun <T : Any> isNullIfNullGivenElse(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        noinline assertionCreatorOrNull: (AssertionPlant<T>.() -> Unit)?
    ) = anyAssertions.isNullIfNullGivenElse(plant, type, assertionCreatorOrNull)


    /**
     * Returns [AnyTypeTransformationAssertionsBuilder]
     * which inter alia delegates to the implementation of [AnyTypeTransformationAssertions].
     */
    @Suppress("DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from `Assert` to `Expect` use `ExpectImpl.changeSubject` or `ExpectImpl.feature.extract` instead; will be removed with 1.0.0")
    inline val typeTransformation
        get() = AnyTypeTransformationAssertionsBuilder
}

/**
 * Delegates inter alia to the implementation of [AnyTypeTransformationAssertions].
 * In detail, it implements [AnyTypeTransformationAssertions] by delegating to [anyTypeTransformationAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
@Suppress("DEPRECATION")
@Deprecated("Switch from `Assert` to `Expect` and use `ExpectImpl` instead; will be removed with 1.0.0")
object AnyTypeTransformationAssertionsBuilder : AnyTypeTransformationAssertions {

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("This function will be removed in v.1.0.0 in favour of AssertImpl.any.isNotNull", ReplaceWith(
        "plant.addAssertion(AssertImpl.any.isNotNull(plant, type, assertionCreator))",
        "ch.tutteli.atrium.domain.builders.AssertImpl"
    ))
    override inline fun <T : Any> isNotNull(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        noinline assertionCreator: AssertionPlant<T>.() -> Unit
    ) = anyTypeTransformationAssertions.isNotNull(plant, type, assertionCreator)

    override inline fun <TSub : Any> isA(
        plant: AssertionPlant<Any>,
        subType: KClass<TSub>,
        noinline assertionCreator: AssertionPlant<TSub>.() -> Unit
    ) = anyTypeTransformationAssertions.isA(plant, subType, assertionCreator)

    override inline fun <T : Any, TSub : T> downCast(
        description: Translatable,
        subType: KClass<TSub>,
        subjectPlant: BaseAssertionPlant<T?, *>,
        noinline assertionCreator: AssertionPlant<TSub>.() -> Unit,
        failureHandler: AnyTypeTransformation.FailureHandler<T, TSub>
    ) = anyTypeTransformationAssertions.downCast(description, subType, subjectPlant, assertionCreator, failureHandler)

    override inline fun <S : Any, T : Any> transform(
        parameterObject: AnyTypeTransformation.ParameterObject<S, T>,
        noinline canBeTransformed: (S) -> Boolean,
        noinline transform: (S) -> T,
        failureHandler: AnyTypeTransformation.FailureHandler<S, T>
    ) = anyTypeTransformationAssertions.transform(parameterObject, canBeTransformed, transform, failureHandler)

    /**
     * Returns [AnyTypeTransformationFailureHandlerFactoryBuilder]
     * which inter alia delegates to the implementation of [FailureHandlerFactory].
     */
    inline val failureHandlers get() = AnyTypeTransformationFailureHandlerFactoryBuilder
}

/**
 * Delegates inter alia to the implementation of [FailureHandlerFactory].
 * In detail, it implements [FailureHandlerFactory] by delegating to [failureHandlerFactory]
 * which in turn delegates to the implementation via [loadSingleService].
 */
@Suppress("DEPRECATION")
@Deprecated("Switch from `Assert` to `Expect` and use `ExpectImpl` instead; will be removed with 1.0.0")
object AnyTypeTransformationFailureHandlerFactoryBuilder : FailureHandlerFactory {

    override inline fun <S : Any, T : Any> newExplanatory() = failureHandlerFactory.newExplanatory<S, T>()

    override inline fun <S : Any, T : Any> newExplanatoryWithHint(
        noinline showHint: () -> Boolean,
        noinline failureHintFactory: () -> Assertion
    ) = failureHandlerFactory.newExplanatoryWithHint<S, T>(showHint, failureHintFactory)
}
