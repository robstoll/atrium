package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider

/**
 * The access point to an implementation of [FloatingPointAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val floatingPointAssertions by lazy { loadSingleService(FloatingPointAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to floating points,
 * which an implementation of the domain of Atrium has to provide.
 *
 * An `actual` or in other words platform specific interface might add further methods.
 */
expect interface FloatingPointAssertions: FloatingPointAssertionsCommon

/**
 * Defines the minimum set of assertion functions and builders applicable to floating points ([Float], [Double]),
 * which an implementation of the domain of Atrium has to provide for any platform.
 */
interface FloatingPointAssertionsCommon {
    fun toBeWithErrorTolerance(subjectProvider: SubjectProvider<Float>, expected: Float, tolerance: Float): Assertion
    fun toBeWithErrorTolerance(subjectProvider: SubjectProvider<Double>, expected: Double, tolerance: Double): Assertion
}
