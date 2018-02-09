@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.creating

object AssertImpl {
    inline val any get() = AnyAssertionsBuilder
    inline val bigDecimal get() = BigDecimalAssertionsBuilder
    inline val charSequence get() = CharSequenceAssertionsBuilder
    inline val collection get() = CollectionAssertionsBuilder
    inline val comparable get() = ComparableAssertionsBuilder
    inline val feature get() = FeatureAssertionsBuilder
    inline val floatingPoint get() = FloatingPointAssertionsBuilder
    inline val iterable get() = IterableAssertionsBuilder
    inline val throwable get() = ThrowableAssertionsBuilder
}

