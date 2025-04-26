@file:Suppress("ObjectPropertyName", "FunctionName") // because we want to use _core and co.
package ch.tutteli.atrium

import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.forgotToAppendProofPseudoUsageHint
import ch.tutteli.atrium.reporting.reportables.Reportable

/**
 * Appends the [Proof] the given [proofCreator] creates based on this [Expect].
 *
 * Use [_core] for more sophisticated scenarios, like feature extraction.
 *
 * @since 1.3.0
 */
inline fun <T> Expect<T>._coreAppend(crossinline proofCreator: ProofContainer<T>.() -> Proof): Expect<T> =
    _core.appendAsGroupIndicateIfOneCollected(ExpectationCreatorWithUsageHints(forgotToAppendProofPseudoUsageHint) {
        _core.run { append(proofCreator()) }
    }).first


/**
 * Entry point to the core level of Atrium -- which is one level deeper than the API.
 *
 * On the core level we don't operate on [Expect] and create expectations but on [ProofContainer] and create [Proof]s
 * or even more general [Reportable]s.
 *
 * Use [_coreAppend] in case you want to create and append a [Proof] to this [Expect].
 *
 * @since 1.3.0
 */
inline val <T> Expect<T>._core: ProofContainer<T>
    get() = this.toProofContainer()

inline fun ExpectGrouping._coreAppend(crossinline proofCreator: ProofContainer<*>.() -> Proof): ExpectGrouping {
    // unchecked cast necessary, so that we can call appendAsGroupIndicateIfOneCollected
    // inside we use proofCreator which is still based on ProofContainer<*> so we are still type safe
    @Suppress("UNCHECKED_CAST")
    return (_core as ProofContainer<Any>).appendAsGroupIndicateIfOneCollected(ExpectationCreatorWithUsageHints(
        forgotToAppendProofPseudoUsageHint
    ) {
        //TODO 2.0.0 remove types once we use K2
        _core.run<ProofContainer<Any>, Unit> { append(proofCreator()) }
    }).first.toExpectGrouping()
}

/**
 * Turns this [ExpectGrouping] into an [AssertionContainer] without known subject type.
 *
 * @since 1.1.0
 */

inline val ExpectGrouping._core: ProofContainer<*>
    get() = when (this) {
        is ExpectInternal<*> -> this
        else -> throw UnsupportedOperationException("Unsupported Expect: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20ExpectGrouping.toAssertionContainer")
    }
