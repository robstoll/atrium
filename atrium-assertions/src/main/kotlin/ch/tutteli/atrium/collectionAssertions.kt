package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionCollectionAssertion.HAS_SIZE
import ch.tutteli.atrium.DescriptionCollectionAssertion.IS_EMPTY
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.IEnTranslatable

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

enum class DescriptionCollectionAssertion(override val value: String) : IEnTranslatable {
    HAS_SIZE("has size"),
    IS_EMPTY("is"),
    ;
}
