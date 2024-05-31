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
 * Represents the extension point of the domain level for subjects of type [T].
 *
 * In contrast to expectation functions defined for [Expect] which usually return [Expect], functions defined for
 * [ProofContainer] (we call them proof functions) return [Proof] (or even more generally [Reportable])
 *
 * @param T The type of the subject of `this` expectation.
 */
//TODO remove AssertionContainer with Kotlin 2.0.0 at the latest
interface ProofContainer<T> : AssertionContainer<T> {
    /**
     * Either [Some] wrapping the subject of a [Proof] or [None] in case a previous subject transformation
     * could not be carried out.
     */
    override val maybeSubject: Option<T>

    /**
     * Do not use yet, this is experimental and might change or be removed without prior notice.
     */
    //TODO 1.3.0 maybe it would be better to have proofFactories as val like we have components?
    //TODO 1.3.0 I guess it would make sense to get rid of getImpl and only use the ComponentFactoryContainer approach
    // however, check if extensibility for a library author is still given. We don't want that a consumer of a third-party
    // expectation function collection-library needs to use an own expectation verb
    @ExperimentalNewExpectTypes
    override fun <I : Any> getImpl(kClass: KClass<I>, defaultFactory: () -> I): I

    /**
     * Do not use yet, this is experimental
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
     */
    fun append(proof: Proof): Expect<T>

    /**
     * Appends the [Proof]s the given [expectationCreator] creates to this container and
     * returns an [Expect] which includes them.
     *
     * Whether the returned [Expect] is the same as the initial one is up to the implementation (i.e. if a mutable
     * structure is used or an immutable). Atrium strives for an immutable data structure in the long run and will
     * little by little refactor the code accordingly.
     *
     * @param expectationCreator The lambda that will create expectations which means the lambda which will append
     *   [Proof]s to this container.
     * @param usageHintsOverloadWithoutExpectationCreator Reportables explaining what other overload (or expectation
     *   function) should have been used if one really doesn't want to create additional expectations. Whenever a user
     *   creates an [expectationCreator] then it is best practice to fail if no [Proof] was appended to a corresponding
     *   [ProofContainer].
     *   Provide an empty list in case you don't want that a usage hint is shown (or there is simply no alternative) in
     *   which case only the failing expectation is shown.
     *
     * @return an [Expect] for the subject of `this` expectation.
     *
     * @throws AssertionError Might throw an [AssertionError] in case the container evaluates [Proof]s immediately and
     *   one of the created [Proof]s does not [hold][Proof.holds].
     */
    fun appendAsGroupIndicateIfNoneCollected(
        expectationCreator: Expect<T>.() -> Unit,
        usageHintsOverloadWithoutExpectationCreator: List<Reportable>
    ): Pair<Expect<T>, Boolean>

}

fun <T> Expect<T>.toProofContainer(): ProofContainer<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported Expect: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20Expect.toProofContainer")
    }

fun <T> ProofContainer<T>.toExpect(): Expect<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported ProofContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20ProofContainer.toExpect")
    }

fun <T> ProofContainer<T>.toExpectGrouping(): ExpectGrouping =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported ProofContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20ProofContainer.toExpectGrouping")
    }

