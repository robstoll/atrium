@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectInternal
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
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
): Assertion = assertionBuilder.createDescriptive(description, representation) {
    maybeSubject.fold(trueProvider) { test(it) }
}

/**
 * Entry point to use the [SubjectChangerBuilder] based on this [AssertionContainer].
 */
val <T> AssertionContainer<T>.changeSubject: SubjectChangerBuilder.KindStep<T>
    get() = SubjectChangerBuilder.create(this.toExpect())

/**
 * Entry point to use the [FeatureExtractorBuilder] based on this [AssertionContainer].
 */
val <T> AssertionContainer<T>.extractFeature: FeatureExtractorBuilder.DescriptionStep<T>
    get() = FeatureExtractorBuilder.create(this.toExpect())

/**
 * Use this function if you want to make [Assertion]s about a feature or you perform a type transformation or any
 * other action which results in an assertion container being created and
 * you do not require this resulting container.
 *
 * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
 * assertion container.
 *
 * This basically delegates to [AssertionCollector.collect] using the subject of the assertion as `maybeSubject`.
 *
 * @param assertionCreator A lambda which defines the assertions for the feature.
 *
 * @return The collected assertions as an [AssertionGroup] with an [InvisibleAssertionGroupType].
 *
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
 *   assertion.
 */
inline fun <T> AssertionContainer<T>.collect(noinline assertionCreator: Expect<T>.() -> Unit): Assertion =
    assertionCollector.collect(maybeSubject, assertionCreator)

/**
 * Use this function if you want to collect [Assertion]s and use it as part of an [AssertionGroup].
 *
 * This basically delegates to [AssertionCollector.collectForComposition] using the subject of the assertion
 * as `maybeSubject`.
 *
 * @param assertionCreator A lambda which defines the assertions for the feature.
 *
 * @return The collected assertions as a `List<[Assertion]>`.
 *
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
 *   assertion.
 */
inline fun <T> collectForComposition(
    maybeSubject: Option<T>,
    noinline assertionCreator: Expect<T>.() -> Unit
): List<Assertion> = assertionCollector.collectForComposition(maybeSubject, assertionCreator)

/**
 * Casts this [Expect] to an [AssertionContainer] so that you have access to the functionality provided on the
 * logic level.
 */
fun <T> Expect<T>.toAssertionContainer(): AssertionContainer<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported Expect: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20Expect%20to%20toAssertionContainer")
    }

/**
 * Casts this [AssertionContainer] back to an [Expect] so that you can use it in places where an [Expect] is used.
 */
fun <T> AssertionContainer<T>.toExpect(): Expect<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported AssertionContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20AssertionContainer%20to%20Expect%")
    }
