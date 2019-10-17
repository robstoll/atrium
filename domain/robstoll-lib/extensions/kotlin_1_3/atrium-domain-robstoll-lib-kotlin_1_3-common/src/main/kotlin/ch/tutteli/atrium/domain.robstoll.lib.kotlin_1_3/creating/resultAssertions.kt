@file:JvmMultifileClass
@file:JvmName("ResultAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionResultAssertion
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName


fun  <E, T : Result<E>>_isSuccess( assertionContainer: Expect<T>) : ExtractedFeaturePostStep<T, E> =
    ExpectImpl.feature.extractor(assertionContainer)
        .methodCall("isSuccess")
        .withRepresentationForFailure(DescriptionResultAssertion.COULD_NOT_INTERPRET_INPUT)
        .withCheck { it.getOrNull() != null }
        .withFeatureExtraction { it.getOrNull() }
        .build()

