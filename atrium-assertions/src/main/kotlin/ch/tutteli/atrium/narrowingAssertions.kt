package ch.tutteli.atrium

import ch.tutteli.atrium.DescriptionNarrowingAssertion.IS_A
import ch.tutteli.atrium.DescriptionNarrowingAssertion.IS_NOT_NULL
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.Message
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.createAssertionsAndCheckThem
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.KProperty0

/**
 * Makes the assertion that [IAssertionPlant.subject] is not null.
 *
 * @return This plant to support a fluent-style API.
 */
inline fun <reified T : Any> IAssertionPlantNullable<T?>.isNotNull()
    = AtriumFactory.newDownCastBuilder(IS_NOT_NULL, commonFields)
    .cast()

/**
 * Makes the assertion that [IAssertionPlant.subject] is not null and if so, uses [createAssertions]
 * which could create further assertions which are lazily evaluated at the end.
 *
 * @return This plant to support a fluent-style API.
 */
inline fun <reified T : Any> IAssertionPlantNullable<T?>.isNotNull(noinline createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newDownCastBuilder(IS_NOT_NULL, commonFields)
    .withLazyAssertions(createAssertions)
    .cast()

/**
 * Makes the assertion that [IAssertionPlant.subject] *is a* [TSub] (the same type or a sub-type).
 *
 * @return This plant to support a fluent-style API.
 */
inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(): IAssertionPlant<TSub>
    = AtriumFactory.newDownCastBuilder<TSub, Any>(IS_A, commonFields)
    .cast()

/**
 * Makes the assertion that [IAssertionPlant.subject] *is a* [TSub] (the same type or a sub-type) and if so,
 * uses [createAssertions] which could create further assertions which are lazily evaluated at the end.
 *
 * @return This plant to support a fluent-style API.
 */
inline fun <reified TSub : Any> IAssertionPlant<Any>.isA(noinline createAssertions: IAssertionPlant<TSub>.() -> Unit): IAssertionPlant<TSub>
    = AtriumFactory.newDownCastBuilder<TSub, Any>(IS_A, commonFields)
    .withLazyAssertions(createAssertions)
    .cast()

/**
 * Contains the [Message.description]s of the assertion functions which postulate that a [IAssertionPlant.subject]
 * of type `T` can be narrowed to `TSub` where `TSub <: T`.
 */
enum class DescriptionNarrowingAssertion(override val value: String) : ISimpleTranslatable {
    IS_NOT_NULL("is not"),
    IS_A("is type or sub-type of"),
}
