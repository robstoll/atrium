package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KProperty0


fun <T : Any> IAssertionPlant<T>.genericCheck(feature: KProperty0<Boolean>)
    = createAndAddAssertion("generic check ${feature.name}", true, { feature.get() })

fun <T : Any> IAssertionPlant<T>.toBe(expected: T)
    = createAndAddAssertion("to be", expected, { subject == expected })
