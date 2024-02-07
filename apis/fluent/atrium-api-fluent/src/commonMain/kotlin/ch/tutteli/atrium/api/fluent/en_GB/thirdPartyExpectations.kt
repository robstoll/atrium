package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.toHoldThirdPartyExpectation

/**
 * Expects that the [expectationExecutor] doesn't throw and creates an assertion representing this third party
 * expectation with the given [description] and [representation].
 *
 * @param description The description of the third party expectation.
 * @param representation The representation of the third party expectation.
 * @param expectationExecutor The lambda which executes the third party expectation where the current subject is
 *   passed as parameter.
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ThirdPartyExpectationSamples.toHoldThirdPartyExpectation
 *
 * @since 1.2.0
 */
fun <T> Expect<T>.toHoldThirdPartyExpectation(
    description: String,
    representation: Any?,
    expectationExecutor: (T) -> Unit
): Expect<T> = _logicAppend { toHoldThirdPartyExpectation(description, representation, expectationExecutor) }
