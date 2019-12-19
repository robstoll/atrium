@file:JvmMultifileClass
@file:JvmName("ListAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionListAssertion
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

fun <E, T : List<E>> _get(assertionContainer: Expect<T>, index: Int): ExtractedFeaturePostStep<T, E> =
    assertionContainer._domain.featureExtractor
        .methodCall("get", index)
        .withRepresentationForFailure(DescriptionListAssertion.INDEX_OUT_OF_BOUNDS)
        .withFeatureExtraction {
            Option.someIf(index < it.size) { it[index] }
        }
        .withoutOptions()
        .build()
