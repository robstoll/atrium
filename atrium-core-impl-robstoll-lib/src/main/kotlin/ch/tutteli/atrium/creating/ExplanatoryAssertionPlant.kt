package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroup
import ch.tutteli.atrium.assertions.IAssertion

class ExplanatoryAssertionPlant<out T : Any>(
    private val subjectPlant: IBaseAssertionPlant<*, *>,
    private val reasonWhyNoSubject: String,
    private val explanatoryGroupFactory: (IAssertion) -> ExplanatoryAssertionGroup
) : IAssertionPlant<T> {
    override val subject: T get() = throw PlantHasNoSubjectException(reasonWhyNoSubject)

    override fun addAssertion(assertion: IAssertion): IAssertionPlant<T> {
        subjectPlant.addAssertion(explanatoryGroupFactory(assertion))
        return this
    }
}
