//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
actual fun createAdditionalHints(
    throwable: Throwable,
    maxStackTrace: Int
): List<Assertion> = throwable.suppressed.map { suppressed ->
    ThrowableThrownFailureHandler.createChildHint(
        throwable,
        suppressed,
        DescriptionThrowableAssertion.OCCURRED_EXCEPTION_SUPPRESSED,
        maxStackTrace
    )
}
