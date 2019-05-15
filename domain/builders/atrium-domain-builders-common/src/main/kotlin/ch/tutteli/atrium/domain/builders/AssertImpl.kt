package ch.tutteli.atrium.domain.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.newReportingPlantNullable
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.domain.builders.creating.*
import ch.tutteli.atrium.domain.builders.creating.collectors.AssertionCollectorBuilder
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Bundles different domain objects which are defined by the module atrium-domain-api
 * to give users of Atrium a fluent API as well.
 */
@Suppress("OVERRIDE_BY_INLINE")
object AssertImpl : AssertImplCommon {

    override inline val builder get() = assertionBuilder

    override inline val collector get() = AssertionCollectorBuilder

    override inline val coreFactory get() = ch.tutteli.atrium.core.coreFactory


    //--- assertions ---------------------------------------------------------------------------

    override inline val any get() = AnyAssertionsBuilder

    override inline val charSequence get() = CharSequenceAssertionsBuilder

    override inline val collection get() = CollectionAssertionsBuilder

    override inline val comparable get() = ComparableAssertionsBuilder

    override inline val feature get() = FeatureAssertionsBuilder

    override inline val floatingPoint get() = FloatingPointAssertionsBuilder

    override inline val iterable get() = IterableAssertionsBuilder

    override inline val list get() = ListAssertionsBuilder

    override inline val map get() = MapAssertionsBuilder

    override inline val pair get() = PairAssertionsBuilder

    override inline val throwable get() = ThrowableAssertionsBuilder
}

interface AssertImplCommon {

    /**
     * Returns [AssertionBuilder] - helping you creating [Assertion]s.
     * In detail, its an `inline` property which returns [ch.tutteli.atrium.assertions.builders.assertionBuilder]
     * which in turn returns an implementation of [AssertionBuilder].
     */
    val builder: AssertionBuilder

    /**
     * Returns [AssertionCollectorBuilder] - helping you to collect feature assertions.
     * which inter alia delegates to the implementation of [AssertionCollector].
     */
    val collector: AssertionCollectorBuilder

    /**
     * Returns the implementation of [CoreFactory].
     * In detail, its an `inline` property which returns [ch.tutteli.atrium.core.coreFactory]
     * which in turn delegates to the implementation via [loadSingleService].
     */
    val coreFactory: CoreFactory

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
        val (assertionChecker, assertionVerb) = createDelegatingAssertionCheckerAndVerb(originalPlant)
        return AssertImpl.coreFactory.newReportingPlant(assertionVerb, subjectProvider, assertionChecker)
    }

    /**
     * Creates a new [AssertionPlantNullable] based on the given [subjectProvider] whereas the [AssertionPlant]
     * delegates assertion checking to the given [originalPlant].
     *
     * This method is useful if you want to make feature assertion(s) but you do not want that the feature is shown up
     * in reporting. For instance, if a class can behave as another class (e.g. `Sequence::asIterable`) or you want to
     * hide a conversion (e.g. `Int::toChar`) then you can use this function.
     *
     * Notice, if you do not require the resulting [AssertionPlantNullable] but merely want to make feature
     * assertions so that you can use them as part of a bigger assertion, then use [collector] instead.
     */
    fun <T, R> changeToNullableSubject(
        originalPlant: BaseAssertionPlant<T, *>,
        subjectProvider: () -> R
    ): AssertionPlantNullable<R> {
        val (assertionChecker, assertionVerb) = createDelegatingAssertionCheckerAndVerb(originalPlant)
        return AssertImpl.coreFactory.newReportingPlantNullable(assertionVerb, subjectProvider, assertionChecker)
    }

    private fun <T> createDelegatingAssertionCheckerAndVerb(originalPlant: BaseAssertionPlant<T, *>): Pair<AssertionChecker, Untranslatable> {
        val assertionChecker = AssertImpl.coreFactory.newDelegatingAssertionChecker(originalPlant)
        return assertionChecker to Untranslatable(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG)
    }


    //--- assertions ---------------------------------------------------------------------------

    /**
     * Returns [AnyAssertionsBuilder]
     * which inter alia delegates to the implementation of [AnyAssertions].
     */
    val any: AnyAssertionsBuilder

    /**
     * Returns [CharSequenceAssertionsBuilder]
     * which inter alia delegates to the implementation of [CharSequenceAssertions].
     */
    val charSequence: CharSequenceAssertionsBuilder

    /**
     * Returns [CollectionAssertionsBuilder]
     * which inter alia delegates to the implementation of [CollectionAssertions].
     */
    val collection: CollectionAssertionsBuilder

    /**
     * Returns [ComparableAssertionsBuilder]
     * which inter alia delegates to the implementation of [ComparableAssertions].
     */
    val comparable: ComparableAssertionsBuilder

    /**
     * Returns [FeatureAssertionsBuilder]
     * which inter alia delegates to the implementation of [FeatureAssertions].
     */
    val feature: FeatureAssertionsBuilder

    /**
     * Returns [FloatingPointAssertionsBuilder] - [Assertion]s applicable to [Float], [Double]
     * and maybe more - which inter alia delegates to the implementation of [FloatingPointAssertions].
     */
    val floatingPoint: FloatingPointAssertionsBuilder

    /**
     * Returns [IterableAssertionsBuilder].
     * which inter alia delegates to the implementation of [IterableAssertions].
     */
    val iterable: IterableAssertionsBuilder

    /**
     * Returns [ListAssertionsBuilder]
     * which inter alia delegates to the implementation of [ListAssertions].
     */
    val list: ListAssertionsBuilder

    /**
     * Returns [MapAssertionsBuilder]
     * which inter alia delegates to the implementation of [MapAssertions].
     */
    val map: MapAssertionsBuilder

    /**
     * Returns [PairAssertionsBuilder]
     * which inter alia delegates to the implementation of [PairAssertions].
     */
    val pair: PairAssertionsBuilder

    /**
     * Returns [ThrowableAssertionsBuilder]
     * which inter alia delegates to the implementation of [ThrowableAssertions].
     */
    val throwable: ThrowableAssertionsBuilder
}
