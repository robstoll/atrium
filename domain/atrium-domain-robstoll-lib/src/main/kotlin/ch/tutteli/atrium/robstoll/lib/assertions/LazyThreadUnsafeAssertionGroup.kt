package ch.tutteli.atrium.robstoll.lib.assertions

import ch.tutteli.atrium.assertions.AssertionGroup

/**
 * Represents an [AssertionGroup] which is evaluated lazily where the lazy loading is not thread safe.
 *
 * @constructor Represents an [AssertionGroup] which is evaluated lazily where the lazy loading is not thread safe.
 * @param assertionCreator The factory function which is used for lazy loading.
 */
class LazyThreadUnsafeAssertionGroup(assertionCreator: () -> AssertionGroup) : AssertionGroup {
    private val basicAssertion by lazy(LazyThreadSafetyMode.NONE) {
        assertionCreator()
    }

    override val name get() = basicAssertion.name
    override val type get() = basicAssertion.type
    override val subject get() = basicAssertion.subject
    override val assertions get() = basicAssertion.assertions
    override fun holds() = basicAssertion.holds()
}
