@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionOptionalAssertion
import java.util.*

fun <T : Optional<*>> _isEmpty(expect: Expect<T>): Assertion =
    ExpectImpl.builder.createDescriptive(expect, IS, RawString.create(DescriptionOptionalAssertion.EMPTY)) { !it.isPresent }

fun <E, T : Optional<E>> _isPresent(expect: Expect<T>): ExtractedFeaturePostStep<T, E> =
    ExpectImpl.feature.extractor(expect)
        .withDescription(DescriptionOptionalAssertion.GET)
        .withRepresentationForFailure(DescriptionOptionalAssertion.IS_NOT_PRESENT)
        .withFeatureExtraction {
            Option.someIf(it.isPresent) { it.get() }
        }
        .withoutOptions()
        .build()
