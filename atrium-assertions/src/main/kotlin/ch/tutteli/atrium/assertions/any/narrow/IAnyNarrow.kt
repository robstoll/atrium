package ch.tutteli.atrium.assertions.any.narrow

import ch.tutteli.atrium.assertions.throwable.thrown.IThrowableThrown.IAbsentThrowableMessageProvider
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IBaseAssertionPlant
import kotlin.reflect.KClass

/**
 * Defines the contract for sophisticated down-cast assertions.
 *
 * The assertion is typically created by the [DownCaster] which itself requires an [IAbsentThrowableMessageProvider].
 */
interface IAnyNarrow {

    /**
     * A handler which decides what to do with the lambda which would create subsequent assertions in case the down-cast
     * of an [IAssertionPlant.subject] to type [TSub] failed.
     *
     * @param T The type of [IAssertionPlant.subject].
     * @param TSub The type to which [IAssertionPlant.subject] should have been be down-casted, hence needs to be a
     * subtype of [T].
     */
    interface IDownCastFailureHandler<T : Any, TSub : T> {

        /**
         * Makes something out of the given [createAssertions] lambda; might add assertions to [subjectPlant].
         *
         * @param subType The type to which the [subjectPlant]'s [subject][IAssertionPlant.subject] should have been
         * down-casted.
         * @param subjectPlant The plant to which additional assertions would have been added.
         * @param createAssertions The lambda function which could have created subsequent assertions for a down-casted
         * subject.
         *
         * @throws AssertionError Might throw an [AssertionError] depending on the [subjectPlant].
         */
        fun createAndAddAssertionToPlant(
            subType: KClass<TSub>,
            subjectPlant: IBaseAssertionPlant<T?, *>,
            createAssertions: IAssertionPlant<TSub>.() -> Unit)
    }
}
