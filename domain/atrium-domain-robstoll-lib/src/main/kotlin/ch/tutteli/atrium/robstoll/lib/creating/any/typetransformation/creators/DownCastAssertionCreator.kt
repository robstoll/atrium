package ch.tutteli.atrium.robstoll.lib.creating.any.typetransformation.creators

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionTypeTransformationAssertion
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

/**
 * Helps to make an assertion about the [BaseAssertionPlant.subject], that it is of type [T] and can be
 * down-casted to type [TSub].
 *
 * @param T The type of [BaseAssertionPlant.subject].
 * @param TSub The type to which [BaseAssertionPlant.subject] can be down-casted, hence needs to be a subtype of [T].
 */
class DownCastAssertionCreator<T : Any, TSub : T> {
    private val creator = TypeTransformationAssertionCreator<T, TSub>()

    /**
     * Performs the down-cast and applies the given [assertionCreator] to the down-casted
     * [subject][BaseAssertionPlant.subject] of [subjectPlant] if successful or passes it
     * to a [AnyTypeTransformation.FailureHandler] otherwise.
     *
     * It also adds a [DescriptiveAssertion], representing the down-cast as such (succeeding or failing), to the given
     * [subjectPlant] using the given [description].
     *
     * @param description The [description][DescriptiveAssertion.description] of the resulting [DescriptiveAssertion].
     * @param subType The type to which the [subjectPlant]'s [subject][AssertionPlant.subject] should be down-casted.
     * @param subjectPlant The plant to which additional assertions will be added.
     * @param assertionCreator The lambda function which can create subsequent assertions for the down-casted subject.
     * @param failureHandler The handler which deals with a lambda function which could have created subsequent assertions
     *   for a down-casted subject.
     *
     * @throws AssertionError Might throw an [AssertionError] in case the down-cast cannot be performed, depending on
     *   the [subjectPlant] and the defined [AnyTypeTransformation.FailureHandler].
     */
    fun downCast(
        description: Translatable,
        subType: KClass<TSub>,
        subjectPlant: BaseAssertionPlant<T?, *>,
        assertionCreator: AssertionPlant<TSub>.() -> Unit,
        failureHandler: AnyTypeTransformation.FailureHandler<T, TSub>
    ) {
        val warningTransformationFailed = TranslatableWithArgs(
            DescriptionTypeTransformationAssertion.WARNING_DOWN_CAST_FAILED,
            subType.java.name
        )
        val parameterObject = AnyTypeTransformation.ParameterObject(
            description, subType, subjectPlant, assertionCreator, warningTransformationFailed
        )
        creator.create(
            parameterObject,
            { subType.isInstance(it) },
            { subType.cast(it) },
            failureHandler
        )
    }
}
