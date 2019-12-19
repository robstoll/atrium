@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion

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
