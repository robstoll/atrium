//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.assertions.impl

import ch.tutteli.atrium.assertions.AssertionGroup

/**
 * Represents an [AssertionGroup] which is evaluated lazily where the lazy loading is not thread safe.
 *
 * @constructor Represents an [AssertionGroup] which is evaluated lazily where the lazy loading is not thread safe.
 * @param assertionCreator The factory function which is used for lazy loading.
 */
//TODO 1.3.0 is there a better replacement?
@Deprecated(
    "switch to ProofGroup, will be removed with 2.0.0 at the latest",
    ReplaceWith("ProofGroup", "ch.tutteli.atrium.creating.proofs.ProofGroup")
)
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
