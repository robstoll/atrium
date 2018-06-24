package ch.tutteli.atrium.domain.creating.any.typetransformation

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation.FailureHandler
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract for sophisticated `type transformation` assertion builders.
 *
 * The assertion is created by a [AnyTypeTransformation.Creator] which itself uses a [FailureHandler] to
 * report a failing transformation.
 */
interface AnyTypeTransformation {

    /**
     * Represents the final step of a sophisticated `type transformation` assertion builder which creates the
     * [Assertion] as such.
     *
     * @param S The type of [AssertionPlant.subject].
     * @param T The target type to which [AssertionPlant.subject] should have been transformed to.
     */
    interface Creator<S : Any, T : Any> {
        /**
         * Creates the type transformation [Assertion] and ads it to the given [ParameterObject.subjectPlant] and
         * delegates to the given [failureHandler] if the transformation fails.
         *
         * @param parameterObject The [ParameterObject] containing inter alia [ParameterObject.assertionCreator] to
         *   create subsequent assertions.
         * @param canBeTransformed Defines whether the subject of the given [ParameterObject.subjectPlant]
         *   (with type [S]) can be transformed to the target type [T].
         * @param transform The transformation function as such.
         * @param failureHandler The failure handler which is called if the transformation cannot be executed
         */
        fun create(
            parameterObject: ParameterObject<S, T>,
            canBeTransformed: (S) -> Boolean,
            transform: (S) -> T,
            failureHandler: FailureHandler<S, T>
        )
    }

    /**
     * A handler which is responsible to create the [Assertion] of a failed type transformation.
     *
     * It is also responsible to decide how the lambda -- which could have created subsequent assertions for the
     * transformed [AssertionPlant.subject] if the transformation of the [AssertionPlant.subject] to type [T]
     * did not fail -- should be used in reporting.
     *
     * @param S The type of [AssertionPlant.subject].
     * @param T The target type to which [AssertionPlant.subject] should have been transformed to.
     */
    interface FailureHandler<in S : Any, out T : Any> {

        /**
         * Creates the failing assertion and adds it to the [ParameterObject.subjectPlant].
         *
         * Typically it uses [createFailingAssertion] to create the failing assertion and augments it with
         * collected assertions or such.
         *
         * @param parameterObject The [ParameterObject] containing inter alia [ParameterObject.assertionCreator], the
         *   lambda which could have created subsequent assertions for the transformed [AssertionPlant.subject].
         *
         * @throws AssertionError Might throw an [AssertionError] depending on the [ParameterObject.subjectPlant].
         */
        fun createAndAddAssertionToPlant(parameterObject: ParameterObject<S, T>)

        /**
         * Creates the failing [Assertion] based on the given [description] and [representation]
         */
        fun createFailingAssertion(description: Translatable, representation: Any): Assertion
    }

    /**
     * A parameter object which contains all necessary information to report a failure
     *
     * @param description Describes what assertion the type transformation represents, e.g. `is a` in case of a down cast.
     * @param representation The representation of the expected result, e.g. `Int::class` in case of a down cast
     *   from [Number] to [Int].
     * @param subjectPlant The plant to which the assertion (including additional assertions created by
     *   [assertionCreator]) should be added.
     * @param assertionCreator The lambda which can create subsequent assertions for the transformed
     *   [AssertionPlant.subject] in case the type transformation succeeds
     * @param warningTransformationFailed Explains why the [subjectPlant]'s [subject][AssertionPlant.subject]
     *   could not be transformed to the desired type.
     */
    data class ParameterObject<out S : Any, in T : Any>(
        val description: Translatable,
        val representation: Any,
        val subjectPlant: BaseAssertionPlant<S?, *>,
        val assertionCreator: AssertionPlant<T>.() -> Unit,
        val warningTransformationFailed: Translatable
    )
}
