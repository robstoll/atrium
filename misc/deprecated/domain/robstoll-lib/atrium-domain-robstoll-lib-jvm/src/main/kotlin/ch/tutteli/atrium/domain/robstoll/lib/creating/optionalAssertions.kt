//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)
package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionOptionalAssertion
import java.util.*

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : Optional<*>> _isEmpty(expect: Expect<T>): Assertion =
    assertionBuilder.createDescriptive(expect, IS, DescriptionOptionalAssertion.EMPTY) { !it.isPresent }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <E, T : Optional<E>> _isPresent(expect: Expect<T>): ExtractedFeaturePostStep<T, E> =
    ExpectImpl.feature.extractor(expect)
        .withDescription(DescriptionOptionalAssertion.GET)
        .withRepresentationForFailure(DescriptionOptionalAssertion.IS_NOT_PRESENT)
        .withFeatureExtraction {
            Option.someIf(it.isPresent) { it.get() }
        }
        .withoutOptions()
        .build()
