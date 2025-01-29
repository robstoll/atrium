package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.Reportable


/**
 * Represents an [ExpectationCreator] with usage hints which  are shown in case no expectation is created within the
 * lambda.
 *
 * @property usageHintsOverloadWithoutExpectationCreator  Reportables explaining what other overload (or other
 *   expectation function) should have been used if one really doesn't want to create additional expectations.
 *
 *   Whenever a user creates an [expectationCreator] then it is best practice to fail if no expectation was created (to
 *   be more precise, if no [Proof] was appended to a corresponding [ProofContainer]).
 *   Provide an empty list in case you don't want that a usage hint is shown (or there is simply no alternative) in
 *   which case only the explanation that no expectation was created will be shown.
 * @property expectationCreator The lambda that will create expectations which means the lambda which will append
 *   [Proof]s to the receiving [Expect].
 *
 * @since 1.3.0
 */
data class ExpectationCreatorWithUsageHints<SubjectT>(
    val usageHintsOverloadWithoutExpectationCreator: List<Reportable>,
    val expectationCreator: Expect<SubjectT>.() -> Unit,
)
