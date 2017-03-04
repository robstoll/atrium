package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.OneMessageAssertion
import java.util.*

open class AssertionFactory<out T : Any> private constructor(
    override final val assertionVerb: String,
    override final val subject: T,
    override final val assertionChecker: IAssertionChecker) : IAssertionFactory<T> {

    private val assertions: MutableList<IAssertion> = ArrayList()

    override final fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean)
        = addAssertion(OneMessageAssertion(description, expected, test))

    override fun addAssertion(assertion: IAssertion): IAssertionFactory<T> {
        assertions.add(assertion)
        return this
    }

    override final fun checkAssertions() {
        try {
            assertionChecker.check(assertionVerb, subject, assertions)
        } finally {
            assertions.clear()
        }
    }

    companion object {
        var objectFormatter: IObjectFormatter = DetailedObjectFormatter()
        var assertionMessageFormatter: IAssertionMessageFormatter = SameLineAssertionMessageFormatter(objectFormatter)
        var reporter: IReporter = OnlyFailureReporter(assertionMessageFormatter)
        var assertionChecker: IAssertionChecker = ThrowingAssertionChecker(reporter)

        fun <T : Any> new(assertionVerb: String, subject: T): IAssertionFactory<T>
            = new(assertionVerb, subject, assertionChecker)

        fun <T : Any> new(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionFactory<T>
            = AssertionFactory(assertionVerb, subject, assertionChecker)

        fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T): IAssertionFactory<T>
            = newCheckImmediately(assertionVerb, subject, assertionChecker)

        fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionFactory<T>
            = ImmediateCheckAssertionFactory(assertionVerb, subject, assertionChecker)

        fun <T : Any?> newNullable(assertionVerb: String, subject: T): IAssertionFactoryNullable<T>
            = AssertionFactoryNullable(assertionVerb, subject, assertionChecker)

        fun <T : Any?> newNullable(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionFactoryNullable<T>
            = AssertionFactoryNullable(assertionVerb, subject, assertionChecker)
    }

    private class ImmediateCheckAssertionFactory<out T : Any> internal constructor(
        assertionVerb: String, subject: T, assertionChecker: IAssertionChecker) : AssertionFactory<T>(assertionVerb, subject, assertionChecker) {

        override fun addAssertion(assertion: IAssertion): AssertionFactory<T> {
            super.addAssertion(assertion)
            checkAssertions()
            return this
        }
    }

    private class AssertionFactoryNullable<out T : Any?> internal constructor(
        override val assertionVerb: String,
        override val subject: T,
        override val assertionChecker: IAssertionChecker) : IAssertionFactoryNullable<T> {

        override fun isNull() {
            if (subject != null) {
                assertionChecker.fail(assertionVerb, subject, OneMessageAssertion("to be", RawString.NULL, false))
            }
        }
    }
}
