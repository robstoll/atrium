package ch.tutteli.atrium.assertions.any.narrow

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.BasicDescriptiveAssertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

/**
 * Helps to make an assertion about the [BaseAssertionPlant.subject], that it is of type [T] and can be
 * down-casted to type [TSub].
 *
 * @param T The type of [BaseAssertionPlant.subject].
 * @param TSub The type to which [BaseAssertionPlant.subject] can be down-casted, hence needs to be a subtype of [T].
 *
 * @property failureHandler The handler which deals with a lambda function which could have created subsequent assertions
 *           for a down-casted subject.
 *
 * @constructor Helps to make an assertion about the [BaseAssertionPlant.subject], that it is of type [T] and can be
 *              down-casted to type [TSub].
 * @param failureHandler The handler which deals with a lambda function which could have created subsequent assertions
 *        for a down-casted subject.
 */
class DownCaster<T : Any, TSub : T>(private val failureHandler: AnyNarrow.DownCastFailureHandler<T, TSub>) {

    /**
     * Performs the down-cast and applies the given [assertionCreator] to the down-casted
     * [subject][BaseAssertionPlant.subject] of [subjectPlant] if successful or passes it to the [failureHandler]
     * otherwise.
     *
     * It also adds a [DescriptiveAssertion], representing the down-cast as such (succeeding or failing), to the given
     * [subjectPlant] using the given [description].
     *
     * @param description The [description][DescriptiveAssertion.description] of the resulting [DescriptiveAssertion].
     * @param subType The type to which the [subjectPlant]'s [subject][AssertionPlant.subject] should be down-casted.
     * @param subjectPlant The plant to which additional assertions will be added.
     * @param assertionCreator The lambda function which can create subsequent assertions for the down-casted subject.
     *
     * @throws AssertionError Might throw an [AssertionError] in case the down-cast cannot be performed, depending on
     *         the [subjectPlant] and the [failureHandler].
     */
    fun downCast(
        description: Translatable,
        subType: KClass<TSub>,
        subjectPlant: BaseAssertionPlant<T?, *>,
        assertionCreator: AssertionPlant<TSub>.() -> Unit
    ) {
        val subject = subjectPlant.subject
        val assertionVerb = Untranslatable("Should not be shown to the user; if you see this, please fill in a bug report at " + BUG_REPORT_URL)
        if (subType.isInstance(subject)) {
            val assertionChecker = AtriumFactory.newDelegatingAssertionChecker(subjectPlant)
            val plant = AtriumFactory.newReportingPlant(assertionVerb, subType.cast(subject), assertionChecker)
            plant.addAssertion(BasicDescriptiveAssertion(description, subType, true))
            plant.addAssertionsCreatedBy(assertionCreator)
        } else {
            failureHandler.createAndAddAssertionToPlant(subType, subjectPlant, BasicDescriptiveAssertion(description, subType, false), assertionCreator)
        }
    }
}
