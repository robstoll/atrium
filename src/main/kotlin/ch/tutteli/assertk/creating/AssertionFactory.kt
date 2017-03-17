package ch.tutteli.assertk.creating

import ch.tutteli.assertk.checking.IAssertionChecker
import ch.tutteli.assertk.checking.ThrowingAssertionChecker
import ch.tutteli.assertk.reporting.*

class AssertionFactory {
    companion object {
        private val OBJECT_FORMATTER: IObjectFormatter = DetailedObjectFormatter()
        private val ASSERTION_MESSAGE_FORMATTER: IAssertionMessageFormatter = SameLineAssertionMessageFormatter(OBJECT_FORMATTER)
        private val REPORTER: IReporter = OnlyFailureReporter(ASSERTION_MESSAGE_FORMATTER)
        private val ASSERTION_CHECKER: IAssertionChecker = ThrowingAssertionChecker(REPORTER)

        /**
         * Creates an IAssertionFactory which does not check the created assertions until one calls checkAssertions.
         *
         * It uses the default IAssertionChecker.
         */
        fun <T : Any> newCheckLazily(assertionVerb: String, subject: T): IAssertionFactory<T>
            = newCheckLazily(assertionVerb, subject, ASSERTION_CHECKER)

        /**
         * Creates an IAssertionFactory which does not check the created assertions until one calls checkAssertions.
         *
         */
        fun <T : Any> newCheckLazily(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionFactory<T>
            = AssertionFactoryCheckLazily(IAssertionFactoryBase.CommonFields(assertionVerb, subject, assertionChecker))

        /**
         * Use this function to create a custom assertion verb function which lazy evaluate the assertions created by createAssertions.
         *
         * This function will create an IAssertionFactory which does not check the created assertions until one calls checkAssertions
         * but uses the given createAssertions function which might add some assertions and then calls checkAssertions. In case all
         * assertions added so far hold, then it will not evaluate further added assertions until one calls checkAssertions again.
         */
        inline fun <T : Any> newCheckLazilyAtTheEnd(assertionVerb: String, subject: T, createAssertions: IAssertionFactory<T>.() -> Unit)
            = createAssertionsAndCheckThem(AssertionFactory.newCheckLazily(assertionVerb, subject), createAssertions)

        inline fun <T : Any> createAssertionsAndCheckThem(factory: IAssertionFactory<T>, createAssertions: IAssertionFactory<T>.() -> Unit): IAssertionFactory<T> {
            factory.createAssertions()
            factory.checkAssertions()
            return factory
        }

        fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T): IAssertionFactory<T>
            = newCheckImmediately(assertionVerb, subject, ASSERTION_CHECKER)

        fun <T : Any> newCheckImmediately(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionFactory<T>
            = AssertionFactoryCheckImmediately(IAssertionFactoryBase.CommonFields(assertionVerb, subject, assertionChecker))

        fun <T : Any?> newNullable(assertionVerb: String, subject: T): IAssertionFactoryNullable<T>
            = newNullable(assertionVerb, subject, ASSERTION_CHECKER)

        fun <T : Any?> newNullable(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionFactoryNullable<T>
            = AssertionFactoryNullable(IAssertionFactoryBase.CommonFields(assertionVerb, subject, assertionChecker))

        fun throwableFluent(assertionVerb: String, act: () -> Unit): ThrowableFluent
            = ThrowableFluent.create(assertionVerb, act, ThrowingAssertionChecker(REPORTER))
    }

}

