package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionBasic

class AssertionCollectorForExplanationImpl<T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> (
    private val throwIfNoAssertionIsCollected: Boolean,
    private val collectingPlantFactory: (() -> T) -> C
) {

    fun collect(
        warningCannotEvaluate: Translatable,
        subject: MaybeSubject<T>,
        assertionCreator: (C.() -> Unit)?
    ): List<Assertion> {
        return try {
            val collectedAssertions = collect(subject, assertionCreator)

            //TODO change to check in v. 1.0.0
            require(!(throwIfNoAssertionIsCollected && collectedAssertions.isEmpty())) {
                "There was not any assertion created. Specify at least one assertion"
            }

            // since assertions can be lazily computed we have to provoke their creation here,
            // so that a potential PlantHasNoSubjectException is thrown. It's fine to provoke the computation
            // because we require the assertions for the explanation anyway.
            expandAssertionGroups(collectedAssertions)

            collectedAssertions
        } catch (e: PlantHasNoSubjectException) {
            listOf(
                AssertImpl.builder.explanatoryGroup
                    .withWarningType
                    .withExplanatoryAssertion(warningCannotEvaluate)
                    .build()
            )
        }
    }

    private fun collect(subject: MaybeSubject<T>, assertionCreator: (C.() -> Unit)?): List<Assertion> {
        //TODO almost same as in _containsKeyWithNullableValueAssertions
        return if (assertionCreator != null) {
            val collectingAssertionPlant = collectingPlantFactory(subject::get)
            collectingAssertionPlant.assertionCreator()
            collectingAssertionPlant.getAssertions()
        } else {
            listOf(AssertImpl.builder.createDescriptive(DescriptionBasic.IS, RawString.NULL) {
                subject is MaybeSubject.Absent
            })
        }
    }
}
