package ch.tutteli.assertk

interface IAssertionFactoryNullable<out T : Any?> {
    val subject: T
    val assertionVerb: String
    fun isNull()
}
