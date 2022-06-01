@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectInternal
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Creates a [DescriptiveAssertion] based on the given [description], [representation] and [test].
 *
 * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
 * then wrap it into a [Text] and pass it instead.
 *
 * @param description The description of the assertion, e.g. `to Be`
 * @param representation The representation of the expected outcome
 * @param test The test which checks whether the assertion holds
 */
//TODO deprecate with 0.19.0 (when ProofContainer is introduced)
fun <T> AssertionContainer<T>.createDescriptiveAssertion(
    description: Translatable,
    representation: Any?,
    test: (T) -> Boolean
): Assertion = assertionBuilder.descriptive
    .withTest(this.toExpect(), test)
    .withDescriptionAndRepresentation(description, representation)
    .build()

/**
 * Entry point to use the [SubjectChangerBuilder] based on this [AssertionContainer].
 */
//TODO deprecate with 0.19.0 (when ProofContainer is introduced)
val <T> AssertionContainer<T>.changeSubject: SubjectChangerBuilder.KindStep<T>
    get() = SubjectChangerBuilder(this)

/**
 * Entry point to use the [FeatureExtractorBuilder] based on this [AssertionContainer].
 */
//TODO deprecate with 0.19.0 (when ProofContainer is introduced)
val <T> AssertionContainer<T>.extractFeature: FeatureExtractorBuilder.DescriptionStep<T>
    get() = FeatureExtractorBuilder(this)


/**
 * Casts this [Expect] to an [AssertionContainer] so that you have access to the functionality provided on the
 * logic level.
 */
//is not internal as it is used by extensions, however it is not made visible via module-info.java
//TODO deprecate with 0.19.0 and move toProofContainer to core
fun <T> Expect<T>.toAssertionContainer(): AssertionContainer<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported Expect: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20Expect.toAssertionContainer")
    }

/**
 * Casts this [AssertionContainer] back to an [Expect] so that you can use it in places where an [Expect] is used.
 */
//TODO deprecate with 0.19.0 and move ProofContainer.toExpect to core
fun <T> AssertionContainer<T>.toExpect(): Expect<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported AssertionContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20AssertionContainer.toExpect")
    }

