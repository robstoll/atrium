package ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3.creating

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionResultAssertion

fun <E, T : Result<E>> _isSuccess(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, E> =
    ExpectImpl.feature.extractor(assertionContainer)
        .withDescription(DescriptionResultAssertion.VALUE)
        .withRepresentationForFailure(DescriptionResultAssertion.IS_NOT_SUCCESS)
        .withFeatureExtraction {
            Option.someIf(it.isSuccess) { it.getOrThrow() }
        }
        .withoutOptions()
        .build()

