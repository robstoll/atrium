package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.reporting.IRawString
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
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
 * @property commonFields The [IAssertionPlantWithCommonFields.CommonFields] of the
 *        [IAssertionPlant]/[IAssertionPlantNullable] which uses this [DownCastBuilder].
 *        The down-cast will be performed on its [subject][IAssertionPlant.subject].
 *        Moreover, the containing information will inter alia be used in reporting.
 *
 * @constructor
 * @param description The description of this down-cast; will be used for the creation of the [IAssertion].
 * @param subType The resulting type of the down-cast.
 * @param commonFields The [IAssertionPlantWithCommonFields.CommonFields] of the
 *        [IAssertionPlant]/[IAssertionPlantNullable] which uses this [DownCastBuilder].
 *        The down-cast will be performed on its [subject][IAssertionPlant.subject].
 *        Moreover, the containing information will inter alia be used in reporting.
 */
class DownCastBuilder<T : Any, TSub : T>(
    private val description: ITranslatable,
    private val subType: KClass<TSub>,
    private val commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>
) : IDownCastBuilder<T, TSub> {

    /**
     * The nullable function which will be called (if not null) to create additional assertions in [cast].
     */
    private var createAssertions: (IAssertionPlant<TSub>.() -> Unit)? = null
    /**
     * The null representation which defaults to [RawString.NULL].
     */
    private var nullRepresentation: IRawString = RawString.NULL

    override fun withNullRepresentation(representation: String): IDownCastBuilder<T, TSub> {
        nullRepresentation = RawString(representation)
        return this
    }

    override fun withNullRepresentation(translatableRepresentation: ITranslatable): IDownCastBuilder<T, TSub> {
        nullRepresentation = TranslatableRawString(translatableRepresentation)
        return this
    }

    override fun withLazyAssertions(createAssertions: IAssertionPlant<TSub>.() -> Unit): IDownCastBuilder<T, TSub> {
        this.createAssertions = createAssertions
        return this
    }

    /**
     * Performs the down-cast if possible; reports a failure otherwise.
     *
     * Down-Casting is possible if [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
     * is not null and its type is [subType] (or a sub-type of [subType]).
     *
     * In case the down-cast can be performed, it will create an [IAssertionPlant] and use the down-casted
     * [subject][IAssertionPlantWithCommonFields.CommonFields.subject] of [commonFields] as [IAssertionPlant.subject]
     * of the newly created [IAssertionPlant]. Furthermore, it will add an [IAssertion] (i.a., using the [description])
     * -- which represents the performed down-cast -- to the newly created [IAssertionPlant].
     *
     * @return The newly created [IAssertionPlant] for the down-casted
     *         [subject][IAssertionPlantWithCommonFields.CommonFields.subject] of [commonFields].
     *
     * @throws AssertionError In case the down-cast cannot be performed
     *         or an additionally specified assertion (using [withLazyAssertions]) does not hold.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */

    override fun cast(): IAssertionPlant<TSub> {
        val (assertionVerb, subject, assertionChecker) = commonFields
        if (subType.isInstance(subject)) {
            val plant = if (createAssertions != null) {
                AtriumFactory.newCheckLazily(assertionVerb, subType.cast(subject), assertionChecker)
            } else {
                AtriumFactory.newCheckImmediately(assertionVerb, subType.cast(subject), assertionChecker)
            }
            plant.addAssertion(BasicAssertion(description, subType, true))
            if (createAssertions != null) {
                createAssertions?.invoke(plant)
                plant.checkAssertions()
            }
            return plant
        }
        assertionChecker.fail(assertionVerb, subject ?: nullRepresentation, BasicAssertion(description, subType, false))
        throw IllegalStateException("calling ${IAssertionChecker::class.java.simpleName}#${IAssertionChecker::fail.name} should throw an exception, ${assertionChecker::class.java.name} did not")
    }
}
