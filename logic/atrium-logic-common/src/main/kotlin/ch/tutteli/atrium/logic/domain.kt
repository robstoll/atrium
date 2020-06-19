package ch.tutteli.atrium.logic

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.impl.ExpectInternal
import ch.tutteli.atrium.reporting.BUG_REPORT_URL

@ExperimentalNewExpectTypes
inline fun <T, R> Expect<T>._domain(provider: AssertionContainer<T>.() -> R): R =
    when (this) {
        is ExpectInternal<T> -> this.provider()
        else -> throw UnsupportedOperationException("Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20_domain")
    }