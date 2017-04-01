package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString


fun <T : CharSequence> IAssertionPlant<T>.contains(expected: CharSequence)
    = createAndAddAssertion("contains", expected, { subject.contains(expected) })

fun <T : CharSequence> IAssertionPlant<T>.contains(expected: CharSequence, vararg otherExpected: CharSequence): IAssertionPlant<T> {
    val plant = contains(expected)
    otherExpected.forEach { contains(it) }
    return plant
}

fun <T : CharSequence> IAssertionPlant<T>.contains(expected: Any, vararg otherExpected: Any): IAssertionPlant<T> {
    val plant = contains(expected.toString())
    otherExpected.forEach { contains(it.toString()) }
    return plant
}

fun <T : CharSequence> IAssertionPlant<T>.startsWith(expected: CharSequence)
    = createAndAddAssertion("starts with", expected, { subject.startsWith(expected) })

fun <T : CharSequence> IAssertionPlant<T>.endsWith(expected: CharSequence)
    = createAndAddAssertion("ends with", expected, { subject.endsWith(expected) })

fun <T : CharSequence> IAssertionPlant<T>.isEmpty()
    = createAndAddAssertion("is", RawString("empty"), { subject.isEmpty() })
