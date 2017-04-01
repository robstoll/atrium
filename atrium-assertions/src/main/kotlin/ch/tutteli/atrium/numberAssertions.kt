package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant


fun IAssertionPlant<Int>.isSmallerThan(expected: Int)
    = createAndAddAssertion("is smaller than", expected, { subject < expected })

fun IAssertionPlant<Int>.isSmallerOrEquals(expected: Int)
    = createAndAddAssertion("is smaller or equals", expected, { subject <= expected })

fun IAssertionPlant<Int>.isGreaterThan(expected: Int)
    = createAndAddAssertion("is greater than", expected, { subject > expected })

fun IAssertionPlant<Int>.isGreaterOrEquals(expected: Int)
    = createAndAddAssertion("is greater or equals", expected, { subject >= expected })
