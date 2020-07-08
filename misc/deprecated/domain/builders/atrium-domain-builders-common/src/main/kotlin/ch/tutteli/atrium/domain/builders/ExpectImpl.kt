//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.creating.*
import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.builders.creating.collectors.AssertionCollectorBuilder
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
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
     * Returns [SubjectChangerBuilder] - helping you to change the subject of the assertion.
     * In detail, its an `inline` property which returns [SubjectChangerBuilder]
     * which inter alia delegates to the implementation of [SubjectChanger].
     *
     * In case you want to extract a feature (e.g. get the first element of a `List`) instead of changing the subject
     * into another representation (e.g. down-cast `Person` to `Student`) then you should use
     * [feature.extractor][NewFeatureAssertionsBuilder.extractor] instead.
     */
    inline fun <T> changeSubject(originalExpect: Expect<T>) =
        SubjectChangerBuilder.create(originalExpect)

    @Deprecated("Do no longer use Assert, use Expect instead - this method was introduced in 0.9.0 to ease the migration from Assert to Expect; will be removed with 1.0.0")
    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    inline fun <T> changeSubject(originalAssertionContainer: SubjectProvider<T>) =
        SubjectChangerBuilder.create(originalAssertionContainer)

    /**
     * Returns [AssertionCollectorBuilder] - helping you to collect feature assertions.
     * In detail, its an `inline` property which returns [AssertionCollectorBuilder]
     * which inter alia delegates to the implementation of [AssertionCollector].
     */
    inline val collector get() = AssertionCollectorBuilder

    //--- assertions ---------------------------------------------------------------------------

    /**
     * Returns [AnyAssertionsBuilder]
     * which inter alia delegates to the implementation of [AnyAssertions].
     */
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
    inline val any get() = AnyAssertionsBuilder

    /**
     * Returns [CharSequenceAssertionsBuilder]
     * which inter alia delegates to the implementation of [CharSequenceAssertions].
     */
    inline val charSequence get() = CharSequenceAssertionsBuilder

    /**
     * Returns [CollectionAssertionsBuilder]
     * which inter alia delegates to the implementation of [CollectionAssertions].
     */
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
    inline val collection get() = CollectionAssertionsBuilder

    /**
     * Returns [ComparableAssertionsBuilder]
     * which inter alia delegates to the implementation of [ComparableAssertions].
     */
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
    inline val comparable get() = ComparableAssertionsBuilder

    /**
     * Returns [NewFeatureAssertionsBuilder]
     * which inter alia delegates to the implementation of [FeatureAssertions].
     */
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
    inline val feature get() = NewFeatureAssertionsBuilder

    /**
     * Returns [FloatingPointAssertionsBuilder] - [Assertion]s applicable to [Float], [Double]
     * and maybe more - which inter alia delegates to the implementation of [FloatingPointAssertions].
     */
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
    inline val floatingPoint get() = FloatingPointAssertionsBuilder

    /**
     * Returns [Fun0AssertionsBuilder] - [Assertion]s applicable to lambdas with arity 0
     * which inter alia delegates to the implementation of [FloatingPointAssertions].
     */
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
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
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
    val list get() = ListAssertionsBuilder

    /**
     * Returns [MapAssertionsBuilder]
     * which inter alia delegates to the implementation of [MapAssertions].
     */
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
    inline val map get() = MapAssertionsBuilder

    /**
     * Returns [PairAssertionsBuilder]
     * which inter alia delegates to the implementation of [PairAssertions].
     */
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
    inline val pair get() = PairAssertionsBuilder

    /**
     * Returns [ThrowableAssertionsBuilder]
     * which inter alia delegates to the implementation of [ThrowableAssertions].
     */
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
    inline val throwable get() = ThrowableAssertionsBuilder

    /**
     * Returns [IteratorAssertionsBuilder]
     * which inter alia delegates to the implementation of [IteratorAssertions].
     */
    @Deprecated("Use _logic instead; will be removed with 1.0.0")
    inline val iterator get() = IteratorAssertionsBuilder
}
