package ch.tutteli.atrium.domain.robstoll.lib.assertions

import ch.tutteli.atrium.assertions.AssertionGroup

/**
 * Represents an [AssertionGroup] which is evaluated lazily where the lazy loading is not thread safe.
 *
 * @constructor Represents an [AssertionGroup] which is evaluated lazily where the lazy loading is not thread safe.
 * @param assertionCreator The factory function which is used for lazy loading.
 */
//TODO 0.13.0 deprecate
//@Deprecated("Use LazyThreadUnsafeAssertionGroup from atrium-logic; will be removed with 0.14.0")
class LazyThreadUnsafeAssertionGroup(assertionCreator: () -> AssertionGroup) : AssertionGroup {
    private val assertionGroup by lazy(LazyThreadSafetyMode.NONE) {
        assertionCreator()
    }

    override val description get() = assertionGroup.description
    override val type get() = assertionGroup.type
    override val representation get() = assertionGroup.representation
    override val assertions get() = assertionGroup.assertions
    override fun holds() = assertionGroup.holds()
}
