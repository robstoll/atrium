package ch.tutteli.atrium.domain.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.creating.MapAssertions

/**
 * The access point to an implementation of [AssertionCollector].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val assertionCollector: AssertionCollector by lazy { loadSingleService(AssertionCollector::class) }

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
     * @param assertionCreator A lambda which typically creates a sub [AssertionPlant] and adds assertions
     *   to it. For instance, if you create a feature assertion or a type transformation assertion, you will typically
     *   end up creating a sub assertion plant which delegates created [Assertion]s to the [CollectingAssertionPlant].
     *
     * @return The collected assertions as an [AssertionGroup] with an [InvisibleAssertionGroupType].
     *
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
     *   assertion, did not pass it to the [CollectingAssertionPlant] respectively.
     */
    fun <T : Any> collect(
        plant: AssertionPlant<T>,
        assertionCreator: CollectingAssertionPlant<T>.() -> Unit
    ): AssertionGroup = collect(plant.subjectProvider, assertionCreator)

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
     * @param assertionCreator A lambda which typically creates a sub [AssertionPlant] and adds assertions
     *   to it. For instance, if you create a feature assertion or a type transformation assertion, you will typically
     *   end up creating a sub assertion plant which delegates created [Assertion]s to the [CollectingAssertionPlant].
     *
     * @return The collected assertions as an [AssertionGroup] with an [InvisibleAssertionGroupType].
     *
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
     *   assertion, did not pass it to the [CollectingAssertionPlant] respectively.
     */
    fun <T : Any> collect(
        subjectProvider: () -> T,
        assertionCreator: CollectingAssertionPlant<T>.() -> Unit
    ): AssertionGroup = collect(subjectProvider, coreFactory::newCollectingPlant, assertionCreator)

    /**
     * Use this function if you want to make [Assertion](s) about a feature or you perform a type transformation or any
     * other action which results in a sub-[AssertionPlantNullable] being created and you do not require this resulting
     * plant.
     *
     * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
     * assertion plant.
     *
     * This function can be useful in several cases. For instance:
     * - You are writing an assertion about a feature often enough so that it deserves an own assertion function
     *   (see e.g. [MapAssertions.hasSize])
     * - You want the collected assertion to be part of an [AssertionGroup]
     *
     * @param plant The plant from which the [AssertionPlantNullable.subject] will be used as subject of the
     *   [CollectingAssertionPlantNullable].
     * @param assertionCreator A lambda which typically creates a sub [AssertionPlantNullable] and adds
     *   assertions to it. For instance, if you create a feature assertion or a type transformation assertion, you will
     *   typically end up creating a sub assertion plant which delegates created [Assertion]s to the
     *   [CollectingAssertionPlantNullable].
     *
     * @return The collected assertions as an [AssertionGroup] with an [InvisibleAssertionGroupType].
     *
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
     *   assertion, did not pass it to the [CollectingAssertionPlantNullable] respectively.
     */
    fun <T> collectNullable(
        plant: AssertionPlantNullable<T>,
        assertionCreator: CollectingAssertionPlantNullable<T>.() -> Unit
    ): AssertionGroup = collectNullable(plant.subjectProvider, assertionCreator)

    /**
     * Use this function if you want to make [Assertion](s) about a feature or you perform a type transformation or any
     * other action which results in a sub-[AssertionPlantNullable] being created and you do not require this resulting
     * plant.
     *
     * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
     * assertion plant.
     *
     * This function can be useful in several cases. For instance:
     * - You are writing an assertion about a feature often enough so that it deserves an own assertion function
     *   (see e.g. [MapAssertions.hasSize])
     * - You want the collected assertion to be part of an [AssertionGroup]
     *
     * @param subjectProvider Provides the subject which is used as [CollectingAssertionPlantNullable.subject].
     * @param assertionCreator A lambda which typically creates a sub [AssertionPlantNullable] and adds
     *   assertions to it. For instance, if you create a feature assertion or a type transformation assertion, you will
     *   typically end up creating a sub assertion plant which delegates created [Assertion]s to the
     *   [CollectingAssertionPlantNullable].
     *
     * @return The collected assertions as an [AssertionGroup] with an [InvisibleAssertionGroupType].
     *
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
     *   assertion, did not pass it to the [CollectingAssertionPlantNullable] respectively.
     */
    fun <T> collectNullable(
        subjectProvider: () -> T,
        assertionCreator: CollectingAssertionPlantNullable<T>.() -> Unit
    ): AssertionGroup = collect(subjectProvider, coreFactory::newCollectingPlantNullable, assertionCreator)


    /**
     * Use this function if you want to make [Assertion](s) about a feature or you perform a type transformation or any
     * other action which results in a sub-[BaseAssertionPlant] being created and
     * you do not require this resulting plant.
     *
     * Or in other words, you do not want to make further assertions about the resulting subject in the resulting sub
     * assertion plant.
     *
     * This function can be useful in several cases. For instance:
     * - You are writing an assertion about a feature often enough so that it deserves an own assertion function
     *   (see e.g. [MapAssertions.hasSize])
     * - You want the collected assertion to be part of an [AssertionGroup]
     *
     * @param subjectProvider Provides the subject which is used as [CollectingAssertionPlantNullable.subject].
     * @param collectingPlantFactory The factory method which creates the appropriate collecting plant which is suitable
     *   for the given [assertionCreator].
     * @param assertionCreator A lambda which typically creates a sub [AssertionPlant] and adds assertions
     *   to it. For instance, if you create a feature assertion or a type transformation assertion, you will typically
     *   end up creating a sub assertion plant which delegates created [Assertion]s to the [CollectingAssertionPlant].
     *
     * @return The collected assertions as an [AssertionGroup] with an [InvisibleAssertionGroupType].
     *
     * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single
     *   assertion, did not pass it to the [BaseCollectingAssertionPlant] respectively.
     */
    fun <T, A: BaseAssertionPlant<T, A>, C: BaseCollectingAssertionPlant<T, A, C>> collect(
        subjectProvider: () -> T,
        collectingPlantFactory: (() -> T) -> C,
        assertionCreator: C.() -> Unit
    ): AssertionGroup
}
