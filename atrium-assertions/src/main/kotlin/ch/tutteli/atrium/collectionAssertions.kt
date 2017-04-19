package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionCollectionAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.ISimpleTranslatable
import ch.tutteli.atrium.reporting.RawString

/**
 * Makes the assertion that [IAssertionPlant.subject]'s [Collection.size] is [size].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : Collection<*>> IAssertionPlant<T>.hasSize(size: Int)
    = createAndAddAssertion(HAS_SIZE, size, { subject.size == size })

/**
 * Makes the assertion that [IAssertionPlant.subject] is an empty [Collection].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the made assertion does not hold.
 */
fun <T : Collection<*>> IAssertionPlant<T>.isEmpty()
    = createAndAddAssertion(IS_EMPTY, RawString("empty"), { subject.isEmpty() })

enum class DescriptionCollectionAssertion(override val value: String) : ISimpleTranslatable {
    HAS_SIZE("has size"),
    IS_EMPTY("is"),
    ;
}
