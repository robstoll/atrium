@file:JvmMultifileClass
@file:JvmName("ListAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionListAssertion
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

fun <E, T : List<E>> _get(assertionContainer: Expect<T>, index: Int): ExtractedFeaturePostStep<T, E> =
    ExpectImpl.feature.extractor(assertionContainer)
        .methodCall("get", index)
        .withRepresentationForFailure(DescriptionListAssertion.INDEX_OUT_OF_BOUNDS)
        .withCheck { index < it.size }
        .withFeatureExtraction { it[index] }
        .build()
