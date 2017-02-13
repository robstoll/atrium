@file:JvmName("AssertK")

package ch.tutteli.assertk

fun <T : Any> assert(subject: T): IAssertionFactory<T>
    = AssertionFactory.newCheckImmediately("assert", subject)

fun <T : Any?> assert(subject: T): IAssertionFactoryNullable<T>
    = AssertionFactory.newNullable("assert", subject)

inline fun <T : Any> assert(subject: T, createAssertions: IAssertionFactory<T>.() -> Unit)
    = createAndCheckAssertions("assert", subject, createAssertions)

fun expect(act: () -> Unit)
    = ThrowableFluent.create("expect to throw", act)

/**
 * Use this function to create custom 'expect' functions which lazy evaluate the given assertions.
 */
inline fun <T : Any> createAndCheckAssertions(assertionVerb: String, subject: T, createAssertions: IAssertionFactory<T>.() -> Unit) {
    val factory = AssertionFactory.new(assertionVerb, subject)
    factory.createAssertions()
    factory.checkAssertions()
}

fun <T : Any> IAssertionFactoryNullable<T?>.isNotNull(createAssertions: IAssertionFactory<T>.() -> Unit) {
    if (subject != null) {
        createAndCheckAssertions(assertionVerb, subject!!, createAssertions)
    } else {
        AssertionFactory.failWithCustomSubject(assertionVerb, "null", listOf("is not" to "null"))
    }
}

fun <T : Any> IAssertionFactoryNullable<T?>.isNotNull(): IAssertionFactory<T> {
    if (subject != null) {
        return AssertionFactory.newCheckImmediately(assertionVerb, subject!!)
    } else {
        AssertionFactory.failWithCustomSubject(assertionVerb, "null", listOf("is not" to "null"))
    }
}

fun <T : Any> IAssertionFactory<T>.toBe(expected: T)
    = createAndAddAssertion("to be", expected, { subject == expected })

fun IAssertionFactory<Int>.isSmallerThan(expected: Int)
    = createAndAddAssertion("is smaller than", expected, { subject < expected })

fun IAssertionFactory<Int>.isSmallerOrEquals(expected: Int)
    = createAndAddAssertion("is smaller or equals", expected, { subject <= expected })

fun IAssertionFactory<Int>.isGreaterThan(expected: Int)
    = createAndAddAssertion("is greater than", expected, { subject > expected })

fun IAssertionFactory<Int>.isGreaterOrEquals(expected: Int)
    = createAndAddAssertion("is greater or equals", expected, { subject >= expected })

fun IAssertionFactory<CharSequence>.contains(expected: CharSequence)
    = createAndAddAssertion("contains", expected, { subject.contains(expected) })

fun IAssertionFactory<CharSequence>.contains(expected: CharSequence, vararg otherExpected: CharSequence): IAssertionFactory<CharSequence> {
    val factory = contains(expected)
    otherExpected.forEach { contains(it) }
    return factory
}

fun IAssertionFactory<CharSequence>.startsWith(expected: CharSequence)
    = createAndAddAssertion("starts with", expected, { subject.startsWith(expected) })

fun IAssertionFactory<CharSequence>.endsWith(expected: CharSequence)
    = createAndAddAssertion("ends with", expected, { subject.endsWith(expected) })

fun IAssertionFactory<CharSequence>.isEmpty()
    = createAndAddAssertion("is", "empty", { subject.isEmpty() })
