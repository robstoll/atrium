package ch.tutteli.atrium.domain.builders

import ch.tutteli.atrium.domain.builders.creating.BigDecimalAssertionsBuilder
import ch.tutteli.atrium.domain.creating.BigDecimalAssertions

/**
 * Returns [BigDecimalAssertionsBuilder]
 * which inter alia delegates to the implementation of [BigDecimalAssertions].
 */
inline val ExpectImpl.bigDecimal get() = BigDecimalAssertionsBuilder
