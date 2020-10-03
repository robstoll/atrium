package ch.tutteli.atrium.logic.creating.transformers

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.impl.subjectchanger.*
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import kotlin.reflect.KClass

/**
 * Helps in using [SubjectChanger] by providing a guide to set the different parameters in form of a fluent builder.
 */
interface SubjectChangerBuilder {

    companion object {
        /**
         * Entry point to use the [SubjectChangerBuilder].
         */
        operator fun <T> invoke(container: AssertionContainer<T>): KindStep<T> =
            KindStepImpl(container)
    }

    /**
     * Step where one has to decide the kind of subject change.
     *
     * @param T the type of the current subject.
     */
    interface KindStep<T> {
        /**
         * The previously specified assertion container to which the new [Expect] will delegate.
         */
        val container: AssertionContainer<T>


        /**
         * First, [FinalStep] as well as [ExecutionStep] in the change-subject-process --
         * changes the subject without showing the change as such in reporting.
         *
         * @return The newly created [Expect] for the new subject of type [R].
         */
        fun <R> unreported(transformation: (T) -> R): Expect<R> =
            container.subjectChanger.unreported(container, transformation)

        /**
         * Entry point of the building process to not only change the subject but also report the change in reporting.
         *
         * Typically the change is documented by adding a [DescriptiveAssertion] to the new resulting [Expect].
         *
         * This is basically a guide towards [SubjectChanger.reported],
         * hence in a more verbose manner but also more readable in many cases.
         */
        fun reportBuilder(): DescriptionRepresentationStep<T>
    }

    /**
     * Step which allows to specify the description and representation of the change.
     *
     * @param T the type of the current subject.
     */
    interface DescriptionRepresentationStep<T> {
        /**
         * The previously specified assertion container to which the new [Expect] will delegate assertion checking.
         */
        val container: AssertionContainer<T>

        /**
         * Uses [DescriptionAnyAssertion.IS_A] as description of the change,
         * the given [subType] as representation and tries to perform a down-cast of [container]'s
         * [AssertionContainer.maybeSubject] to the given type [TSub]
         */
        //TODO once kotlin supports to have type parameters as upper bounds of another type parameter next to `: Any` we should restrict TSub : T & Any
        fun <TSub : Any> downCastTo(subType: KClass<TSub>): FailureHandlerStep<T, TSub> =
            withDescriptionAndRepresentation(DescriptionAnyAssertion.IS_A, subType)
                .withTransformation {
                    Option.someIf(subType.isInstance(it)) { subType.cast(it) }
                }

        /**
         * Uses the given [description] and [representation] to represent the change by delegating to the other overload
         * which expects a [Translatable] instead of a [String].
         *
         * See the other overload for further information.
         */
        fun withDescriptionAndRepresentation(description: String, representation: Any?): TransformationStep<T> =
            withDescriptionAndRepresentation(Untranslatable(description), representation)

        /**
         * Uses the given [description] and [representation] to represent the change.
         * Moreover, subsequent options in the building step allow to define rules when the change cannot be applied, in
         * such a case an alternative description and representation might be used (depending on the implementation and
         * chosen options).
         *
         * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
         * then wrap it into a [Text] and pass it instead.
         */
        fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): TransformationStep<T>

        companion object {
            /**
             * Creates a [DescriptionRepresentationStep] in the context of the [SubjectChangerBuilder].
             */
            operator fun <T> invoke(
                container: AssertionContainer<T>
            ): DescriptionRepresentationStep<T> = DescriptionRepresentationStepImpl(container)
        }
    }

    /**
     * Step to define the transformation which yields the new subject wrapped into a [Some] if the transformation
     * as such can be carried out; otherwise [None].
     *
     * @param T the type of the current subject.
     */
    interface TransformationStep<T> {
        /**
         * The previously specified assertion container to which the new [Expect] will delegate.
         */
        val container: AssertionContainer<T>

        /**
         * The previously specified description which describes the kind of subject change.
         */
        val description: Translatable

        /**
         * The previously specified representation of the change.
         */
        val representation: Any

        /**
         * Defines the new subject, most likely based on the current subject (but does not need to be).
         */
        fun <R> withTransformation(transformation: (T) -> Option<R>): FailureHandlerStep<T, R>

        companion object {
            /**
             * Creates a [TransformationStep] in the context of the [SubjectChangerBuilder].
             */
            operator fun <T> invoke(
                container: AssertionContainer<T>,
                description: Translatable,
                representation: Any
            ): TransformationStep<T> = TransformationStepImpl(container, description, representation)
        }
    }

    /**
     * Optional step which allows to specify a custom [SubjectChanger.FailureHandler].
     *
     * @param T the type of the current subject.
     * @param R the type of the new subject.
     */
    interface FailureHandlerStep<T, R> {
        /**
         * The so far chosen options up to the [TransformationStep] step.
         */
        val transformationStep: TransformationStep<T>

        /**
         * The previously specified transformation which will yield the new subject.
         */
        val transformation: (T) -> Option<R>

        /**
         * Uses the given [failureHandler] as [SubjectChanger.FailureHandler]
         * to create the failing assertion in case the subject change fails.
         */
        fun withFailureHandler(failureHandler: SubjectChanger.FailureHandler<T, R>): FinalStep<T, R>

        /**
         * Uses the given [failureHandler] as [SubjectChanger.FailureHandler]
         * to create the failing assertion in case the subject change fails but previously maps the subject from
         * [T] to [R1] as the failure handler only deals with [R1] subjects.
         */
        fun <R1> withFailureHandlerAdapter(
            failureHandler: SubjectChanger.FailureHandler<R1, R>,
            map: (T) -> R1
        ): FinalStep<T, R> = withFailureHandler(FailureHandlerAdapter(failureHandler, map))

        /**
         * Uses the default [SubjectChanger.FailureHandler] which builds the failing assertion based on the specified
         * [TransformationStep.description] and [TransformationStep.representation] and includes the assertions
         * a given assertionCreator lambda would create.
         */
        fun withDefaultFailureHandler(): FinalStep<T, R>

        /**
         * Skips this step by using [withDefaultFailureHandler] and calls [FinalStep.build].
         * @return
         */
        fun build(): ExecutionStep<T, R> = withDefaultFailureHandler().build()

        companion object {
            /**
             * Creates a [FailureHandlerStep] in the context of the [SubjectChangerBuilder].
             */
            operator fun <T, R> invoke(
                transformationStep: TransformationStep<T>,
                transformation: (T) -> Option<R>
            ): FailureHandlerStep<T, R> = FailureHandlerStepImpl(transformationStep, transformation)
        }
    }

    /**
     * Final step in the help-me-to-call-[SubjectChanger]-process, which creates an [ExecutionStep] incorporating all
     * chosen options and is able to call [SubjectChanger] accordingly.
     *
     * @param T the type of the current subject.
     * @param R the type of the new subject.
     */
    interface FinalStep<T, R> {
        /**
         * The so far chosen options up to the [TransformationStep] step.
         */
        val transformationStep: TransformationStep<T>

        /**
         * The previously specified new subject.
         */
        val transformation: (T) -> Option<R>

        /**
         * The previously specified [SubjectChanger.FailureHandler].
         */
        val failureHandler: SubjectChanger.FailureHandler<T, R>

        /**
         * Finishes the help-me-to-call-[SubjectChanger]-process by creating an [ExecutionStep] incorporating all
         * previously chosen options.
         *
         * @return The [ExecutionStep] which allows to define how the change shall be carried out.
         */
        fun build(): ExecutionStep<T, R>

        companion object {
            /**
             * Creates the [FinalStep] in the context of the [SubjectChangerBuilder].
             */
            operator fun <T, R> invoke(
                transformationStep: TransformationStep<T>,
                transformation: (T) -> Option<R>,
                failureHandler: SubjectChanger.FailureHandler<T, R>
            ): FinalStep<T, R> = FinalStepImpl(transformationStep, transformation, failureHandler)
        }
    }

    /**
     * Step which allows to decide how the transformation shall be executed.
     *
     * For instance, if it shall just perform the transformation and return the new [Expect] of type [R]
     * or if it shall pass an assertionCreator-lambda which creates sub-assertions etc.
     */
    interface ExecutionStep<T, R> : TransformationExecutionStep<T, R, Expect<R>> {

        companion object {
            /**
             * Creates the [FinalStep] in the context of the [SubjectChangerBuilder].
             */
            operator fun <T, R> invoke(
                container: AssertionContainer<T>,
                action: (AssertionContainer<T>) -> Expect<R>,
                actionAndApply: (AssertionContainer<T>, Expect<R>.() -> Unit) -> Expect<R>
            ): ExecutionStep<T, R> = ExecutionStepImpl(container, action, actionAndApply)
        }
    }

}
