package ch.tutteli.assertk

import ch.tutteli.assertk.IAssertionChecker

interface IAssertionFactoryNullable<out T : Any?> {
    val subject: T
    val assertionVerb: String
    val assertionChecker: IAssertionChecker
    fun isNull()
}
