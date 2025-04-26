package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.reportables.Reportable
import kotlin.reflect.KClass

/**
 * Represents the extension point of the domain level for subjects of type [SubjectT].
 *
 * In contrast to expectation functions defined for [Expect] which usually return [Expect], functions defined for
 * [ProofContainer] (we call them proof functions) return [Proof] (or even more generally [Reportable])
 *
 * @param SubjectT The type of the subject of `this` expectation.
 *
 * @since 1.3.0
 */
//TODO remove AssertionContainer (and the Suppress) with Kotlin 2.0.0 at the latest
interface ProofContainer<SubjectT> : @Suppress("DEPRECATION") AssertionContainer<SubjectT> {
    /**
     * Either [Some] wrapping the subject of a [Proof] or [None] in case a previous subject transformation
     * could not be carried out.
     *
     * @since 1.3.0
     */
    override val maybeSubject: Option<SubjectT>

    /**
     * Do not use yet, this is experimental and might change or be removed without prior notice.
     *
     * @since 1.3.0
     */
    //TODO 1.3.0 maybe it would be better to have proofFactories as val like we have components?
    //TODO 1.3.0 I guess it would make sense to get rid of getImpl and only use the ComponentFactoryContainer approach
    // however, check if extensibility for a library author is still given. We don't want that a consumer of a third-party
    // expectation function collection-library needs to use an own expectation verb
    @ExperimentalNewExpectTypes
    override fun <ImplT : Any> getImpl(kClass: KClass<ImplT>, defaultFactory: () -> ImplT): ImplT

    /**
     * Do not use yet, this is experimental
     *
     * @since 1.3.0
     */
    @ExperimentalComponentFactoryContainer
    override val components: ComponentFactoryContainer

    /**
     * Appends the given [proof] to this container and returns an [Expect] which includes it.
     *
     * Whether the returned [Expect] is the same as the initial one is up to the implementation (i.e. if a mutable
     * structure is used or an immutable). Atrium strives for an immutable data structure in the long run and will
     * little by little refactor the code accordingly.
     *
     * @param proof The [Proof] which will be appended to this container.
     *
     * @return an [Expect] for the subject of `this` expectation.
     *
     * @throws AssertionError Might throw an [AssertionError] in case the container evaluates [Proof]s immediately and
     *   the given [proof] does not [hold][Proof.holds].
     *
     * @since 1.3.0
     */
    fun append(proof: Proof): Expect<SubjectT>

    /**
     * Appends the [Proof]s the given [ExpectationCreatorWithUsageHints.expectationCreator] creates to this container and
     * returns an [Expect] which includes them next to a flag which indicates if at least one was appended.
     *
     * Whether the returned [Expect] is the same as the initial one is up to the implementation (i.e. if a mutable
     * structure is used or an immutable). Atrium strives for an immutable data structure in the long run and will
     * little by little refactor the code accordingly.
     *
     * @param expectationCreatorWithUsageHints defines the expectationCreator-lambda as well as the usage hints which
     *   are shown in case no expectation is created within the lambda
     *
     * @return A pair consisting of an [Expect] for the subject of `this` expectation and a [Pair.second] element
     *   indicating whether at least one [Proof] was appended to it, i.e. the flag will be `true` if this is the case
     *   and `false` if none was appended.
     *
     * @throws AssertionError Might throw an [AssertionError] in case the container evaluates [Proof]s immediately and
     *   one of the created [Proof]s does not [hold][Proof.holds].
     *
     * @since 1.3.0
     */
    //TODO 1.3.0 check if the boolean flag is used somewhere. I think we used it in the context of Assertion to turn an
    // explanatoryAssertionGroup into a failing one. Now that we distinguish between Reportable and Proof this might be
    // obsolete. Yet, we should check that reporting does not suddenly suppress the error message in case someone forgot
    // to define expectations
    fun appendAsGroupIndicateIfOneCollected(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectT>
    ): Pair<Expect<SubjectT>, Boolean>

}

/** @since 1.3.0 */
fun <SubjectT> Expect<SubjectT>.toProofContainer(): ProofContainer<SubjectT> =
    when (this) {
        is ExpectInternal<SubjectT> -> this
        else -> throw UnsupportedOperationException("Unsupported Expect: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20Expect.toProofContainer")
    }

/** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.toExpect(): Expect<SubjectT> =
    when (this) {
        is ExpectInternal<SubjectT> -> this
        else -> throw UnsupportedOperationException("Unsupported ProofContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20ProofContainer.toExpect")
    }

/** @since 1.3.0 */
fun <T> Expect<T>.toExpectGrouping(): ExpectGrouping =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported AssertionContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%Expect.toExpectGrouping")
    }

/** @since 1.3.0 */
@Suppress("UNCHECKED_CAST") // safe to cast as long as Expect is the only subtype of ExpectGrouping
fun (ExpectGrouping.() -> Unit).toExpectationCreator(): Expect<*>.() -> Unit = this as Expect<*>.() -> Unit
