package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.checking.IAssertionChecker
import ch.tutteli.assertk.reporting.RawString

class DownCastFluent<out T : Any, out TSub : T>(private val subClass: Class<TSub>,
                                                private val commonFields: IAssertionFactoryBase.CommonFields<T?>,
                                                private val assertion: IAssertion) {

    private var createAssertions: (IAssertionFactory<TSub>.() -> Unit)? = null
    private var nullRepresentation: String = RawString.NULL

    fun withNullRepresentation(representation: String): DownCastFluent<T, TSub> {
        nullRepresentation = representation
        return this
    }

    fun withLazyAssertions(assertions: IAssertionFactory<TSub>.() -> Unit): DownCastFluent<T, TSub> {
        createAssertions = assertions
        return this
    }

    fun cast(): IAssertionFactory<TSub> {
        val (assertionVerb, subject, assertionChecker) = commonFields
        if (assertion.holds()) {
            //needs to hold in order that cast can be performed
            val factory = if (createAssertions != null) {
                AssertionFactory.newCheckLazily(assertionVerb, subClass.cast(subject), assertionChecker)
            } else {
                AssertionFactory.newCheckImmediately(assertionVerb, subClass.cast(subject), assertionChecker)
            }
            factory.addAssertion(assertion)
            if (createAssertions != null) {
                createAssertions?.invoke(factory)
                factory.checkAssertions()
            }
            return factory
        }
        assertionChecker.fail(assertionVerb, subject ?: nullRepresentation, assertion)
        throw IllegalStateException("calling ${IAssertionChecker::class.java.simpleName}#${IAssertionChecker::fail.name} should throw an exception, ${assertionChecker::class.java.name} did not")
    }

    companion object {
        /**
         * Prepares a down cast, use the {@link DownCastFluent#cast} to perform it.
         */
        inline fun <reified TSub : T, T : Any> create(commonFields: IAssertionFactoryBase.CommonFields<T?>, assertion: IAssertion): DownCastFluent<T, TSub> {
            return DownCastFluent(TSub::class.java, commonFields, assertion)
        }
    }
}
