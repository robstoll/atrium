package ch.tutteli.assertk

interface IAssertionFactoryNullable<out T : Any?> {
    val subject: T
    val assertionVerb: String
    val assertionChecker: IAssertionChecker
    fun isNull()
}
