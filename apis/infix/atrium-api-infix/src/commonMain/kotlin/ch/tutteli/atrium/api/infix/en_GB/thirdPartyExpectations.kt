//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.api.infix.en_GB.creating.KeyWithCreator
import ch.tutteli.atrium.api.infix.en_GB.creating.thirdparty.DescriptionRepresentationWithExecutor
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.toHoldThirdPartyExpectation

/**
 * Expects that the [DescriptionRepresentationWithExecutor.expectationExecutor] doesn't throw and creates an assertion
 * representing this third party expectation with the given [DescriptionRepresentationWithExecutor.description] and
 * [DescriptionRepresentationWithExecutor.representation].
 *
 * @param thirdPartyExpectation The parameter object used for this function, use
 *   `thirdPartyExpectation(description, representation) { ... }` to create a DescriptionRepresentationWithExecutor
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ThirdPartyExpectationSamples.toHoldThirdPartyExpectation
 *
 * @since 1.2.0
 */
infix fun <T> Expect<T>.toHold(
    thirdPartyExpectation: DescriptionRepresentationWithExecutor<T>
): Expect<T> = _coreAppend { toHoldThirdPartyExpectation(
        thirdPartyExpectation.description,
        thirdPartyExpectation.representation,
        thirdPartyExpectation.expectationExecutor
    ) }

/**
 * Helper function to create a [DescriptionRepresentationWithExecutor] based on the given [description] and [representation] and [expectationExecutor].
 *
 * @param description The description of the third party expectation.
 * @param representation The representation of the third party expectation.
 * @param expectationExecutor The lambda which executes the third party expectation where the current subject is
 *   passed as parameter.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ThirdPartyExpectationSamples.toHoldThirdPartyExpectation
 *
 * @since 1.2.0
 */
fun <T> thirdPartyExpectation(
    description: String,
    representation: Any?,
    expectationExecutor: (T) -> Unit
) = DescriptionRepresentationWithExecutor(description, representation, expectationExecutor)
