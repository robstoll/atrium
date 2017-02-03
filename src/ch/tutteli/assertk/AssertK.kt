@file:JvmName("AssertK")

package ch.tutteli.assertk

fun <T : Any> assert(subject: T): IAssertionFactory<T>
    = AssertionFactory.newCheckImmediately("assert", subject)

fun <T : Any?> assert(subject: T): IAssertionFactoryNullable<T>
    = AssertionFactory.newNullable("assert", subject)

fun expect(act: () -> Unit)
    = ThrowableFluent.create("expect to throw", act)

fun <T : Any> IAssertionFactoryNullable<T?>.isNotNull(): IAssertionFactory<T> {
    if (subject != null) {
        return AssertionFactory.newCheckImmediately(assertionVerb, subject!!)
    } else {
        AssertionFactory.failWithCustomSubject(assertionVerb, "null", listOf("is not" to "null"))
    }
}

fun <T : Any> IAssertionFactory<T>.toBe(expected: T)
    = createAndAddAssertion("to be", expected, { subject == expected })

fun IAssertionFactory<CharSequence>.contains(expected: CharSequence)
    = createAndAddAssertion("contains", expected, { subject.contains(expected) })

fun IAssertionFactory<CharSequence>.contains(expected: CharSequence, vararg otherExpected: CharSequence): IAssertionFactory<CharSequence> {
    val factory = contains(expected)
    otherExpected.forEach { contains(it) }
    return factory
}

fun IAssertionFactory<CharSequence>.isEmpty()
    = createAndAddAssertion("is", "empty", { subject.isEmpty() })
