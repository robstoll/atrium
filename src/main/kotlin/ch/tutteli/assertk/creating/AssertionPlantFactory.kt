package ch.tutteli.assertk.creating

import ch.tutteli.assertk.checking.IAssertionChecker
import ch.tutteli.assertk.checking.ThrowingAssertionChecker
import ch.tutteli.assertk.reporting.*

class AssertionPlantFactory {
    companion object {
        private val OBJECT_FORMATTER: IObjectFormatter = DetailedObjectFormatter()
        private val ASSERTION_MESSAGE_FORMATTER: IAssertionMessageFormatter = SameLineAssertionMessageFormatter(OBJECT_FORMATTER)
        private val REPORTER: IReporter = OnlyFailureReporter(ASSERTION_MESSAGE_FORMATTER)
        private val ASSERTION_CHECKER: IAssertionChecker = ThrowingAssertionChecker(REPORTER)


        /**
         * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
         *
         * It uses the default [IAssertionChecker].
         */
        fun <T : Any> newCheckLazily(assertionVerb: String, subject: T): IAssertionPlant<T>
            = newCheckLazily(assertionVerb, subject, ASSERTION_CHECKER)

        /**
         * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
         */
        fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
            = newCheckLazily(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

        /**
         * Creates an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions].
         */
        fun <T : Any> newCheckLazily(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
            = AssertionPlantCheckLazily(commonFields)


        fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T): IAssertionPlant<T>
            = newCheckImmediately(assertionVerb, subject, ASSERTION_CHECKER)

        fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlant<T>
            = newCheckImmediately(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

        fun <T : Any> newCheckImmediately(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlant<T>
            = AssertionPlantCheckImmediately(commonFields)

        fun <T : Any?> newNullable(assertionVerb: String, subject: T): IAssertionPlantNullable<T>
            = newNullable(assertionVerb, subject, ASSERTION_CHECKER)

        fun <T : Any?> newNullable(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionPlantNullable<T>
            = newNullable(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker))

        fun <T : Any?> newNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IAssertionPlantNullable<T>
            = AssertionPlantNullable(commonFields)

        /**
         * Use this function to create a custom assertion verb function which lazy evaluate the assertions created by createAssertions.
         *
         * This function will create an [IAssertionPlant] which does not check the created assertions until one calls [IAssertionPlant.checkAssertions]
         * but uses the given createAssertions function which might add some assertions and then calls [IAssertionPlant.checkAssertions].
         * In case all assertions added so far hold, then it will not evaluate further added assertions until one calls [IAssertionPlant.checkAssertions] again.
         */
        inline fun <T : Any> newCheckLazilyAtTheEnd(assertionVerb: String, subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
            = createAssertionsAndCheckThem(AssertionPlantFactory.newCheckLazily(assertionVerb, subject), createAssertions)

        inline fun <T : Any> createAssertionsAndCheckThem(plant: IAssertionPlant<T>, createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T> {
            plant.createAssertions()
            plant.checkAssertions()
            return plant
        }

        fun throwableFluent(assertionVerb: String, act: () -> Unit): ThrowableFluent
            = ThrowableFluent.create(assertionVerb, act, ThrowingAssertionChecker(REPORTER))
    }

}

