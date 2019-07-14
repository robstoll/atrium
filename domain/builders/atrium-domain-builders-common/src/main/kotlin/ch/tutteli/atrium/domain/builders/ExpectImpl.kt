package ch.tutteli.atrium.domain.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.polyfills.loadSingleService
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
@Suppress("OVERRIDE_BY_INLINE")
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
    inline val changeSubject get() = SubjectChangerBuilder

    /**
     * Returns [AssertionCollectorBuilder] - helping you to collect feature assertions.
     * In detail, its an `inline` property which returns [AssertionCollectorBuilder]
     * which inter alia delegates to the implementation of [AssertionCollector].
     */
    inline val collector get() = AssertionCollectorBuilder

    /**
     * Returns the implementation of [CoreFactory].
     * In detail, its an `inline` property which returns [ch.tutteli.atrium.core.coreFactory]
     * which in turn delegates to the implementation via [loadSingleService].
     */
    inline val coreFactory get() = ch.tutteli.atrium.core.coreFactory


    //--- assertions ---------------------------------------------------------------------------

    /**
     * Returns [AnyAssertionsBuilder]
     * which inter alia delegates to the implementation of [AnyAssertions].
     */
    inline val any get() = AnyAssertionsBuilder

//    override inline val charSequence get() = CharSequenceAssertionsBuilder
//
    /**
     * Returns [CollectionAssertionsBuilder]
     * which inter alia delegates to the implementation of [CollectionAssertions].
     */
    inline val collection get() = CollectionAssertionsBuilder

    /**
     * Returns [ComparableAssertionsBuilder]
     * which inter alia delegates to the implementation of [ComparableAssertions].
     */
    inline val comparable get() = ComparableAssertionsBuilder

    /**
     * Returns [NewFeatureAssertionsBuilder]
     * which inter alia delegates to the implementation of [FeatureAssertions].
     */
    inline val feature get() = NewFeatureAssertionsBuilder

    /**
     * Returns [FloatingPointAssertionsBuilder] - [Assertion]s applicable to [Float], [Double]
     * and maybe more - which inter alia delegates to the implementation of [FloatingPointAssertions].
     */
    inline val floatingPoint get() = FloatingPointAssertionsBuilder

//    override inline val iterable get() = IterableAssertionsBuilder

    /**
     * Returns [ListAssertionsBuilder]
     * which inter alia delegates to the implementation of [ListAssertions].
     */
    val list get() = ListAssertionsBuilder

//
//    override inline val map get() = MapAssertionsBuilder
//
//    override inline val pair get() = PairAssertionsBuilder
//
//    override inline val throwable get() = ThrowableAssertionsBuilder
}

//interface ExpectImplCommon {

//--- assertions ---------------------------------------------------------------------------


//    /**
//     * Returns [CharSequenceAssertionsBuilder]
//     * which inter alia delegates to the implementation of [CharSequenceAssertions].
//     */
//    val charSequence: CharSequenceAssertionsBuilder

//
//    /**
//     * Returns [IterableAssertionsBuilder].
//     * which inter alia delegates to the implementation of [IterableAssertions].
//     */
//    val iterable: IterableAssertionsBuilder

//    /**
//     * Returns [PairAssertionsBuilder]
//     * which inter alia delegates to the implementation of [PairAssertions].
//     */
//    val pair: PairAssertionsBuilder
//
//    /**
//     * Returns [ThrowableAssertionsBuilder]
//     * which inter alia delegates to the implementation of [ThrowableAssertions].
//     */
//    val throwable: ThrowableAssertionsBuilder
//}
