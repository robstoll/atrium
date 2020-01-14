package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents a general interface which merely defines that assertions can be added to this type via the [addAssertion]
 * method.
 *
 * Notice, this interface has its mere purpose to facilitate the transition from [Assert] to [Expect].
 * It might well be that we are going to remove it with 0.10.0 without previous notice.
 * Hence, to be on the safe side, you should use [Expect] instead.
 */
interface AssertionHolder {
    /**
     * Adds the given [assertion] to this holder.
     *
     * @param assertion The assertion which will be added to this holder.
     *
     * @return This holder to support a fluent API.
     * @throws AssertionError Might throw an [AssertionError] in case the [Assertion] does not hold.
     */
    fun addAssertion(assertion: Assertion): AssertionHolder
}
