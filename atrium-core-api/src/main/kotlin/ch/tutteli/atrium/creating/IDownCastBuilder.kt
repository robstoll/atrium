package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

/**
 * A builder for creating a down-casting [IAssertion].
 *
 * Or in other words, helps to make an assertion about [IAssertionPlant.subject] of type [T] that it can be
 * down-casted to [TSub].
 *
 * @param T The type of [IAssertionPlant.subject].
 * @param TSub The type to which [IAssertionPlant.subject] can be down-casted, hence needs to be a subtype of [T].
 */
interface IDownCastBuilder<out T : Any, out TSub : T> {

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
