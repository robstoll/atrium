package ch.tutteli.atrium.domain.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.core.SingleServiceLoader
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.reporting.translating.Translatable
import java.util.*

/**
 * The access point to an implementation of [NonThrowingAssertionCollectorForExplanation].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val nonThrowingAssertionCollectorForExplanation: NonThrowingAssertionCollectorForExplanation by lazy {
    SingleServiceLoader.load(NonThrowingAssertionCollectorForExplanation::class.java)
}

/**
 * Represents an assertion collector meant for explanation which does *not* throw in case not a single [Assertion]
 * was collected.
 */
interface NonThrowingAssertionCollectorForExplanation {

    /**
     * Collects the [Assertion] created by [assertionCreator] and uses the given [subject] as
     * [CollectingAssertionPlant.subject] if not null.
     *
     * In contrast to regular [AssertionCollector] a
     *
     * In case [subject] is null and [assertionCreator] tries to access it, then a [PlantHasNoSubjectException] is
     * thrown and caught in which case a single [AssertionGroup] with an [ExplanatoryAssertionGroupType]
     * is returned containing the given [warning].
     *
     * In contrast to [ThrowingAssertionCollectorForExplanation] this assertion collector does not throw.
     *
     * @param warning The translatable used to explain why the assertions could not be evaluated.
     * @param assertionCreator The function which should at least create one assertion.
     * @param subject The subject which will be used for the [AssertionPlant].
     *
     * @return A list with the collected assertion or an [AssertionGroup] with an [ExplanatoryAssertionGroupType]
     *   containing a warning if [subject] is `null` and an assertion function tries to access it.
     */
    fun <E : Any> collect(warning: Translatable, assertionCreator: (AssertionPlant<E>.() -> Unit)?, subject: E?): List<Assertion>
}


/**
 * The access point to an implementation of [ThrowingAssertionCollectorForExplanation].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val throwingAssertionCollectorForExplanation: ThrowingAssertionCollectorForExplanation by lazy {
    SingleServiceLoader.load(ThrowingAssertionCollectorForExplanation::class.java)
}

/**
 * Represents an assertion collector meant for explanation which throws in case not a single [Assertion] was collected.
 */
interface ThrowingAssertionCollectorForExplanation {
    /**
     * Collects the [Assertion] created by [assertionCreator] and uses the given [subject] as
     * [CollectingAssertionPlant.subject] if not null.
     *
     * In case [subject] is null then a [PlantHasNoSubjectException] is thrown in case the
     * subject is accessed. In such a case a single [AssertionGroup] with an
     * [ExplanatoryAssertionGroupType] is returned containing the given [warning].
     *
     * In contrast to [ThrowingAssertionCollectorForExplanation] this assertion collector should not throw
     *
     * @param warning The translatable used to explain why the assertions could not be evaluated.
     * @param assertionCreator The function which should at least create one assertion.
     * @param subject The subject which will be used for the [AssertionPlant].
     *
     * @return A list with the collected assertion or an [AssertionGroup] with an [ExplanatoryAssertionGroupType]
     *   containing a warning if [subject] is `null` and an assertion function tries to access it.
     *
     * @throws IllegalArgumentException in case not a single [Assertion] was collected.
     */
    fun <E : Any> collect(warning: Translatable, assertionCreator: (AssertionPlant<E>.() -> Unit)?, subject: E?): List<Assertion>
}
