package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.SingleServiceLoader
import ch.tutteli.atrium.creating.AssertionPlant
import java.util.*

/**
 * The access point to an implementation of [ComparableAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val comparableAssertions by lazy { SingleServiceLoader.load(ComparableAssertions::class.java) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Comparable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ComparableAssertions {
    fun <T1 : Comparable<T2>, T2: Any?> isLessThan(plant: AssertionPlant<T1>, expected: T2): Assertion
    fun <T1 : Comparable<T2>, T2: Any?> isLessOrEquals(plant: AssertionPlant<T1>, expected: T2): Assertion
    fun <T1 : Comparable<T2>, T2: Any?> isGreaterThan(plant: AssertionPlant<T1>, expected: T2): Assertion
    fun <T1 : Comparable<T2>, T2: Any?> isGreaterOrEquals(plant: AssertionPlant<T1>, expected: T2): Assertion
}
