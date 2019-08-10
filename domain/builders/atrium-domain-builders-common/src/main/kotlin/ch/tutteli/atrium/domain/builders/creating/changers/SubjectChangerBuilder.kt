@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating.changers

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.impl.subjectchanger.*
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.domain.creating.changers.subjectChanger
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import kotlin.reflect.KClass


/**
 * Delegates inter alia to the implementation of [SubjectChanger].
 * In detail, it delegates to [subjectChanger]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object SubjectChangerBuilder {

    inline fun <T, R> unreported(
        originalAssertionContainer: Expect<T>,
        noinline transformation: (T) -> R
    ): Expect<R> = subjectChanger.unreported(originalAssertionContainer, transformation)

    /**
     * Entry point of the building process to not only change the subject but also report the change in reporting.
     *
     * Typically the change is documented by adding a [DescriptiveAssertion] to the new resulting [Expect].
     *
     * This is basically a guide towards [SubjectChanger.reported],
     * hence in a more verbose manner but also more readable in many cases.
     */
    fun <T> reportBuilder(originalAssertionContainer: Expect<T>): DescriptionOption<T> =
        DescriptionOption.create(originalAssertionContainer)


    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    inline fun <T, R : Any> unreported(
        originalPlant: BaseAssertionPlant<T, *>,
        noinline transformation: (T) -> R
    ): Assert<R> = subjectChanger.unreported(originalPlant, transformation)

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    inline fun <T, R> unreportedNullable(
        originalPlant: BaseAssertionPlant<T, *>,
        noinline transformation: (T) -> R
    ): AssertionPlantNullable<R> = subjectChanger.unreportedNullable(originalPlant, transformation)

    /**
     * Option step which allows to specify the description and representation of the change.
     *
     * @param T the type of the current subject.
     */
    interface DescriptionOption<T> {
        /**
         * The previously specified assertion container to which the new [Expect] will delegate assertion checking.
         */
        val originalAssertionContainer: Expect<T>

        /**
         * Uses [DescriptionAnyAssertion.IS_A] as description of the change,
         * the given [subType] as representation and tries to perform a down-cast of [originalAssertionContainer]'s
         * [Expect.maybeSubject] to the given type [TSub]
         */
        //TODO once kotlin supports to have type parameters as upper bounds of another type parameter we should restrict TSub : T
        fun <TSub : Any> downCastTo(subType: KClass<TSub>): FailureHandlerOption<T, TSub> =
            withDescriptionAndRepresentation(DescriptionAnyAssertion.IS_A, subType)
                .withCheck { subType.isInstance(it) }
                .withTransformation { subType.cast(it) }

        /**
         * Uses the given [description] and [representation] to represent the change by delegating to the other overload
         * which expects a [Translatable] instead of a [String].
         *
         * See the other overload for further information.
         */
        fun withDescriptionAndRepresentation(description: String, representation: Any?): CheckOption<T> =
            withDescriptionAndRepresentation(Untranslatable(description), representation)

        /**
         * Uses the given [description] and [representation] to represent the change.
         * Moreover, subsequent options in the building step allow to define rules when the change cannot be applied, in
         * such a case an alternative description and representation might be used (depending on the implementation and
         * chosen options).
         *
         * Notice, if you want to use text (e.g. a [String]) as [representation],
         * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
         */
        fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): CheckOption<T>

        companion object {
            fun <T> create(
                originalAssertionContainer: Expect<T>
            ): DescriptionOption<T> = DescriptionOptionImpl(originalAssertionContainer)
        }
    }

    /**
     * Option step which allows to specify checks which should be consulted to see whether the subject change is
     * feasible or not.
     *
     * @param T the type of the current subject.
     */
    interface CheckOption<T> {
        /**
         * The previously specified assertion container to which the new [Expect] will delegate assertion checking.
         */
        val originalAssertionContainer: Expect<T>

        /**
         * The previously specified description which describes the kind of subject change.
         */
        val description: Translatable

        /**
         * The previously specified representation of the change.
         */
        val representation: Any

        /**
         * Defines when the current subject can be transformed to the new one.
         */
        fun withCheck(canBeTransformed: (T) -> Boolean): TransformationOption<T>

        companion object {
            fun <T> create(
                originalAssertionContainer: Expect<T>,
                description: Translatable,
                representation: Any
            ): CheckOption<T> = CheckOptionImpl(originalAssertionContainer, description, representation)
        }
    }

    /**
     * Option step to define the transformation which yields the new subject.
     *
     * @param T the type of the current subject.
     */
    interface TransformationOption<T> {
        /**
         * The so far chosen options up to but not inclusive the [CheckOption] step.
         */
        val checkOption: CheckOption<T>

        /**
         * The previously specified lambda which indicates whether we can transform the current subject
         * to the new one or not.
         */
        val canBeTransformed: (T) -> Boolean

        /**
         * Defines the new subject, most likely based on the current subject (but does not need to be).
         */
        fun <R> withTransformation(transformation: (T) -> R): FailureHandlerOption<T, R>

        companion object {
            fun <T> create(
                checkOption: CheckOption<T>,
                canBeTransformed: (T) -> Boolean
            ): TransformationOption<T> = TransformationOptionImpl(checkOption, canBeTransformed)
        }
    }

    /**
     * Option step which allows to specify a custom [SubjectChanger.FailureHandler].
     *
     * @param T the type of the current subject.
     * @param R the type of the new subject.
     */
    interface FailureHandlerOption<T, R> {
        /**
         * The so far chosen options up to the [CheckOption] step.
         */
        val checkOption: CheckOption<T>

        /**
         * The previously specified lambda which indicates whether we can transform the current subject
         * to the new one or not.
         */
        val canBeTransformed: (T) -> Boolean

        /**
         * The previously specified new subject.
         */
        val transformation: (T) -> R

        /**
         * Uses the given [failureHandler] as [SubjectChanger.FailureHandler]
         * to create the failing assertion in case the subject change fails.
         */
        fun withFailureHandler(failureHandler: SubjectChanger.FailureHandler<T, R>): FinalStep<T, R>

        /**
         * Uses the default [SubjectChanger.FailureHandler] which builds the failing assertion based on the specified
         * [CheckOption.description] and [CheckOption.representation] and includes the assertions
         * a given assertionCreator lambda would create.
         */
        fun withDefaultFailureHandler(): FinalStep<T, R>

        /**
         * Skips this step by using [withDefaultFailureHandler] and calls [FinalStep.build].
         * @return
         */
        fun build(): ChangedSubjectPostStep<T, R> = withDefaultFailureHandler().build()

        companion object {
            fun <T, R> create(
                checkOption: CheckOption<T>,
                canBeTransformed: (T) -> Boolean,
                transformation: (T) -> R
            ): FailureHandlerOption<T, R> = FailureHandlerOptionImpl(checkOption, canBeTransformed, transformation)
        }
    }

    /**
     * Final step in the change-subject-process, creates a [ChangedSubjectPostStep]
     * ased on the previously specified options.
     *
     * @param T the type of the current subject.
     * @param R the type of the new subject.
     */
    interface FinalStep<T, R> {
        /**
         * The so far chosen options up to the [CheckOption] step.
         */
        val checkOption: CheckOption<T>

        /**
         * The previously specified lambda which indicates whether we can transform the current subject
         * to the new one or not.
         */
        val canBeTransformed: (T) -> Boolean

        /**
         * The previously specified new subject.
         */
        val transformation: (T) -> R

        /**
         * The previously specified [SubjectChanger.FailureHandler].
         */
        val failureHandler: SubjectChanger.FailureHandler<T, R>

        /**
         * Finishes the `reported subject change`-process by building a new [Expect] taking the previously chosen
         * options into account.
         *
         * @return A [ChangedSubjectPostStep] which allows to define what should happen with the new [Expect].
         */
        fun build(): ChangedSubjectPostStep<T, R>

        companion object {
            fun <T, R> create(
                checkOption: CheckOption<T>,
                canBeTransformed: (T) -> Boolean,
                transformation: (T) -> R,
                failureHandler: SubjectChanger.FailureHandler<T, R>
            ): FinalStep<T, R> = FinalStepImpl(checkOption, canBeTransformed, transformation, failureHandler)
        }
    }
}
