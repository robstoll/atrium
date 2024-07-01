@file:Suppress("ObjectPropertyName", "FunctionName") // because we want to use _core and co.
package ch.tutteli.atrium

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.toProofContainer
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Reportable

/**
 * Appends the [Proof] the given [proofCreator] creates based on this [Expect].
 *
 * Use [_core] for more sophisticated scenarios, like feature extraction.
 */
inline fun <T> Expect<T>._coreAppend(crossinline proofCreator: ProofContainer<T>.() -> Proof): Expect<T> =
    _core.appendAsGroupIndicateIfOneCollected(ExpectationCreatorWithUsageHints(
        listOf(
            Text("bug detected, looks like we forgot to append a proof, please open a bug at $BUG_REPORT_URL")
        )
    ) {
        _core.run { append(proofCreator()) }
    }).first


/**
 * Entry point to the core level of Atrium -- which is one level deeper than the API.
 *
 * On the core level we don't operate on [Expect] and create expectations but on [ProofContainer] and create [Proof]s
 * or even more general [Reportable]s.
 *
 * Use [_coreAppend] in case you want to create and append a [Proof] to this [Expect].
 */
inline val <T> Expect<T>._core: ProofContainer<T>
    get() = this.toProofContainer()
