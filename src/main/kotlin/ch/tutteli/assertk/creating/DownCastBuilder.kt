package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.checking.IAssertionChecker
import ch.tutteli.assertk.reporting.RawString

/**
 * A builder for down-casting assertions.
 */
class DownCastBuilder<out T : Any, out TSub : T>(private val subClass: Class<TSub>,
                                                 private val commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>,
                                                 private val assertion: IAssertion) {

    private var createAssertions: (IAssertionPlant<TSub>.() -> Unit)? = null
    private var nullRepresentation: String = RawString.NULL

    /**
     * Use this method if you want to use your own `null` representation (default is [RawString.NULL])
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

    fun cast(): IAssertionPlant<TSub> {
        val (assertionVerb, subject, assertionChecker) = commonFields
        if (assertion.holds()) {
            //needs to hold in order that cast can be performed
            val plant = if (createAssertions != null) {
                AssertionPlantFactory.newCheckLazily(assertionVerb, subClass.cast(subject), assertionChecker)
            } else {
                AssertionPlantFactory.newCheckImmediately(assertionVerb, subClass.cast(subject), assertionChecker)
            }
            plant.addAssertion(assertion)
            if (createAssertions != null) {
                createAssertions?.invoke(plant)
                plant.checkAssertions()
            }
            return plant
        }
        assertionChecker.fail(assertionVerb, subject ?: nullRepresentation, assertion)
        throw IllegalStateException("calling ${IAssertionChecker::class.java.simpleName}#${IAssertionChecker::fail.name} should throw an exception, ${assertionChecker::class.java.name} did not")
    }

    companion object {
        /**
         * Prepares a down cast; use [cast] to perform it and first call [withLazyAssertions]/[withNullRepresentation] to specialise it further.
         */
        inline fun <reified TSub : T, T : Any> create(commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>, assertion: IAssertion): DownCastBuilder<T, TSub> {
            return DownCastBuilder(TSub::class.java, commonFields, assertion)
        }
    }
}
