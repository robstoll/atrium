package ch.tutteli.atrium.domain.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.domain.builders.creating.*
import ch.tutteli.atrium.domain.builders.creating.collectors.AssertionCollectorBuilder
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Untranslatable
import java.math.BigDecimal
import java.util.*

/**
 * Bundles different domain objects which are defined by the module atrium-domain-api
 * to give users of Atrium a fluent API as well.
 */
object AssertImpl {

    /**
     * Returns [AssertionBuilder] - helping you creating [Assertion]s.
     * In detail, its an `inline` property which returns [ch.tutteli.atrium.assertions.builders.assertionBuilder]
     * which in turn returns an implementation of [AssertionBuilder].
     */
    inline val builder get() = assertionBuilder

    /**
     * Returns [AssertionCollectorBuilder] - helping you to collect feature assertions.
     * which inter alia delegates to the implementation of [AssertionCollector].
     */
    inline val collector get() = AssertionCollectorBuilder

    /**
     * Returns the implementation of [CoreFactory].
     * In detail, its an `inline` property which returns [ch.tutteli.atrium.core.coreFactory]
     * which in turn delegates to the implementation via [ServiceLoader].
     */
    inline val coreFactory get() = ch.tutteli.atrium.core.coreFactory

    /**
     * Creates a new [AssertionPlant] based on the given [subjectProvider] whereas the [AssertionPlant] delegates
     * assertion checking to the given [originalPlant].
     *
     * This method is useful if you want to make feature assertion(s) but you do not want that the feature is shown up
     * in reporting. For instance, if a class can behave as another class (e.g. `Sequence::asIterable`) or you want to
     * hide a conversion (e.g. `Int::toChar`) then you can use this function.
     *
     * Notice, if you do not require the resulting [AssertionPlant] but merely want to make feature assertions so that
     * you can use them as part of a bigger assertion, then use [collector] instead.
     */
    fun <T, R : Any> changeSubject(
        originalPlant: BaseAssertionPlant<T, *>,
        subjectProvider: () -> R
    ): AssertionPlant<R> {
        val assertionChecker = coreFactory.newDelegatingAssertionChecker(originalPlant)
        val assertionVerb = Untranslatable(
            "Should not be shown to the user; if you see this, please fill in a bug report at $BUG_REPORT_URL"
        )
        return coreFactory.newReportingPlant(assertionVerb, subjectProvider, assertionChecker)
    }


//--- assertions ---------------------------------------------------------------------------

    /**
     * Returns [AnyAssertionsBuilder]
     * which inter alia delegates to the implementation of [AnyAssertions].
     */
    inline val any get() = AnyAssertionsBuilder

    /**
     * Returns [BigDecimalAssertionsBuilder]
     * which inter alia delegates to the implementation of [BigDecimalAssertions].
     */
    inline val bigDecimal get() = BigDecimalAssertionsBuilder

    /**
     * Returns [CharSequenceAssertionsBuilder]
     * which inter alia delegates to the implementation of [CharSequenceAssertions].
     */
    inline val charSequence get() = CharSequenceAssertionsBuilder

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
     * Returns [FeatureAssertionsBuilder]
     * which inter alia delegates to the implementation of [FeatureAssertions].
     */
    inline val feature get() = FeatureAssertionsBuilder

    /**
     * Returns [FloatingPointAssertionsBuilder] - [Assertion]s applicable to [Float], [Double], [BigDecimal]
     * and maybe more.
     * which inter alia delegates to the implementation of [FloatingPointAssertions].
     */
    inline val floatingPoint get() = FloatingPointAssertionsBuilder

    /**
     * Returns [IterableAssertionsBuilder].
     * which inter alia delegates to the implementation of [IterableAssertions].
     */
    inline val iterable get() = IterableAssertionsBuilder

    /**
     * Returns [MapAssertionsBuilder]
     * which inter alia delegates to the implementation of [MapAssertions].
     */
    inline val map get() = MapAssertionsBuilder

    /**
     * Returns [ThrowableAssertionsBuilder]
     * which inter alia delegates to the implementation of [ThrowableAssertions].
     */
    inline val throwable get() = ThrowableAssertionsBuilder
}
