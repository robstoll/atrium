package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant


fun <T : Collection<*>> IAssertionPlant<T>.hasSize(size: Int)
    = createAndAddAssertion("has size", size, { subject.size == size })

