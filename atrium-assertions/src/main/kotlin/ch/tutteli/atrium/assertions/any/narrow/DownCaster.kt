package ch.tutteli.atrium.assertions.any.narrow

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IBaseAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

/**
 * Helps to make an assertion about the [IBaseAssertionPlant.subject], that it is of type [T] and can be
 * down-casted to type [TSub].
 *
 * @param T The type of [IBaseAssertionPlant.subject].
 * @param TSub The type to which [IBaseAssertionPlant.subject] can be down-casted, hence needs to be a subtype of [T].
 *
 * @property failureHandler The handler which deals with a lambda function which could have created subsequent assertions
 *           for a down-casted subject.
 *
 * @constructor Helps to make an assertion about the [IBaseAssertionPlant.subject], that it is of type [T] and can be
 *              down-casted to type [TSub].
 * @param failureHandler The handler which deals with a lambda function which could have created subsequent assertions
 *        for a down-casted subject.
 */
class DownCaster<T : Any, TSub : T>(private val failureHandler: IAnyNarrow.IDownCastFailureHandler<T, TSub>) {

    /**
     * Performs the down-cast and applies the given [assertionCreator] to the down-casted
     * [subject][IBaseAssertionPlant.subject] of [subjectPlant] if successful or passes it to the [failureHandler]
     * otherwise.
     *
     * It also adds an [IBasicAssertion], representing the down-cast as such (succeeding or failing), to the given
     * [subjectPlant] using the given [description].
     *
     * @param description The [description][IBasicAssertion.description] of the resulting [IBasicAssertion].
     * @param subType The type to which the [subjectPlant]'s [subject][IAssertionPlant.subject] should be down-casted.
     * @param subjectPlant The plant to which additional assertions will be added.
     * @param assertionCreator The lambda function which can create subsequent assertions for the down-casted subject.
     *
     * @throws AssertionError Might throw an [AssertionError] in case the down-cast cannot be performed, depending on
     *         the [subjectPlant] and the [failureHandler].
     */
    fun downCast(
        description: ITranslatable,
        subType: KClass<TSub>,
        subjectPlant: IBaseAssertionPlant<T?, *>,
        assertionCreator: IAssertionPlant<TSub>.() -> Unit
    ) {
        val subject = subjectPlant.subject
        val assertionVerb = Untranslatable("Should not be shown to the user; if you see this, please fill in a bug report at https://github.com/robstoll/atrium/issues/new")
        if (subType.isInstance(subject)) {
            val assertionChecker = AtriumFactory.newDelegatingAssertionChecker(subjectPlant)
            val plant = AtriumFactory.newReportingPlant(assertionVerb, subType.cast(subject), assertionChecker)
            plant.addAssertion(BasicAssertion(description, subType, true))
            plant.addAssertionsCreatedBy(assertionCreator)
        } else {
            failureHandler.createAndAddAssertionToPlant(subType, subjectPlant, BasicAssertion(description, subType, false), assertionCreator)
        }
    }
}
