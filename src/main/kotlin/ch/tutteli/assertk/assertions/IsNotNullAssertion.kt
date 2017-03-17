package ch.tutteli.assertk.assertions

import ch.tutteli.assertk.reporting.RawString

class IsNotNullAssertion(subject: Any?) : IOneMessageAssertion {
    override val message by lazy {
        Message(MESSAGE_DESCRIPTION, RawString.NULL, subject != null)
    }

    override fun toString() = message.toString()

    companion object {
        internal const val MESSAGE_DESCRIPTION = "is not"
    }
}
