package ch.tutteli.atrium.logic.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.Expect

//TODO 0.16.0 I am not sure this is actually needed, couldn't they just be helper methods?
// moreover, we need to create it via ComponentFactoryContainer as the CollectingExpect which is used internally needs to use the components
interface AssertionCollector {

    /**
     * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
     * other action which results in an [Expect] being created for a different subject and
     * you do not require this resulting [Expect].
     *
     * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
     * [Expect].
     *
     * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
     *
     * @param maybeSubject Either [Some] wrapping the subject of the current assertion or
     *   [None] in case a previous subject transformation was not successful -
     *   this will be used as subject for the given [assertionCreator].
     * @param assertionCreator A lambda which defines the expectations for the given [maybeSubject].
     *
     * @return The collected assertions.
     */
    fun <T> collect(maybeSubject: Option<T>, assertionCreator: Expect<T>.() -> Unit): Assertion


    /**
     * Use this function if you want to collect [Assertion]s and use it as part of another [Assertion] (e.g. as part
     * of an [AssertionGroup]).
     *
     * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
     *
     * @param maybeSubject Either [Some] wrapping the subject of the current assertion or
     *   [None] in case a previous subject transformation was not successful -
     *   this will be used as subject for the given [assertionCreator].
     * @param assertionCreator A lambda which defines the expectations for the given [maybeSubject].
     *
     * @return The collected assertions as a `List<[Assertion]>`.
     *
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
     *   assertion.
     */
    fun <T> collectForComposition(maybeSubject: Option<T>, assertionCreator: Expect<T>.() -> Unit): List<Assertion>
}
