package ch.tutteli.atrium.assertions

/**
 * Represents an [DescriptiveAssertion] which is evaluated lazily where the lazy loading is not thread safe.
 *
 * @constructor Represents an [DescriptiveAssertion] which is evaluated lazily where the lazy loading is not thread safe.
 * @param assertionCreator The factory function which is used for lazy loading.
 */
class LazyThreadUnsafeBasicAssertion(assertionCreator: () -> DescriptiveAssertion) : DescriptiveAssertion {
    private val basicAssertion by lazy(LazyThreadSafetyMode.NONE) {
        assertionCreator()
    }

    override val expected get() = basicAssertion.expected
    override val description get() = basicAssertion.description
    override fun holds() = basicAssertion.holds()
}
