package ch.tutteli.atrium.domain.builders

import ch.tutteli.atrium.domain.builders.creating.BigDecimalAssertionsBuilder
import ch.tutteli.atrium.domain.builders.creating.OptionalAssertionsBuilder
import ch.tutteli.atrium.domain.builders.creating.PathAssertionsBuilder
import ch.tutteli.atrium.domain.creating.BigDecimalAssertions
import ch.tutteli.atrium.domain.creating.OptionalAssertions
import ch.tutteli.atrium.domain.creating.PathAssertions

/**
 * Returns [BigDecimalAssertionsBuilder]
 * which inter alia delegates to the implementation of [BigDecimalAssertions].
 */
inline val ExpectImpl.bigDecimal get() = BigDecimalAssertionsBuilder

/**
 * Returns [OptionalAssertionsBuilder]
 * which inter alia delegates to the implementation of [OptionalAssertions].
 */
inline val ExpectImpl.optional get() = OptionalAssertionsBuilder

/**
 * Returns [PathAssertionsBuilder]
 * which inter alia delegates to the implementation of [PathAssertions].
 */
inline val ExpectImpl.path get() = PathAssertionsBuilder
