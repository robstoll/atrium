package ch.tutteli.atrium.creating

class CollectingAssertionPlant<out T : Any> : BaseAssertionPlant<T>(), ICollectingAssertionPlant<T> {
    override val subject: T
        get() = throw UnsupportedOperationException(
            "${javaClass.simpleName}'s purpose is to collect added assertions and not for something else."
        )
}
