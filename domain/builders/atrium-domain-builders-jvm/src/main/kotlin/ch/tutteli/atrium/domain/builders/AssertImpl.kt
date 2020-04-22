// TODO remove file with 1.0.0
@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE", "DEPRECATION")

package ch.tutteli.atrium.domain.builders

import ch.tutteli.atrium.domain.builders.creating.BigDecimalAssertionsBuilder

/**
 * Bundles different domain objects which are defined by the module atrium-domain-api
 * to give users of Atrium a fluent API for the domain as well.
 */
inline val AssertImpl.bigDecimal get() = BigDecimalAssertionsBuilder
