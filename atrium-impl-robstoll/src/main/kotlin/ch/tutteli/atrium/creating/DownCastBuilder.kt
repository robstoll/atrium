package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.OneMessageAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.reporting.IRawString
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

/**
 * A builder for creating a down-casting assertion.
 *
 * Or in other words, helps to make an assertion about [IAssertionPlant.subject] that it can be
 * down-casted to [subType].
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
class DownCastBuilder<T : Any, TSub : T>(private val description: ITranslatable,
                                         private val subType: KClass<TSub>,
                                         private val commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>
) {

    /**
     * The nullable function which will be called (if not null) to create additional assertions in [cast].
     */
    private var createAssertions: (IAssertionPlant<TSub>.() -> Unit)? = null
    /**
     *
     */
    private var nullRepresentation: IRawString = RawString.NULL

    /**
     * Use this method if you want to use your own `null` representation in error reporting
     * (default is [RawString.NULL]).
     *
     * @param representation The representation for `null`.
     *
     * @return This builder to support a fluent-style API.
     */
    fun withNullRepresentation(representation: String): DownCastBuilder<T, TSub> {
        nullRepresentation = RawString(representation)
        return this
    }

    /**
     * Use this method if you want to use your own `null` representation in error reporting
     * (default is [RawString.NULL]).
     *
     * @param translatableRepresentation A translatable representation for `null`.
     *
     * @return This builder to support a fluent-style API.
     */
    fun withNullRepresentation(translatableRepresentation: ITranslatable): DownCastBuilder<T, TSub> {
        nullRepresentation = TranslatableRawString(translatableRepresentation)
        return this
    }

    /**
     * Use this method if you want to add several assertions which are checked lazily after the down cast is performed.
     *
     * Or in other words, the given [createAssertions] function will be called,
     * which might add additional [IAssertion]s to the newly created [IAssertionPlant], which are then lazily checked
     * in [cast] after the down-cast was performed and before the newly created [IAssertionPlant] is returned.
     *
     * @param createAssertions A function which is called after the down-cast has been performed in [cast]
     *        using the newly created [IAssertionPlant] as receiver.
     *
     * @return This builder to support a fluent-style API.
     */
    fun withLazyAssertions(createAssertions: IAssertionPlant<TSub>.() -> Unit): DownCastBuilder<T, TSub> {
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

    fun cast(): IAssertionPlant<TSub> {
        val (assertionVerb, subject, assertionChecker) = commonFields
        if (subType.isInstance(subject)) {
            val plant = if (createAssertions != null) {
                AtriumFactory.newCheckLazily(assertionVerb, subType.cast(subject), assertionChecker)
            } else {
                AtriumFactory.newCheckImmediately(assertionVerb, subType.cast(subject), assertionChecker)
            }
            plant.addAssertion(OneMessageAssertion(description, subType, true))
            if (createAssertions != null) {
                createAssertions?.invoke(plant)
                plant.checkAssertions()
            }
            return plant
        }
        assertionChecker.fail(assertionVerb, subject ?: nullRepresentation, OneMessageAssertion(description, subType, false))
        throw IllegalStateException("calling ${IAssertionChecker::class.java.simpleName}#${IAssertionChecker::fail.name} should throw an exception, ${assertionChecker::class.java.name} did not")
    }
}
