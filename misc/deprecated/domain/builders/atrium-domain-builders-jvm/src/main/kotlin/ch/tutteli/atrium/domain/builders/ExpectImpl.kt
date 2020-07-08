//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
package ch.tutteli.atrium.domain.builders

import ch.tutteli.atrium.domain.builders.creating.*
import ch.tutteli.atrium.domain.creating.BigDecimalAssertions
import ch.tutteli.atrium.domain.creating.ChronoZonedDateTimeAssertions
import ch.tutteli.atrium.domain.creating.LocalDateAssertions
import ch.tutteli.atrium.domain.creating.OptionalAssertions
import ch.tutteli.atrium.domain.creating.PathAssertions

/**
 * Returns [BigDecimalAssertionsBuilder]
 * which inter alia delegates to the implementation of [BigDecimalAssertions].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
inline val ExpectImpl.bigDecimal get() = BigDecimalAssertionsBuilder

/**
 * Returns [OptionalAssertionsBuilder]
 * which inter alia delegates to the implementation of [OptionalAssertions].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
inline val ExpectImpl.optional get() = OptionalAssertionsBuilder

/**
 * Returns [PathAssertionsBuilder]
 * which inter alia delegates to the implementation of [PathAssertions].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
inline val ExpectImpl.path get() = PathAssertionsBuilder

/**
 * Returns [ChronoLocalDateAssertionsBuilder]
 * which inter alia delegates to the implementation of [ChronoLocalDateAssertions].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
inline val ExpectImpl.chronoLocalDate get() = ChronoLocalDateAssertionsBuilder

/**
 * Returns [ChronoLocalDateTimeAssertionsBuilder]
 * which inter alia delegates to the implementation of [ChronoLocalDateTimeAssertions].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
inline val ExpectImpl.chronoLocalDateTime get() = ChronoLocalDateTimeAssertionsBuilder

/**
 * Returns [ChronoZonedDateTimeAssertionsBuilder]
 * which inter alia delegates to the implementation of [ChronoZonedDateTimeAssertions].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
inline val ExpectImpl.chronoZonedDateTime get() = ChronoZonedDateTimeAssertionsBuilder

/**
 * Returns [LocalDateAssertionsBuilder]
 * which inter alia delegates to the implementation of [LocalDateAssertions].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
inline val ExpectImpl.localDate get() = LocalDateAssertionsBuilder

/**
 * Returns [LocalDateTimeAssertionsBuilder]
 * which inter alia delegates to the implementation of [LocalDateTimeAssertionsBuilder].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
inline val ExpectImpl.localDateTime get() = LocalDateTimeAssertionsBuilder

/**
 * Returns [ZonedDateTimeAssertionsBuilder]
 * which inter alia delegates to the implementation of [ZonedDateTimeAssertionsBuilder].
 */
@Deprecated("Use _logic instead; will be removed with 1.0.0")
inline val ExpectImpl.zonedDateTime get() = ZonedDateTimeAssertionsBuilder
