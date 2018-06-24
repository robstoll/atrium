package ch.tutteli.atrium.domain.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.core.SingleServiceLoader
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.domain.creating.MapAssertions
import java.util.*

/**
 * The access point to an implementation of [AssertionCollector].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val assertionCollector: AssertionCollector by lazy { SingleServiceLoader.load(AssertionCollector::class.java) }

/**
 * Responsible to collect assertions made in a sub-[AssertionPlant].
 */
interface AssertionCollector {

    /**
     * Use this function if you want to make [Assertion](s) about a feature or you perform a type transformation or any
     * other action which results in a sub-[AssertionPlant] being created and you do not require this resulting plant.
     *
     * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
     * assertion plant.
     *
     * This function can be useful in several cases. For instance:
     * - You are writing an assertion about a feature often enough so that it deserves an own assertion function
     *   (see e.g. [MapAssertions.hasSize])
     * - You want the collected assertion to be part of an [AssertionGroup]
     *
     * @param plant The plant from which the [AssertionPlant.subject] will be used as subject of the
     *   [CollectingAssertionPlant].
     * @param subPlantAndAssertionCreator A lambda which typically creates a sub [AssertionPlant] and adds assertions
     *   to it. For instance, if you create a feature assertion or a type transformation assertion, you will typically
     *   end up creating a sub assertion plant which delegates created [Assertion]s to the [CollectingAssertionPlant].
     *
     * @return The collected assertions as an [AssertionGroup] with an [InvisibleAssertionGroupType].
     *
     * @throws IllegalArgumentException in case the given [subPlantAndAssertionCreator] did not create a single
     *   assertion, did not pass it to the [CollectingAssertionPlant] respectively.
     */
    fun <T: Any> collect(plant: AssertionPlant<T>, subPlantAndAssertionCreator: CollectingAssertionPlant<T>.() -> Unit): AssertionGroup
        = collect(plant.subjectProvider, subPlantAndAssertionCreator)

    /**
     * Use this function if you want to make [Assertion](s) about a feature or you perform a type transformation or any
     * other action which results in a sub-[AssertionPlant] being created and you do not require this resulting plant.
     *
     * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
     * assertion plant.
     *
     * This function can be useful in several cases. For instance:
     * - You are writing an assertion about a feature often enough so that it deserves an own assertion function
     *   (see e.g. [MapAssertions.hasSize])
     * - You want the collected assertion to be part of an [AssertionGroup]
     *
     * @param subjectProvider Provides the subject which is used as [CollectingAssertionPlant.subject].
     * @param subPlantAndAssertionCreator A lambda which typically creates a sub [AssertionPlant] and adds assertions
     *   to it. For instance, if you create a feature assertion or a type transformation assertion, you will typically
     *   end up creating a sub assertion plant which delegates created [Assertion]s to the [CollectingAssertionPlant].
     *
     * @return The collected assertions as an [AssertionGroup] with an [InvisibleAssertionGroupType].
     *
     * @throws IllegalArgumentException in case the given [subPlantAndAssertionCreator] did not create a single
     *   assertion, did not pass it to the [CollectingAssertionPlant] respectively.
     */
    fun <T: Any> collect(subjectProvider: () -> T, subPlantAndAssertionCreator: CollectingAssertionPlant<T>.() -> Unit): AssertionGroup
}
