@file:JvmName("AssertK")

package ch.tutteli.assertk

fun <T : Any> assert(subject: T): IAssertionFactory<T>
    = AssertionFactory.newCheckImmediately("assert", subject)

fun <T : Any?> assert(subject: T): IAssertionFactoryNullable<T>
    = AssertionFactory.newNullable("assert", subject)

inline fun <T : Any> assert(subject: T, createAssertions: IAssertionFactory<T>.() -> Unit)
    = createAndCheckAssertions("assert", subject, createAssertions)

fun expect(act: () -> Unit): ThrowableFluent {
    val objectFormatter = DetailedObjectFormatter()
    val assertionMessageFormatter = SameLineAssertionMessageFormatter(objectFormatter)
    val reporter = OnlyFailureReporter(assertionMessageFormatter)
    return ThrowableFluent.create("expect to throw", act, ThrowingAssertionChecker(reporter))
}

/**
 * Use this function to create custom 'assert' functions which lazy evaluate the given assertions.
 */
inline fun <T : Any> createAndCheckAssertions(assertionVerb: String, subject: T, createAssertions: IAssertionFactory<T>.() -> Unit)
    = createAndCheckAssertions(AssertionFactory.new(assertionVerb, subject), createAssertions)

inline fun <T : Any> createAndCheckAssertions(factory: IAssertionFactory<T>, createAssertions: IAssertionFactory<T>.() -> Unit): IAssertionFactory<T> {
    factory.createAssertions()
    factory.checkAssertions()
    return factory
}

fun <T : Any> IAssertionFactoryNullable<T?>.isNotNull(createAssertions: IAssertionFactory<T>.() -> Unit)
    = isNotNull(this, { createAndCheckAssertions(assertionVerb, subject!!, createAssertions) })

fun <T : Any> IAssertionFactoryNullable<T?>.isNotNull()
    = isNotNull(this, { AssertionFactory.newCheckImmediately(assertionVerb, subject!!) })

private fun <T : Any> isNotNull(factoryNullable: IAssertionFactoryNullable<T?>, factory: () -> IAssertionFactory<T>): IAssertionFactory<T> {
    if (factoryNullable.subject != null) {
        return factory()
    } else {
        factoryNullable.assertionChecker.failWithCustomSubject(factoryNullable.assertionVerb, "null", DescriptionExpectedAssertion("is not", "null", { false }))
        throw IllegalStateException("calling ${IAssertionChecker::class.java.simpleName}#${IAssertionChecker::failWithCustomSubject.name} should throw an exception")
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
