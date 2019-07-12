@file:JvmMultifileClass
@file:JvmName("ListAssertionsKt")
package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.translations.DescriptionListAssertion
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

fun <E, T : List<E>> _get(
    assertionContainer: Expect<T>,
    index: Int
): Expect<E> = get(assertionContainer, index, null)

fun <E, T : List<E>> _get(
    assertionContainer: Expect<T>,
    index: Int,
    assertionCreator: Expect<E>.() -> Unit
): Assertion = ExpectImpl.collector.collect(assertionContainer) {
    get(this, index, assertionCreator)
}

private fun <E, T : List<E>> get(
    assertionContainer: Expect<T>,
    index: Int,
    assertionCreator: (Expect<E>.() -> Unit)?
): Expect<E> = ExpectImpl.feature.extractor(assertionContainer)
    .methodCall("get", index)
    .withRepresentationForFailure(DescriptionListAssertion.INDEX_OUT_OF_BOUNDS)
    .withCheck { index < it.size }
    .withFeatureExtraction { it[index] }
    .maybeWithSubAssertions(assertionCreator)
    .build()
