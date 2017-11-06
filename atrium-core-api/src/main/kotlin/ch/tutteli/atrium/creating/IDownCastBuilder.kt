package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable
import kotlin.reflect.full.cast

/**
 * A builder for creating a down-casting [IAssertion].
 *
 * Or in other words, helps to make an assertion about [IAssertionPlant.subject] of type [T] that it can be
 * down-casted to [TSub].
 *
 * @param T The type of [IAssertionPlant.subject].
 * @param TSub The type to which [IAssertionPlant.subject] can be down-casted, hence needs to be a subtype of [T].
 */
interface IDownCastBuilder<T : Any, TSub : T> {

    /**
     * Use this method if you want to add several assertions which are checked lazily after the down cast is performed.
     *
     * Or in other words, the given [createAssertions] function will be called,
     * which might add additional [IAssertion]s to the newly created [IAssertionPlant], which are then lazily checked
     * in [cast] after the down-cast was performed and before the newly created [IAssertionPlant] is returned.
     *
     * @param createAssertions A function which is called after the down-cast has been performed in [cast]
     *        using the newly created [IAssertionPlant] as receiver.
     *
     * @return This builder to support a fluent API.
     */
    fun withLazyAssertions(createAssertions: IAssertionPlant<TSub>.() -> Unit): IDownCastBuilder<T, TSub>

    /**
     * Performs the down-cast if possible; reports a failure otherwise.
     *
     * @return The newly created [IAssertionPlant] for the down-casted [IAssertionPlant.subject].
     *
     * @throws AssertionError Might throw an [AssertionError] in case the down-cast cannot be performed
     *         or an additionally specified assertion (using [withLazyAssertions]) does not hold.
     * @throws IllegalStateException In case reporting a failure does not throw itself.
     */
    fun cast(): IAssertionPlant<TSub>
}
