package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionCollectionAssertion.*
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

/**
 * Makes the assertion that [IAssertionPlant.subject]'s [Collection.size] is [size].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : Collection<*>> IAssertionPlant<T>.hasSize(size: Int)
    = createAndAddAssertion(HAS_SIZE, size, { subject.size == size })

/**
 * Makes the assertion that [IAssertionPlant.subject] is an empty [Collection].
 *
 * @return This plant to support a fluent-style API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct
 */
fun <T : Collection<*>> IAssertionPlant<T>.isEmpty()
    = createAndAddAssertion(DescriptionBasic.IS, TranslatableRawString(EMPTY), { subject.isEmpty() })

/**
 * Contains the [IBasicAssertion.description]s of the assertion functions which are applicable to [Collection].
 */
enum class DescriptionCollectionAssertion(override val value: String) : ISimpleTranslatable {
    HAS_SIZE("has size"),
    EMPTY("empty")
}
