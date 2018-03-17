package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType

/**
 * Base interface for all [AssertionGroup] builder which want to provide different overloads for [create] methods.
 */
interface AssertionGroupBuilder<out T: AssertionGroupType> {

    /**
     * The [AssertionGroupType] which shall be used for the [AssertionGroup].
     */
    val groupType: T

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertion] as single [AssertionGroup.assertions].
     */
    fun create(assertion: Assertion): AssertionGroup
        = create(listOf(assertion))

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertion1] and [assertion2] as [AssertionGroup.assertions].
     */
    fun create(assertion1: Assertion, assertion2: Assertion): AssertionGroup
        = create(listOf(assertion1, assertion2))

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertions] as [AssertionGroup.assertions].
     */
    fun create(assertions: List<Assertion>): AssertionGroup
}
