@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

/**
 * Bundles different domain objects which are defined by the module atrium-domain-api
 * to give users of Atrium a fluent API as well.
 */
object AssertImpl {
    /**
     * Delegates to [anyAssertions].
     */
    inline val any get() = AnyAssertionsBuilder

    /**
     * Delegates to [bigDecimalAssertions].
     */
    inline val bigDecimal get() = BigDecimalAssertionsBuilder

    /**
     * Delegates to [charSequenceAssertions].
     */
    inline val charSequence get() = CharSequenceAssertionsBuilder

    /**
     * Delegates to [collectionAssertions].
     */
    inline val collection get() = CollectionAssertionsBuilder

    /**
     * Delegates to [comparableAssertions].
     */
    inline val comparable get() = ComparableAssertionsBuilder

    /**
     * Delegates to [featureAssertions].
     */
    inline val feature get() = FeatureAssertionsBuilder

    /**
     * Delegates to [floatingPointAssertions].
     */
    inline val floatingPoint get() = FloatingPointAssertionsBuilder

    /**
     * Delegates to [iterableAssertions].
     */
    inline val iterable get() = IterableAssertionsBuilder

    /**
     * Delegates to [mapAssertions].
     */
    inline val map get() = MapAssertionsBuilder

    /**
     * Delegates to [throwableAssertions].
     */
    inline val throwable get() = ThrowableAssertionsBuilder
}

