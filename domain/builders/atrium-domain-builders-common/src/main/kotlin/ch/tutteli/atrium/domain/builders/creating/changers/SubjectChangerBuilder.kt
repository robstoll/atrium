@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating.changers

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.impl.subjectchanger.*
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.domain.creating.changers.subjectChanger
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import kotlin.reflect.KClass


/**
 * Delegates inter alia to the implementation of [SubjectChanger].
 * In detail, it delegates to [subjectChanger]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object SubjectChangerBuilder : SubjectChanger {

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override inline fun <T, R : Any> unreported(
        originalPlant: BaseAssertionPlant<T, *>,
        noinline transformation: (T) -> R
    ): Assert<R> = subjectChanger.unreported(originalPlant, transformation)

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override inline fun <T, R> unreportedNullable(
        originalPlant: BaseAssertionPlant<T, *>,
        noinline transformation: (T) -> R
    ): AssertionPlantNullable<R> = subjectChanger.unreportedNullable(originalPlant, transformation)


    override inline fun <T, R> unreported(
        originalAssertionContainer: Expect<T>,
        noinline transformation: (T) -> R
    ): Expect<R> = subjectChanger.unreported(originalAssertionContainer, transformation)

    override inline fun <T, R> reported(
        originalAssertionContainer: Expect<T>,
        description: Translatable,
        representation: Any,
        noinline canBeTransformed: (T) -> Boolean,
        noinline transformation: (T) -> R,
        noinline subAssertions: (Expect<R>.() -> Unit)?
    ): Expect<R> = subjectChanger.reported(
        originalAssertionContainer,
        description,
        representation,
        canBeTransformed,
        transformation,
        subAssertions
    )

    /**
     * Entry point of the building process to not only change the subject but also report the change in reporting.
     *
     * Typically the change is documented by adding a [DescriptiveAssertion] to the new resulting [Expect].
     *
     * This is basically a guide towards [reported], hence in a more verbose manner but also more readable in many cases.
     */
    fun <T> reportBuilder(originalAssertionContainer: Expect<T>): DescriptionOption<T> =
        DescriptionOption.create(originalAssertionContainer)


    /**
     * Option step which allows to specify the description and representation of the change.
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
        fun <TSub: Any> downCastTo(subType: KClass<TSub>): SubAssertionOption<T, TSub>
            = withDescriptionAndRepresentation(DescriptionAnyAssertion.IS_A, subType)
            .withCheck { subType.isInstance(it) }
            .withTransformation { subType.cast(it) }

        /**
         * Uses the given [description] and [representation] to represent the change.
         * Unless [representation] is null in which case a representation for null is used.
         * Moreover, subsequent options in the building step allow to define rules when the change cannot be applied, in
         * such a case an alternative description and representation might be used (depending on the implementation and
         * chosen options).
         */
        fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): CheckOption<T>

        companion object {
            fun <T> create(
                originalAssertionContainer: Expect<T>
            ): DescriptionOption<T> = DescriptionOptionImpl(originalAssertionContainer) }
    }

    /**
     *  Option step which allows to specify checks which should be consulted to see whether the subject change is
     *  feasible or not.
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
        fun <R> withTransformation(transformation: (T) -> R): SubAssertionOption<T, R>

        companion object {
            fun <T> create(
                checkOption: CheckOption<T>,
                canBeTransformed: (T) -> Boolean
            ): TransformationOption<T> = TransformationOptionImpl(checkOption, canBeTransformed)
        }
    }

    /**
     *  Option step which allows to specify sub assertions for the new subject and are applied as an
     *  [AssertionGroup].
     */
    interface SubAssertionOption<T, R> {
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
         * In case [assertionCreator] is `null` it delegates to [withoutSubAssertions] otherwise to [withSubAssertions].
         */
        fun maybeWithSubAssertions(assertionCreator: (Expect<R>.() -> Unit)?): FinalStep<T, R> =
            if (assertionCreator != null) withSubAssertions(assertionCreator)
            else withoutSubAssertions()

        /**
         * Perform the change without providing subsequent assertions for the new subject.
         *
         * We recommend using [withSubAssertions] whenever you have sub assertions as they will be reflected in
         * reporting in case the subject change cannot be carried out.
         */
        fun withoutSubAssertions(): FinalStep<T, R>

        /**
         * Defines sub assertions for the new subject (after the change).
         *
         * In contrast to [withoutSubAssertions] we try to reflect the sub assertions in reporting. For instance
         * ```
         * expect(null as Int?).notToBeNull { isLessThan(1) }
         * ```
         * Will result in an error where the reporting will be along the line of
         * ```
         * expect: null
         * - is less than: 1
         *   >> transformation to type Int failed
         * ```
         */
        fun withSubAssertions(assertionCreator: Expect<R>.() -> Unit): FinalStep<T, R>

        companion object {
            fun <T, R> create(
                checkOption: CheckOption<T>,
                canBeTransformed: (T) -> Boolean,
                transformation: (T) -> R
            ): SubAssertionOption<T, R> = SubAssertionOptionImpl(checkOption, canBeTransformed, transformation)
        }
    }

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
         * Optionally, sub assertions for the new subject.
         */
        val subAssertions: (Expect<R>.() -> Unit)?

        /**
         * Finishes the `reported subject change`-process by building a new [Expect] taking the previously chosen
         * options into account.
         *
         * @return the newly created [Expect].
         */
        fun build(): Expect<R>

        companion object {
            fun <T, R> create(
                checkOption: CheckOption<T>,
                canBeTransformed: (T) -> Boolean,
                transformation: (T) -> R,
                subAssertions: (Expect<R>.() -> Unit)?
            ): FinalStep<T, R> = FinalStepImpl(checkOption, canBeTransformed, transformation, subAssertions)
        }
    }
}
