package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectInternal
import ch.tutteli.atrium.domain.builders.creating.changers.FeatureExtractorBuilder
import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T> AssertionContainer<T>.createDescriptiveAssertion(
    description: Translatable,
    representation: Any?,
    test: (T) -> Boolean
): Assertion = assertionBuilder.createDescriptive(description, representation) {
    maybeSubject.fold(trueProvider) { test(it) }
}

val <T> AssertionContainer<T>.changeSubject: SubjectChangerBuilder.KindStep<T>
    get() = SubjectChangerBuilder.create(this.toExpect())

val <T> AssertionContainer<T>.extractFeature: FeatureExtractorBuilder.DescriptionStep<T>
    get() = FeatureExtractorBuilder.create(this.toExpect())

fun <T> AssertionContainer<T>.collect(assertionCreator: Expect<T>.() -> Unit): Assertion =
    assertionCollector.collect(maybeSubject, assertionCreator)


fun <T> Expect<T>.toAssertionContainer(): AssertionContainer<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported Expect: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20Expect%20to%20toAssertionContainer")
    }

fun <T> AssertionContainer<T>.toExpect(): Expect<T> =
    when (this) {
        is ExpectInternal<T> -> this
        else -> throw UnsupportedOperationException("Unsupported AssertionContainer: $this -- Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20AssertionContainer%20to%20Expect%")
    }
