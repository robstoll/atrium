package ch.tutteli.atrium.creating.transformers

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectationCreator
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.transformers.impl.builders.subjectchanger.*
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionAnyProof
import kotlin.reflect.KClass

/**
 * Helps in using [SubjectChanger] by providing a guide to set the different parameters in form of a fluent builder.
 *
 * @since 1.3.0
 */
interface SubjectChangerBuilder {

    /**
     * Extension point for [SubjectChangerBuilder].
     *
     * @since 1.3.0
     */
    companion object {
        /**
         * Entry point to use the [SubjectChangerBuilder].
         */
        operator fun <SubjectT> invoke(container: ProofContainer<SubjectT>): KindStep<SubjectT> =
            KindStepImpl(container)
    }

    /**
     * Step where one has to decide the kind of subject change.
     *
     * @param SubjectT The type of the current subject (before the change).
     *
     * @since 1.3.0
     */
    interface KindStep<SubjectT> {
        /**
         * The previously specified proof container to which the new [Expect] will delegate proof checking.
         *
         * @since 1.3.0
         */
        val container: ProofContainer<SubjectT>


        /**
         * First, [FinalStep] as well as [ExecutionStep] in the change-subject-process --
         * changes the subject without showing the change as such in reporting.
         *
         * @return The newly created [Expect] for the new subject of type [SubjectAfterChangeT].
         *
         * @since 1.3.0
         */
        fun <SubjectAfterChangeT> unreported(
            transformation: (SubjectT) -> SubjectAfterChangeT
        ): Expect<SubjectAfterChangeT> = container.subjectChanger.unreported(container, transformation)

        /**
         * Entry point of the building process to not only change the subject but also report the change in reporting.
         *
         * Typically, the change is documented by appending a [Proof] to the new resulting [Expect].
         *
         * This is basically a guide towards [SubjectChanger.reported],
         * hence in a more verbose manner but also more readable in many cases.
         *
         * @since 1.3.0
         */
        fun reportBuilder(): DescriptionRepresentationStep<SubjectT>
    }

    /**
     * Step which allows to specify the description and representation of the change.
     *
     * @param SubjectT The type of the current subject (before the change).
     *
     * @since 1.3.0
     */
    interface DescriptionRepresentationStep<SubjectT> {
        /**
         * The previously specified proof container to which the new [Expect] will delegate proof checking.
         *
         * @since 1.3.0
         */
        val container: ProofContainer<SubjectT>

        /**
         * Uses [DescriptionAnyProof.TO_BE_AN_INSTANCE_OF] as description of the change,
         * the given [subType] as representation and tries to perform a down-cast of [container]'s
         * [ProofContainer.maybeSubject] to the given type [SubTypeOfSubjectT]
         *
         * @since 1.3.0
         */
        @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
        fun <SubTypeOfSubjectT> downCastTo(
            subType: KClass<SubTypeOfSubjectT>
        ): FailureHandlerStep<SubjectT, SubTypeOfSubjectT> where SubTypeOfSubjectT : Any, SubTypeOfSubjectT : SubjectT =
            withDescriptionAndRepresentation(DescriptionAnyProof.TO_BE_AN_INSTANCE_OF, subType)
                .withTransformation {
                    Option.someIf(subType.isInstance(it)) { subType.cast(it) }
                }

        /**
         * Uses the given [description] and [representation] to represent the change by delegating to the other overload
         * which expects an [InlineElement] instead of a [String] wrapping the given [description] into a [Text].
         *
         * See the other overload for further information.
         *
         * @since 1.3.0
         */
        fun withDescriptionAndRepresentation(description: String, representation: Any?): TransformationStep<SubjectT> =
            withDescriptionAndRepresentation(Text(description), representation)

        /**
         * Uses the given [description] and [representation] to represent the change.
         * Moreover, subsequent options in the building step allow to define rules when the change cannot be applied, in
         * such a case an alternative description and representation might be used (depending on the implementation and
         * chosen options).
         *
         * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
         * then wrap it into a [Text] and pass it instead.
         *
         * @since 1.3.0
         */
        fun withDescriptionAndRepresentation(
            description: InlineElement,
            representation: Any?
        ): TransformationStep<SubjectT>

        /**
         * Extension point for [DescriptionRepresentationStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates a [DescriptionRepresentationStep] in the context of the [SubjectChangerBuilder].
             *
             * @since 1.3.0
             */
            operator fun <SubjectT> invoke(
                container: ProofContainer<SubjectT>
            ): DescriptionRepresentationStep<SubjectT> = DescriptionRepresentationStepImpl(container)
        }
    }

    /**
     * Step to define the transformation which yields the new subject wrapped into a [Some] if the transformation
     * as such can be carried out; otherwise [None].
     *
     * @param SubjectT The type of the current subject (before the change).
     *
     * @since 1.3.0
     */
    interface TransformationStep<SubjectT> {
        /**
         * The previously specified proof container to which the new [Expect] will delegate proof checking.
         *
         * @since 1.3.0
         */
        val container: ProofContainer<SubjectT>

        /**
         * The previously specified description which describes the kind of subject change.
         *
         * @since 1.3.0
         */
        val description: InlineElement

        /**
         * The previously specified representation of the change.
         *
         * @since 1.3.0
         */
        val representation: Any

        /**
         * Defines the new subject, most likely based on the current subject (but does not need to be).
         *
         * @param SubjectAfterChangeT The type of the subject after the change.
         *
         * @since 1.3.0
         */
        fun <SubjectAfterChangeT> withTransformation(
            transformation: (SubjectT) -> Option<SubjectAfterChangeT>
        ): FailureHandlerStep<SubjectT, SubjectAfterChangeT>

        /**
         * Extension point for [TransformationStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates a [TransformationStep] in the context of the [SubjectChangerBuilder].
             *
             * @since 1.3.0
             */
            operator fun <T> invoke(
                container: ProofContainer<T>,
                description: InlineElement,
                representation: Any
            ): TransformationStep<T> = TransformationStepImpl(container, description, representation)
        }
    }

    /**
     * Optional step which allows to specify a custom [SubjectChanger.FailureHandler].
     *
     * @param SubjectT The type of the current subject (before the change).
     * @param SubjectAfterChangeT the type of the new subject.
     *
     * @since 1.3.0
     */
    //TODO 1.3.0 we intended to merge the existing FailureHandlers as far as I reckon. Would still keep this so
    // that someone else could provide another implementation but internally we should probably only use the default
    interface FailureHandlerStep<SubjectT, SubjectAfterChangeT> {
        /**
         * The so far chosen options up to the [TransformationStep] step.
         *
         * @since 1.3.0
         */
        val transformationStep: TransformationStep<SubjectT>

        /**
         * The previously specified transformation which will yield the new subject.
         *
         * @since 1.3.0
         */
        val transformation: (SubjectT) -> Option<SubjectAfterChangeT>

        /**
         * Uses the given [failureHandler] as [SubjectChanger.FailureHandler]
         * to create a failing [Proof] in case the subject change fails.
         *
         * @since 1.3.0
         */
        fun withFailureHandler(
            failureHandler: SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT>
        ): FinalStep<SubjectT, SubjectAfterChangeT>

        /**
         * Uses the given [failureHandler] as [SubjectChanger.FailureHandler]
         * to create a failing [Proof] in case the subject change fails but previously maps the subject from
         * [SubjectT] to [SubjectIntermediateT] as the failure handler only deals with [SubjectIntermediateT] subjects.
         *
         * @since 1.3.0
         */
        fun <SubjectIntermediateT> withFailureHandlerAdapter(
            failureHandler: SubjectChanger.FailureHandler<SubjectIntermediateT, SubjectAfterChangeT>,
            map: (SubjectT) -> SubjectIntermediateT
        ): FinalStep<SubjectT, SubjectAfterChangeT> = withFailureHandler(FailureHandlerAdapter(failureHandler, map))

        /**
         * Uses the default [SubjectChanger.FailureHandler] which builds the failing assertion based on the specified
         * [TransformationStep.description] and [TransformationStep.representation] and includes the proofs
         * a given [ExpectationCreator] would create.
         *
         * @since 1.3.0
         */
        fun withDefaultFailureHandler(): FinalStep<SubjectT, SubjectAfterChangeT>

        /**
         * Skips this step by using [withDefaultFailureHandler] and calls [FinalStep.build].
         *
         * @since 1.3.0
         */
        fun build(): ExecutionStep<SubjectT, SubjectAfterChangeT> = withDefaultFailureHandler().build()

        /**
         * Extension point for [FailureHandlerStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates a [FailureHandlerStep] in the context of the [SubjectChangerBuilder].
             *
             * @since 1.3.0
             */
            operator fun <SubjectT, SubjectAfterChangeT> invoke(
                transformationStep: TransformationStep<SubjectT>,
                transformation: (SubjectT) -> Option<SubjectAfterChangeT>
            ): FailureHandlerStep<SubjectT, SubjectAfterChangeT> =
                FailureHandlerStepImpl(transformationStep, transformation)
        }
    }

    /**
     * Final step in the help-me-to-call-[SubjectChanger]-process, which creates an [ExecutionStep] incorporating all
     * chosen options and is able to call [SubjectChanger] accordingly.
     *
     * @param SubjectT The type of the current subject (before the change).
     * @param SubjectAfterChangeT the type of the new subject.
     *
     * @since 1.3.0
     */
    interface FinalStep<SubjectT, SubjectAfterChangeT> {
        /**
         * The so far chosen options up to the [TransformationStep] step.
         *
         * @since 1.3.0
         */
        val transformationStep: TransformationStep<SubjectT>

        /**
         * The previously specified new subject.
         *
         * @since 1.3.0
         */
        val transformation: (SubjectT) -> Option<SubjectAfterChangeT>

        /**
         * The previously specified [SubjectChanger.FailureHandler].
         *
         * @since 1.3.0
         */
        val failureHandler: SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT>

        /**
         * Finishes the help-me-to-call-[SubjectChanger]-process by creating an [ExecutionStep] incorporating all
         * previously chosen options.
         *
         * @return The [ExecutionStep] which allows to define how the change shall be carried out.
         *
         * @since 1.3.0
         */
        fun build(): ExecutionStep<SubjectT, SubjectAfterChangeT>

        /**
         * Extension point for [FinalStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates the [FinalStep] in the context of the [SubjectChangerBuilder].
             *
             * @since 1.3.0
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
     * For instance, if it shall just perform the transformation and return the new [Expect] of type
     * [SubjectAfterChangeT] or if it shall pass an [ExpectationCreator] which creates sub-proofs etc.
     *
     * @since 1.3.0
     */
    interface ExecutionStep<SubjectT, SubjectAfterChangeT> :
        TransformationExecutionStep<SubjectT, SubjectAfterChangeT, Expect<SubjectAfterChangeT>> {

        /**
         * Extension point for [ExecutionStep].
         *
         * @since 1.3.0
         */
        companion object {
            /**
             * Creates the [FinalStep] in the context of the [SubjectChangerBuilder].
             *
             * @since 1.3.0
             */
            operator fun <SubjectT, SubjectAfterChangeT> invoke(
                container: ProofContainer<SubjectT>,
                action: (ProofContainer<SubjectT>) -> Expect<SubjectAfterChangeT>,
                actionAndApply: (ProofContainer<SubjectT>, ExpectationCreatorWithUsageHints<SubjectAfterChangeT>) -> Expect<SubjectAfterChangeT>
            ): ExecutionStep<SubjectT, SubjectAfterChangeT> = ExecutionStepImpl(container, action, actionAndApply)
        }
    }

}
