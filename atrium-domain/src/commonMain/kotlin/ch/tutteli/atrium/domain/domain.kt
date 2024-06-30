@file:Suppress("ObjectPropertyName", "FunctionName") // because we want to use _domain and co.
package ch.tutteli.atrium.domain

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.toProofContainer
import ch.tutteli.atrium.reporting.reportables.Reportable

/**
 * Appends the [Proof] the given [proofCreator] creates based on this [Expect].
 *
 * Use [_domain] for more sophisticated scenarios, like feature extraction.
 */
inline fun <T> Expect<T>._domainAppend(proofCreator: ProofContainer<T>.() -> Proof): Expect<T> =
    //TODO 1.3.0 include try-catch and report unexpected exceptions
    _domain.run { append(proofCreator()) }

/**
 * Entry point to the domain level of Atrium -- which is one level deeper than the API.
 *
 * On the domain level we don't operate on [Expect] and create expectations but on [ProofContainer] and create [Proof]s
 * or even more general [Reportable]s.
 *
 * Use [_domainAppend] in case you want to create and append a [Proof] to this [Expect].
 */
inline val <T> Expect<T>._domain: ProofContainer<T>
    get() = this.toProofContainer()
