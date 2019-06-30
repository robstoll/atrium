package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.*
import kotlin.reflect.KClass

/**
 * The access point to an implementation of [AnyAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val anyAssertions by lazy { loadSingleService(AnyAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Any] type,
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyAssertions {

    fun <T : Any> toBe(assertionContainer: Expect<T>, expected: T): Assertion

    fun <T : Any> toBe(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T> notToBe(subjectProvider: SubjectProvider<T>, expected: T): Assertion
    fun <T> isSame(subjectProvider: SubjectProvider<T>, expected: T): Assertion
    fun <T> isNotSame(subjectProvider: SubjectProvider<T>, expected: T): Assertion

    fun <T : Any?> toBeNull(subjectProvider: SubjectProvider<T>): Assertion

    fun <T : Any> notToBeNull(
        assertionContainer: Expect<T?>,
        type: KClass<T>,
        assertionCreator: Expect<T>.() -> Unit
    ): Assertion

    fun <T : Any> toBeNullable(
        assertionContainer: Expect<T?>,
        type: KClass<T>,
        expectedOrNull: T?
    ): Assertion

    fun <T : Any> toBeNullIfNullGivenElse(
        assertionContainer: Expect<T?>,
        type: KClass<T>,
        assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ): Assertion



    fun <T : Any> isNullable(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        expectedOrNull: T?
    ): Assertion

    fun <T : Any> isNotNull(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        assertionCreator: AssertionPlant<T>.() -> Unit
    ): Assertion

    fun <T : Any> isNotNullBut(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        expected: T
    ): Assertion

    fun <T : Any> isNullIfNullGivenElse(
        plant: AssertionPlantNullable<T?>,
        type: KClass<T>,
        assertionCreatorOrNull: (AssertionPlant<T>.() -> Unit)?
    ): Assertion
}

