package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

/**
 * A builder for creating a down-casting assertion.
 *
 * Or in other words, helps to make an assertion about [IAssertionPlant.subject] of type [T] that it can be
 * down-casted to [subType] of type [TSub].
 *
 * @param T The type of [IAssertionPlant.subject].
 * @param TSub The type to which [IAssertionPlant.subject] can be down-casted, hence needs to be a subtype of [T].
 *
 * @property description The description of this down-cast; will be used for the creation of the [IAssertion].
 * @property subType The resulting type of the down-cast.
 * @property subjectPlant The [IBaseAssertionPlant] to which assertions, made for the down-casted subject, will be
 * added.
 * @property createAssertions A function which is called after the down-cast has been performed in [cast]
 * using the newly created [IAssertionPlant] as receiver.
 *
 * @constructor A builder for creating a down-casting assertion.
 * @param description The description of this down-cast; will be used for the creation of the [IAssertion].
 * @param subType The resulting type of the down-cast.
 * @param subjectPlant The [IBaseAssertionPlant] to which assertions, made for the down-casted subject, will be
 * added.
 * @param createAssertions A function which is called after the down-cast has been performed in [cast]
 * using the newly created [IAssertionPlant] as receiver.
 */
class DownCastBuilder<out T : Any, out TSub : T>(
    private val description: ITranslatable,
    private val subType: KClass<TSub>,
    private val subjectPlant: IBaseAssertionPlant<T?, *>,
    private val createAssertions: IAssertionPlant<TSub>.() -> Unit
) : IDownCastBuilder<T, TSub> {

    /**
     * Performs the down-cast if possible; reports a failure otherwise.
     *
     * Down-Casting is possible if the [subjectPlant]'s [subject][IBaseAssertionPlant.subject] is not null and
     * its type is [subType] (or a sub-type of [subType]).
     *
     * In case the down-cast can be performed, it will create an [IAssertionPlant] and use the down-casted
     * subject as new subject of the newly created [IAssertionPlant]. Furthermore, it will add an [IAssertion]
     * (i.a., using the [description]) -- which represents the performed down-cast -- to the newly created
     * [IAssertionPlant].
     *
     * @return The newly created [IAssertionPlant] for the down-casted [subject][IAssertionPlant.subject] of
     * [subjectPlant] or an [ExplanatoryAssertionPlant] if the down-cast failed.
     *
     * @throws AssertionError Might throw an [AssertionError] depending on the [subjectPlant] in case the down-cast
     * cannot be performed or an additionally specified assertion (using [createAssertions]) does not hold.
     */
    override fun cast(): IAssertionPlant<TSub> {
        val subject = subjectPlant.subject
        val assertionVerb = Untranslatable("Should not be shown to the user; if you see this, please fill in a bug report at https://github.com/robstoll/atrium/issues/new")
        return if (subType.isInstance(subject)) {
            val assertionChecker = AtriumFactory.newDelegatingAssertionChecker(subjectPlant)
            val plant = AtriumFactory.newReportingPlantCheckLazily(assertionVerb, subType.cast(subject), assertionChecker)
            plant.addAssertion(BasicAssertion(description, subType, true))
            plant.createAssertions()
            plant.checkAssertions()
            plant
        } else {
            subjectPlant.addAssertion(BasicAssertion(description, subType, false))
            AtriumFactory.newExplanatoryPlant(subjectPlant, "subject is not defined since narrowing $subject to ${subType.qualifiedName} is not possible") { assertion ->
                ExplanatoryAssertionGroup(ExplanatoryAssertionGroupType, listOf(
                    AssertionGroup(ListAssertionGroupType, TranslatableWithArgs(Description.ASSERTIONS_NOT_CHECKED, subType.qualifiedName!!), RawString(""), listOf(assertion))
                ))
            }
        }
    }

    //TODO move to atrium-translations
    enum class Description(override val value: String) : ISimpleTranslatable {
        ASSERTIONS_NOT_CHECKED("the following assertions where not checked since it is not a %s")
    }
}
