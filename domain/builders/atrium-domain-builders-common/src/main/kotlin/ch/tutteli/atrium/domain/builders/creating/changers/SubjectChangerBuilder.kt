@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating.changers

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.impl.DescriptionOptionImpl
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.domain.creating.changers.subjectChanger
import ch.tutteli.atrium.reporting.translating.Translatable


/**
 * Delegates inter alia to the implementation of [SubjectChanger].
 * In detail, it delegates to [subjectChanger]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object SubjectChangerBuilder : SubjectChanger {

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
        DescriptionOptionImpl(originalAssertionContainer)


    /**
     * Option step which allows to specify the description and representation of the change.
     */
    interface DescriptionOption<T> {
        /**
         * The previously specified assertion container to which the new [Expect] will delegate assertion checking.
         */
        val originalAssertionContainer: Expect<T>

        /**
         * Uses the given [description] and [representation] to represent the change.
         * Unless [representation] is null in which case a representation for null is used.
         * Moreover, subsequent options in the building step allow to define rules when the change cannot be applied, in
         * such a case an alternative description and representation might be used (depending on the implementation and
         * chosen options).
         */
        fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): CheckOption<T>
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
        fun withCheck(canBeTransformed: (T) -> Boolean): SubjectProviderOption<T>
    }

    /**
     * Option step to define the new subject
     */
    interface SubjectProviderOption<T> {
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
    }

    /**
     *  Option step which allows to specify whether sub assertions are immediately provided which will be which should be consulted to see whether the subject change is
     *  feasible or not.
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
         * Will result in an error reporting along the line of
         * ```
         * expect: null
         * - is less than: 1
         *   >> transformation to type Int failed
         * ```
         */
        fun withSubAssertions(assertionCreator: Expect<R>.() -> Unit): FinalStep<T, R>
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
    }
}
