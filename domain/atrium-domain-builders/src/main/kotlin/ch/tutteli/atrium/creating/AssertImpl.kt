@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.creating

/**
 * Bundles different domain objects which are defined by the module atrium-domain-api
 * to give users of Atrium a fluent API as well.
 */
object AssertImpl {
    /**
     * Delegates to [AnyAssertions].
     */
    inline val any get() = AnyAssertionsBuilder

    /**
     * Delegates to [BigDecimalAssertions].
     */
    inline val bigDecimal get() = BigDecimalAssertionsBuilder

    /**
     * Delegates to [CharSequenceAssertions].
     */
    inline val charSequence get() = CharSequenceAssertionsBuilder

    /**
     * Delegates to [CollectionAssertions].
     */
    inline val collection get() = CollectionAssertionsBuilder

    /**
     * Delegates to [ComparableAssertions].
     */
    inline val comparable get() = ComparableAssertionsBuilder

    /**
     * Delegates to [FeatureAssertions].
     */
    inline val feature get() = FeatureAssertionsBuilder

    /**
     * Delegates to [FloatingPointAssertions].
     */
    inline val floatingPoint get() = FloatingPointAssertionsBuilder

    /**
     * Delegates to [IterableAssertions].
     */
    inline val iterable get() = IterableAssertionsBuilder

    /**
     * Delegates to [MapAssertions].
     */
    inline val map get() = MapAssertionsBuilder

    /**
     * Delegates to [ThrowableAssertions].
     */
    inline val throwable get() = ThrowableAssertionsBuilder
}

