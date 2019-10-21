package ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionResultAssertion

fun  <E, T : Result<E>>_isSuccess( assertionContainer: Expect<T>) : ExtractedFeaturePostStep<T, E> =
    ExpectImpl.feature.extractor(assertionContainer)
        .withDescription(DescriptionResultAssertion.UNBOXED)
        .withRepresentationForFailure(DescriptionResultAssertion.IS_NOT_SUCCESS)
        .withCheck { it.isSuccess }
        .withFeatureExtraction { it.getOrNull() ?: IllegalStateException("checked it.isSuccess and suddenly it isn't any more: $it")}
        .build()

