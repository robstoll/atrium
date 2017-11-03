package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.ICollectingAssertionPlant
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

object AssertionCollector {
    /**
     * Collects the [IAssertion] created by [createAssertions] and uses the given [subject] as
     * [ICollectingAssertionPlant.subject] if not null.
     *
     * In case [subject] is null then an [ICollectingAssertionPlant.PlantHasNoSubjectException] is thrown in case the
     * subject is accessed (which does not need to be the case all the time). In such a case a single
     * [ExplanatoryAssertionGroup] is returned containing a warning.
     *
     * @param createAssertions The function which should at least create one assertion.
     * @param subject The subject which will be used for the [IAssertionPlant]
     *
     * @return A list with the collected assertion or an [ExplanatoryAssertionGroup] with a warning if [subject] is null
     * and an assertion function tries to access it.
     *
     * @throws IllegalArgumentException in case the [createAssertions] function does not even create one [IAssertion].
     */
    fun <E : Any> collectAssertionsForExplanation(createAssertions: IAssertionPlant<E>.() -> Unit, subject: E?): List<IAssertion> {
        return try {
            val collectingAssertionPlant = AtriumFactory.newCollectingPlant {
                subject ?: throw ICollectingAssertionPlant.PlantHasNoSubjectException("the iterator was empty and thus no subject available")
            }
            collectingAssertionPlant.createAssertions()
            val collectedAssertions = collectingAssertionPlant.getAssertions()
            if (collectedAssertions.isEmpty()) throw IllegalArgumentException("There was not any assertion created which could identify an entry. Specify at least one assertion")
            collectedAssertions
        } catch (e: ICollectingAssertionPlant.PlantHasNoSubjectException) {
            listOf(ExplanatoryAssertionGroup(WarningAssertionGroupType, listOf(
                ExplanatoryAssertion(TranslatableRawString(DescriptionIterableAssertion.WARNING_SUBJECT_NOT_SET))
            )))
        }
    }
}
