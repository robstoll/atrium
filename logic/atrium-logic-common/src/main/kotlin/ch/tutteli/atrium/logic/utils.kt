@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectInternal
import ch.tutteli.atrium.logic.creating.collectors.assertionCollector
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.creating.transformers.TransformationExecutionStep
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
fun <T> AssertionContainer<T>.createDescriptiveAssertion(
    description: Translatable,
    representation: Any?,
    test: (T) -> Boolean
): Assertion = assertionBuilder.descriptive
    .withTest(this, test)
    .withDescriptionAndRepresentation(description, representation)
    .build()

/**
 * Entry point to use the [SubjectChangerBuilder] based on this [AssertionContainer].
 */
val <T> AssertionContainer<T>.changeSubject: SubjectChangerBuilder.KindStep<T>
    get() = SubjectChangerBuilder(this)

/**
 * Entry point to use the [FeatureExtractorBuilder] based on this [AssertionContainer].
 */
val <T> AssertionContainer<T>.extractFeature: FeatureExtractorBuilder.DescriptionStep<T>
    get() = FeatureExtractorBuilder(this)

/**
 * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
 * other action which results in an [Expect] being created for a different subject and
 * you do not require this resulting [Expect].
 *
 * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
 * [Expect].
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
 *
 * It uses the [AssertionContainer.maybeSubject] as subject of the given [assertionCreator] and
 * delegates to [collectForDifferentSubject].
 *
 * @param assertionCreator A lambda which defines the expectations for the [AssertionContainer.maybeSubject].
 *
 * @return The collected assertions.
 */
inline fun <T> AssertionContainer<T>.collect(noinline assertionCreator: Expect<T>.() -> Unit): Assertion =
    collectForDifferentSubject(maybeSubject, assertionCreator)

/**
 * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
 * other action which results in an [Expect] being created for a different subject and
 * you do not require this resulting [Expect].
 *
 * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
 * [Expect].
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
 *
 * This basically delegates to [assertionCollector] using the subject of `this` expectation as `maybeSubject`.
 *
 * @param maybeSubject Either [Some] wrapping the subject of the current assertion or
 *   [None] in case a previous subject transformation was not successful -
 *   this will be used as subject for the given [assertionCreator].
 * @param assertionCreator A lambda which defines the expectations for the given [maybeSubject].
 *
 * @return The collected assertions.
 */
inline fun <T> AssertionContainer<*>.collectForDifferentSubject(
    maybeSubject: Option<T>,
    noinline assertionCreator: Expect<T>.() -> Unit
): Assertion =
    assertionCollector.collect(maybeSubject, assertionCreator)

/**
 * Use this function if you want to collect [Assertion]s and use it as part of another [Assertion] (e.g. as part
 * of an [AssertionGroup]).
 *
 * Note that an assertion will be added which fails in case [assertionCreator] does not create a single assertion.
 *
 * This basically delegates to [assertionCollector] using [AssertionContainer.maybeSubject] as subject of `this` expectation.
 *
 * @param assertionCreator A lambda which defines the expectations for the [AssertionContainer.maybeSubject].
 *
 * @return The collected assertions as a `List<[Assertion]>`.
 *
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
 *   assertion.
 */
inline fun <T> AssertionContainer<T>.collectForComposition(
    noinline assertionCreator: Expect<T>.() -> Unit
): List<Assertion> = assertionCollector.collectForComposition(maybeSubject, assertionCreator)

/**
 * Casts this [Expect] to an [AssertionContainer] so that you have access to the functionality provided on the
 * logic level.
 */
//is not internal as it is used by extensions, however it is not made visible via module-info.java
fun <T> Expect<T>.toAssertionContainer(): AssertionContainer<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported Expect: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20Expect.toAssertionContainer")
    }

/**
 * Casts this [AssertionContainer] back to an [Expect] so that you can use it in places where an [Expect] is used.
 */
fun <T> AssertionContainer<T>.toExpect(): Expect<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported AssertionContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20AssertionContainer.toExpect")
    }

/**
 * Finishes the transformation process by appending the [Assertion]
 * which is returned when calling [TransformationExecutionStep.collectAndAppend] with [_logicAppend]
 * and the given [assertionCreator].
 *
 * See [collectForDifferentSubject] for more information.
 *
 * @return an [Expect] for the subject of this expectation.
 */
fun <T, R> TransformationExecutionStep<T, R, *>.collectAndLogicAppend(assertionCreator: AssertionContainer<R>.() -> Assertion): Expect<T> =
    collectAndAppend { _logicAppend(assertionCreator) }
