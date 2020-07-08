//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.builders.kotlin_1_3

import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.kotlin_1_3.creating.ResultAssertionsBuilder
import ch.tutteli.atrium.domain.kotlin_1_3.creating.ResultAssertions

/**
 * Returns [ResultAssertionsBuilder]
 * which inter alia delegates to the implementation of [ResultAssertions].
 */
inline val ExpectImpl.result get() = ResultAssertionsBuilder
