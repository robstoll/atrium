package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.OneMessageAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.reporting.RawString
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

/**
 * A builder for down-casting assertions.
 */
class DownCastBuilder<T : Any, TSub : T>(private val description: String,
                                         private val subClass: KClass<TSub>,
                                         private val commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>
) {

    private var createAssertions: (IAssertionPlant<TSub>.() -> Unit)? = null
    private var nullRepresentation: String = RawString.NULL

    /**
     * Use this method if you want to use your own `null` representation (default is [RawString.NULL]).
     */
    fun withNullRepresentation(representation: String): DownCastBuilder<T, TSub> {
        nullRepresentation = representation
        return this
    }

    /**
     * Use this method if you want to add several assertions which are checked lazily after the down cast is performed.
     */
    fun withLazyAssertions(assertions: IAssertionPlant<TSub>.() -> Unit): DownCastBuilder<T, TSub> {
        createAssertions = assertions
        return this
    }

    /**
     * Performs the down-cast if possible; reports a failure otherwise.
     *
     * Down-Casting is possible if [commonFields]'s [IAssertionPlantWithCommonFields.CommonFields.subject]
     * is not null and its type is [subClass] (or a sub-class).
     *
     * @throws AssertionError in case the down-cast cannot be performed
     *         or an additionally specified assertion (using [withLazyAssertions]) does not hold.
     * @throws IllegalStateException in case reporting a failure does not throw an [Exception].
     */
    fun cast(): IAssertionPlant<TSub> {
        val (assertionVerb, subject, assertionChecker) = commonFields
        if (subClass.isInstance(subject)) {
            val plant = if (createAssertions != null) {
                AtriumFactory.newCheckLazily(assertionVerb, subClass.cast(subject), assertionChecker)
            } else {
                AtriumFactory.newCheckImmediately(assertionVerb, subClass.cast(subject), assertionChecker)
            }
            plant.addAssertion(OneMessageAssertion(description, subClass, true))
            if (createAssertions != null) {
                createAssertions?.invoke(plant)
                plant.checkAssertions()
            }
            return plant
        }
        assertionChecker.fail(assertionVerb, subject ?: nullRepresentation, OneMessageAssertion(description, subClass, false))
        throw IllegalStateException("calling ${IAssertionChecker::class.java.simpleName}#${IAssertionChecker::fail.name} should throw an exception, ${assertionChecker::class.java.name} did not")
    }
}
