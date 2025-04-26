//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectInternal
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.Text

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
@Deprecated("Switch to ProofContainer and use buildSimpleProof, AssertionContainer will be removed with 2.0.0 at the latest")
@Suppress("DEPRECATION")
fun <T> AssertionContainer<T>.createDescriptiveAssertion(
    description: ch.tutteli.atrium.reporting.translating.Translatable,
    representation: Any?,
    test: (T) -> Boolean
): Assertion = assertionBuilder.descriptive
    .withTest(this.toExpect(), test)
    .withDescriptionAndRepresentation(description, representation)
    .build()

/**
 * Entry point to use the [SubjectChangerBuilder] based on this [AssertionContainer].
 */
@Deprecated(
    "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("this.changeSubject", "ch.tutteli.atrium.creating.changeSubject")
)
val <T> AssertionContainer<T>.changeSubject: SubjectChangerBuilder.KindStep<T>
    get() = SubjectChangerBuilder(this)

/**
 * Entry point to use the [FeatureExtractorBuilder] based on this [AssertionContainer].
 */
//TODO deprecate with 1.3.0 (when ProofContainer is introduced)
val <T> AssertionContainer<T>.extractFeature: FeatureExtractorBuilder.DescriptionStep<T>
    get() = FeatureExtractorBuilder(this)


/**
 * Casts this [Expect] to an [AssertionContainer] so that you have access to the functionality provided on the
 * logic level.
 */
//is not internal as it is used by extensions, however it is not made visible via module-info.java
@Deprecated(
    "Switch to ProofContainer, will be removed with 2.0.0 at the latest",
    ReplaceWith("toProofContainer()", "ch.tutteli.atrium.creating.toProofContainer")
)
fun <T> Expect<T>.toAssertionContainer(): AssertionContainer<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported Expect: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20Expect.toAssertionContainer")
    }

/**
 * Casts this [AssertionContainer] back to an [Expect] so that you can use it in places where an [Expect] is used.
 */
@Deprecated("Switch to ProofContainer, AssertionContainer will be removed with 2.0.0 at the latest")
fun <T> AssertionContainer<T>.toExpect(): Expect<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported AssertionContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20AssertionContainer.toExpect")
    }

/**
 * Casts this [Expect] back to an [ExpectGrouping] so that you can use it in places where an [ExpectGrouping] is used.
 */
@Deprecated(
    "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("this.toExpectGrouping()", "ch.tutteli.atrium.creating.toExpectGrouping")
)
fun <T> Expect<T>.toExpectGrouping(): ExpectGrouping =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported AssertionContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%Expect.toExpectGrouping")
    }

@Deprecated(
    "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("this.toExpectationCreator()", "ch.tutteli.atrium.creating.toExpectationCreator")
)
@Suppress("UNCHECKED_CAST") // safe to cast as long as Expect is the only subtype of ExpectGrouping
fun (ExpectGrouping.() -> Unit).toAssertionCreator(): Expect<*>.() -> Unit = this as Expect<*>.() -> Unit
