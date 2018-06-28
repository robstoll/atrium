package ch.tutteli.atrium.domain.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.domain.builders.creating.*
import ch.tutteli.atrium.domain.builders.creating.collectors.AssertionCollectorBuilder
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
import java.math.BigDecimal
import ch.tutteli.atrium.core.polyfills.loadSingleService

/**
 * Bundles different domain objects which are defined by the module atrium-domain-api
 * to give users of Atrium a fluent API for the domain as well.
 */
@Suppress("OVERRIDE_BY_INLINE")
actual object AssertImpl : AssertImplCommon {

    /**
     * Returns [AssertionBuilder] - helping you creating [Assertion]s.
     * In detail, its an `inline` property which returns [ch.tutteli.atrium.assertions.builders.assertionBuilder]
     * which in turn returns an implementation of [AssertionBuilder].
     */
    override inline val builder get() = assertionBuilder

    /**
     * Returns [AssertionCollectorBuilder] - helping you to collect feature assertions.
     * which inter alia delegates to the implementation of [AssertionCollector].
     */
    override inline val collector get() = AssertionCollectorBuilder

    /**
     * Returns the implementation of [CoreFactory].
     * In detail, its an `inline` property which returns [ch.tutteli.atrium.core.coreFactory]
     * which in turn delegates to the implementation via [loadSingleService].
     */
    override inline val coreFactory get() = ch.tutteli.atrium.core.coreFactory


//--- assertions ---------------------------------------------------------------------------

    /**
     * Returns [AnyAssertionsBuilder]
     * which inter alia delegates to the implementation of [AnyAssertions].
     */
    override inline val any get() = AnyAssertionsBuilder

    /**
     * Returns [BigDecimalAssertionsBuilder]
     * which inter alia delegates to the implementation of [BigDecimalAssertions].
     */
    inline val bigDecimal get() = BigDecimalAssertionsBuilder

    /**
     * Returns [CharSequenceAssertionsBuilder]
     * which inter alia delegates to the implementation of [CharSequenceAssertions].
     */
    override inline val charSequence get() = CharSequenceAssertionsBuilder

    /**
     * Returns [CollectionAssertionsBuilder]
     * which inter alia delegates to the implementation of [CollectionAssertions].
     */
    override inline val collection get() = CollectionAssertionsBuilder

    /**
     * Returns [ComparableAssertionsBuilder]
     * which inter alia delegates to the implementation of [ComparableAssertions].
     */
    override inline val comparable get() = ComparableAssertionsBuilder

    /**
     * Returns [FeatureAssertionsBuilder]
     * which inter alia delegates to the implementation of [FeatureAssertions].
     */
    override inline val feature get() = FeatureAssertionsBuilder

    /**
     * Returns [FloatingPointAssertionsBuilder] - [Assertion]s applicable to [Float], [Double], [BigDecimal]
     * and maybe more.
     * which inter alia delegates to the implementation of [FloatingPointAssertions].
     */
    override inline val floatingPoint get() = FloatingPointAssertionsBuilder

    /**
     * Returns [IterableAssertionsBuilder].
     * which inter alia delegates to the implementation of [IterableAssertions].
     */
    override inline val iterable get() = IterableAssertionsBuilder

    /**
     * Returns [MapAssertionsBuilder]
     * which inter alia delegates to the implementation of [MapAssertions].
     */
    override inline val map get() = MapAssertionsBuilder

    /**
     * Returns [ThrowableAssertionsBuilder]
     * which inter alia delegates to the implementation of [ThrowableAssertions].
     */
    override inline val throwable get() = ThrowableAssertionsBuilder
}
