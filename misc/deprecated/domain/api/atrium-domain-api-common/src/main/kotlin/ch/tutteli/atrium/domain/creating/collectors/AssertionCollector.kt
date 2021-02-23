@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect

/**
 * The access point to an implementation of [AssertionCollector].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Deprecated(
    "Use one of the utility functions: collectBasedOnSubject/collectForCompositionBasedOnSubject from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("container.assertionCollector", "ch.tutteli.atrium.logic.creating.collectors.assertionCollector")
)
val assertionCollector: AssertionCollector by lazy { loadSingleService(AssertionCollector::class) }

/**
 * Responsible to collect assertions made in an `assertionCreator`-lambda.
 */
@Deprecated("Use one of the utility functions: collectBasedOnSubject/collectForCompositionBasedOnSubject from atrium-logic; will be removed with 0.17.0")
interface AssertionCollector {

    /**
     * Uses the [Expect.maybeSubject] and delegates to the other overload.
     *
     * See the other overload for more information.
     */
    @Deprecated(
        "Use collect from atrium-logic; will be removed with 0.17.0",
        ReplaceWith("_logic.collect(assertionCreator)")
    )
    fun <T> collect(
        expect: Expect<T>,
        assertionCreator: Expect<T>.() -> Unit
    ): Assertion = collect(expect.maybeSubject, assertionCreator)

    /**
     * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
     * other action which results in an assertion container being created and
     * you do not require this resulting container.
     *
     * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
     * assertion container.
     *
     * @param maybeSubject Either [Some] wrapping the subject of the current assertion or
     *   [None] in case a previous subject change was not successful - used as subject for the given [assertionCreator].
     * @param assertionCreator A lambda which defines the assertions for the feature.
     *
     * @return The collected assertions as an [AssertionGroup] with an [InvisibleAssertionGroupType].
     *
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
     *   assertion.
     */
    @Deprecated("Use collectBasedOnSubject from atrium-logic; will be removed with 0.17.0")
    fun <T> collect(maybeSubject: Option<T>, assertionCreator: Expect<T>.() -> Unit): Assertion


    /**
     * Use this function if you want to collect [Assertion]s and use it as part of an [AssertionGroup].
     *
     * @param maybeSubject Either [Some] wrapping the subject of the current assertion or
     *   [None] in case a previous subject change was not successful - used as subject for the given [assertionCreator].
     * @param assertionCreator A lambda which defines the assertions for the feature.
     *
     * @return The collected assertions as a `List<[Assertion]>`.
     *
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
     *   assertion.
     */
    @Deprecated("Use collectForCompositionBasedOnSubject from atrium-logic; will be removed with 0.17.0")
    fun <T> collectForComposition(maybeSubject: Option<T>, assertionCreator: Expect<T>.() -> Unit): List<Assertion>
}
