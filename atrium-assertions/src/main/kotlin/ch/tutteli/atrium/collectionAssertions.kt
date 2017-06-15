package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionCollectionAssertion.HAS_SIZE
import ch.tutteli.atrium.DescriptionCollectionAssertion.IS_EMPTY
import ch.tutteli.atrium.assertions.Message
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

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
    = createAndAddAssertion(IS_EMPTY, RawString("empty"), { subject.isEmpty() })

/**
 * Contains the [Message.description]s of the assertion functions which are applicable to [Collection].
 */
enum class DescriptionCollectionAssertion(override val value: String) : ISimpleTranslatable {
    HAS_SIZE("has size"),
    IS_EMPTY("is"),
}
