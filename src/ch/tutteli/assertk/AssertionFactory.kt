package ch.tutteli.assertk

import java.util.*

open class AssertionFactory<out T : Any> private constructor(
    override final val assertionVerb: String,
    override final val subject: T) : IAssertionFactory<T> {

    private val asserts: MutableList<IAssertion> = ArrayList()

    override final fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean)
        = createAndAddAssertion(description, objectFormatter.format(expected), test)

    override final fun createAndAddAssertion(description: String, expected: String, test: () -> Boolean)
        = addAssertion(DescriptionExpectedAssertion(description, expected, test))

    override fun addAssertion(assertion: IAssertion): IAssertionFactory<T> {
        asserts.add(assertion)
        return this
    }

    override final fun checkAssertions() {
        val messages = ArrayList<Pair<String, String>>()
        asserts.flatten { it.messages() }
            .filter { !it.holds }
            .forEach {
                messages.add(it.description to objectFormatter.format(it.representation))
            }
        asserts.clear()
        if (messages.isNotEmpty()) {
            fail(assertionVerb, subject, messages)
        }
    }

    companion object {
        var objectFormatter: IObjectFormatter = DetailedObjectFormatter()
        var assertionMessageFormatter: IAssertionMessageFormatter = SameLineAssertionMessageFormatter()

        fun <T : Any> new(assertionVerb: String, subject: T): IAssertionFactory<T>
            = AssertionFactory(assertionVerb, subject)

        fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T): IAssertionFactory<T>
            = ImmediateCheckAssertionFactory(assertionVerb, subject)

        fun <T : Any?> newNullable(assertionVerb: String, subject: T): IAssertionFactoryNullable<T>
            = AssertionFactoryNullable(assertionVerb, subject)

        fun <T : Any> fail(assertionVerb: String, subject: T, messages: List<Pair<String, String>>): Nothing
            = failWithCustomSubject(assertionVerb, objectFormatter.format(subject), messages)

        fun failWithCustomSubject(assertionVerb: String, subject: String, messages: List<Pair<String, String>>): Nothing {
            val messagesWithAssert = ArrayList<Pair<String, String>>(messages.size + 1)
            messagesWithAssert.add(assertionVerb to subject)
            messagesWithAssert.addAll(messages)
            val formattedMessages = assertionMessageFormatter.format(messagesWithAssert)
            throw AssertionError(formattedMessages)
        }
    }

    private class ImmediateCheckAssertionFactory<out T : Any> internal constructor(
        assertionVerb: String, subject: T) : AssertionFactory<T>(assertionVerb, subject) {

        override fun addAssertion(assertion: IAssertion): AssertionFactory<T> {
            super.addAssertion(assertion)
            checkAssertions()
            return this
        }
    }

    private class AssertionFactoryNullable<out T : Any?> internal constructor(
        override val assertionVerb: String,
        override val subject: T) : IAssertionFactoryNullable<T> {

        override fun isNull() {
            if (subject != null) {
                AssertionFactory.fail(assertionVerb, objectFormatter.format(subject), listOf("to be" to "null"))
            }
        }
    }
}
