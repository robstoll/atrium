package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.CollectingExpectImpl
import ch.tutteli.atrium.creating.proofs.Proof

/**
 * Represents a container of expectations which collects expectations of an [ExpectationCreator].
 *
 * I.e. it is intended to be used as receiver object and provides the additional method [getCollectedProofs] to query
 * the collected proofs making up the expectation.
 *
 * @param SubjectT The type of the subject of this [Expect].
 */
interface CollectingExpect<SubjectT> : Expect<SubjectT> {

    /**
     * Returns the [ch.tutteli.atrium.assertions.Assertion]s which have been [appended][append] to this container.
     *
     * @return The [ch.tutteli.atrium.assertions.Assertion]s which have been [appended][append] to this container.
     */
    @Suppress("DEPRECATION")
    @Deprecated("Assertion is deprecated, move to Proof and use getCollectedProofs instead. Will be removed with 2.0.0 at the latest", ReplaceWith("this.getCollectedProofs()"))
    fun getAssertions(): List<ch.tutteli.atrium.assertions.Assertion>

    //TODO 1.3.0 KDOC
    /**
     *
     * @since 1.3.0
     */
    fun getCollectedProofs(): List<Proof>

    /**
     * Appends the [Proof]s the given [assertionCreator] creates to this container and
     * returns an [Expect] which includes them.
     *
     * Whether the returned [Expect] is the same as the initial one is up to the implementation (i.e. if a mutable
     * structure is used or an immutable). Atrium strives for an immutable data structure in the long run and will
     * little by little refactor the code accordingly.
     *
     * @param assertionCreator The lambda which will create assertions.
     *
     * @return an [Expect] for the subject of `this` expectation.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Proof]s are immediately evaluated.
     */
    @Deprecated(
        "Use appendAsGroupIndicateIfOneCollected and define the alternative or pass an empty list if you don't have any",
        ReplaceWith("this.appendAsGroupIndicateIfOneCollected(assertionCreator, listOf(/* .. add a usage hint in case you have an overload which does not expect an expectationCreator */))")
    )
    fun appendAsGroup(assertionCreator: Expect<SubjectT>.() -> Unit): CollectingExpect<SubjectT>

    /**
     * Appends the [Proof]s the given [expectationCreatorWithUsageHints] creates to this container and
     * returns an [Expect] which includes them next to a flag which indicates if at least one was appended.
     *
     * Whether the returned [Expect] is the same as the initial one is up to the implementation (i.e. if a mutable
     * structure is used or an immutable). Atrium strives for an immutable data structure in the long run and will
     * little by little refactor the code accordingly.
     *
     * @param expectationCreatorWithUsageHints defines the [ExpectationCreator]-lambda as well as the usage hints which
     *   are shown in case no expectation is created within the lambda.
     *
     * @return A pair consisting of an [Expect] for the subject of `this` expectation and a [Pair.second] element
     *   indicating whether at least one [Proof] was appended to it, i.e. the flag will be `true` if this is the case
     *   and `false` if none was appended.
     *
     * @throws AssertionError Might throw an [AssertionError] in case the container evaluates [Proof]s immediately and
     *   one of the created [Proof]s does not [hold][Proof.holds].
     */
    fun appendAsGroupIndicateIfOneCollected(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectT>
    ): Pair<CollectingExpect<SubjectT>, Boolean>

    companion object {
        @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
        operator fun <T> invoke(
            maybeSubject: Option<T>,
            componentFactoryContainer: ComponentFactoryContainer
        ): CollectingExpect<T> = CollectingExpectImpl(maybeSubject, componentFactoryContainer)
    }
}
