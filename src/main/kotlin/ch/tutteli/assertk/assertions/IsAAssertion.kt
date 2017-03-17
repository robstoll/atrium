package ch.tutteli.assertk.assertions

class IsAAssertion<T>(val subject: Any, clazz: Class<T>) : IOneMessageAssertion {
    override val message by lazy {
        Message(MESSAGE_DESCRIPTION, clazz, clazz.isAssignableFrom(subject::class.java))
    }

    companion object {
        internal const val MESSAGE_DESCRIPTION = "is type or sub-type of"
    }
}
