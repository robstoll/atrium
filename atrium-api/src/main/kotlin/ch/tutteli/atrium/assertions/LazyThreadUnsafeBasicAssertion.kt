package ch.tutteli.atrium.assertions

/**
 * Represents an assertion which is evaluated lazily where the lazy loading is not thread safe.
 *
 * @property assertionCreator The factory function which is used for lazy loading.
 *
 * @constructor Represents an assertion which is evaluated lazily where the lazy loading is not thread safe.
 * @param assertionCreator The factory function which is used for lazy loading.
 */
class LazyThreadUnsafeBasicAssertion(private val assertionCreator: () -> IBasicAssertion) : IBasicAssertion {
    private val basicAssertion by lazy(LazyThreadSafetyMode.NONE) {
        assertionCreator()
    }

    override val expected = basicAssertion.expected
    override val description = basicAssertion.description
    override fun holds() = basicAssertion.holds()
}
