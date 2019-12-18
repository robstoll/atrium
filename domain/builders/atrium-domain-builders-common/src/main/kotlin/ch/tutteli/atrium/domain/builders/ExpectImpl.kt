package ch.tutteli.atrium.domain.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.domain.builders.creating.*
import ch.tutteli.atrium.domain.builders.creating.collectors.AssertionCollectorBuilder
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector

/**
 * Bundles different domain objects which are defined by the module atrium-domain-api
 * to give assertion writers (and other consumers of the domain) a fluent API as well.
 */
@Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
object ExpectImpl {

    /**
     * Returns [AssertionBuilder] - helping you creating [Assertion]s.
     * In detail, its an `inline` property which returns [ch.tutteli.atrium.assertions.builders.assertionBuilder]
     * which in turn returns an implementation of [AssertionBuilder].
     */
    inline val builder get() = assertionBuilder

    /**
     * Returns [AssertionCollectorBuilder] - helping you to collect feature assertions.
     * In detail, its an `inline` property which returns [AssertionCollectorBuilder]
     * which inter alia delegates to the implementation of [AssertionCollector].
     */
    inline val collector get() = AssertionCollectorBuilder

    //--- assertions ---------------------------------------------------------------------------

    /**
     * Returns [FloatingPointAssertionsBuilder] - [Assertion]s applicable to [Float], [Double]
     * and maybe more - which inter alia delegates to the implementation of [FloatingPointAssertions].
     */
    inline val floatingPoint get() = FloatingPointAssertionsBuilder

    /**
     * Returns [Fun0AssertionsBuilder] - [Assertion]s applicable to lambdas with arity 0
     * which inter alia delegates to the implementation of [FloatingPointAssertions].
     */
    inline val fun0 get() = Fun0AssertionsBuilder

    /**
     * Returns [IterableAssertionsBuilder].
     * which inter alia delegates to the implementation of [IterableAssertions].
     */
    inline val iterable get() = IterableAssertionsBuilder

    /**
     * Returns [ListAssertionsBuilder]
     * which inter alia delegates to the implementation of [ListAssertions].
     */
    val list get() = ListAssertionsBuilder

    /**
     * Returns [MapAssertionsBuilder]
     * which inter alia delegates to the implementation of [MapAssertions].
     */
    inline val map get() = MapAssertionsBuilder

    /**
     * Returns [PairAssertionsBuilder]
     * which inter alia delegates to the implementation of [PairAssertions].
     */
    inline val pair get() = PairAssertionsBuilder

    /**
     * Returns [ThrowableAssertionsBuilder]
     * which inter alia delegates to the implementation of [ThrowableAssertions].
     */
    inline val throwable get() = ThrowableAssertionsBuilder
}
