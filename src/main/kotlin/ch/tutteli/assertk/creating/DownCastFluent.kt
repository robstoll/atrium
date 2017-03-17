package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.checking.IAssertionChecker
import ch.tutteli.assertk.reporting.RawString

class DownCastFluent<out T : Any, out TSub : T>(private val subClass: Class<TSub>,
                                                private val commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>,
                                                private val assertion: IAssertion) {

    private var createAssertions: (IAssertionPlant<TSub>.() -> Unit)? = null
    private var nullRepresentation: String = RawString.NULL

    fun withNullRepresentation(representation: String): DownCastFluent<T, TSub> {
        nullRepresentation = representation
        return this
    }

    fun withLazyAssertions(assertions: IAssertionPlant<TSub>.() -> Unit): DownCastFluent<T, TSub> {
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
         * Prepares a down cast, use the {@link DownCastFluent#cast} to perform it.
         */
        inline fun <reified TSub : T, T : Any> create(commonFields: IAssertionPlantWithCommonFields.CommonFields<T?>, assertion: IAssertion): DownCastFluent<T, TSub> {
            return DownCastFluent(TSub::class.java, commonFields, assertion)
        }
    }
}
