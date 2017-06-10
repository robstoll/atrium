package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import kotlin.reflect.*

/**
 * Use this function to make the assertion that retrieving the given [property]
 * of the [IAssertionPlant.subject] returns `true`.
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : Any> IAssertionPlant<T>.genericCheck(property: KProperty0<Boolean>)
    = createAndAddAssertion(translatableWithArgs(property), true, { property.get() })

/**
 * Use this function to make the assertion that retrieving the given [property]
 * of the [IAssertionPlant.subject] returns `false`.
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : Any> IAssertionPlant<T>.genericNotCheck(property: KProperty0<Boolean>)
    = createAndAddAssertion(translatableWithArgs(property), false, { !property.get() })

private fun translatableWithArgs(property: KProperty<Boolean>)
    = TranslatableWithArgs(DescriptionGenericAssertion.GENERIC_CHECK, property.name)

enum class DescriptionGenericAssertion(override val value: String) : ISimpleTranslatable {
    GENERIC_CHECK("property %s is"),
}
