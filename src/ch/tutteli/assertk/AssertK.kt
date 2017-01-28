@file:JvmName("AssertK")

package ch.tutteli.assertk

fun <T : Any> assert(subject: T): IAssertionFactory<T>
    = AssertionFactory.newCheckImmediately("assert", subject)

fun <T : Any> IAssertionFactory<T>.toBe(expected: T)
    = createAndAddAssertion("to be", expected, { subject == expected })

fun IAssertionFactory<String>.contains(expected: String)
    = createAndAddAssertion("contains", expected, { subject.contains(expected) })

fun IAssertionFactory<String>.isEmpty()
    = createAndAddAssertion("is", "empty", { subject.isEmpty() })
