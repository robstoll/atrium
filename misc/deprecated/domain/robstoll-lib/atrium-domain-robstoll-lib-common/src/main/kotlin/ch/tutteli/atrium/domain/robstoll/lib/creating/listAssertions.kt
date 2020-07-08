@file:JvmMultifileClass
@file:JvmName("ListAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionListAssertion
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <E, T : List<E>> _get(expect: Expect<T>, index: Int): ExtractedFeaturePostStep<T, E> =
    ExpectImpl.feature.extractor(expect)
        .methodCall("get", index)
        .withRepresentationForFailure(DescriptionListAssertion.INDEX_OUT_OF_BOUNDS)
        .withFeatureExtraction {
            Option.someIf(index < it.size) { it[index] }
        }
        .withoutOptions()
        .build()
