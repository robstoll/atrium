//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
package ch.tutteli.atrium.domain.builders.kotlin_1_3

import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.kotlin_1_3.creating.ResultAssertionsBuilder
import ch.tutteli.atrium.domain.kotlin_1_3.creating.ResultAssertions

/**
 * Returns [ResultAssertionsBuilder]
 * which inter alia delegates to the implementation of [ResultAssertions].
 */
@Deprecated("Use _logic from ch.tutteli.atrium.logic instead; will be removed with 1.0.0")
inline val ExpectImpl.result get() = ResultAssertionsBuilder
