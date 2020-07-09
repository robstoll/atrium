package ch.tutteli.atrium.domain.robstoll.lib.assertions

import ch.tutteli.atrium.assertions.DescriptiveAssertion

/**
 * Represents a [DescriptiveAssertion] which is evaluated lazily where the lazy loading is not thread safe.
 *
 * @constructor Represents a [DescriptiveAssertion] which is evaluated lazily where the lazy loading is not thread safe.
 * @param assertionCreator The factory function which is used for lazy loading.
 */
@Deprecated("Will be removed with 1.0.0")
class LazyThreadUnsafeBasicAssertion(assertionCreator: () -> DescriptiveAssertion) : DescriptiveAssertion {
    private val basicAssertion by lazy(LazyThreadSafetyMode.NONE) {
        assertionCreator()
    }

    override val representation get() = basicAssertion.representation
    override val description get() = basicAssertion.description
    override fun holds() = basicAssertion.holds()
}
