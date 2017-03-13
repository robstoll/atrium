package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion
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
            = AssertionFactoryCheckLazily(assertionVerb, subject, assertionChecker)

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
            = AssertionFactoryCheckImmediately(assertionVerb, subject, assertionChecker)

        fun <T : Any?> newNullable(assertionVerb: String, subject: T): IAssertionFactoryNullable<T>
            = newNullable(assertionVerb, subject, ASSERTION_CHECKER)

        fun <T : Any?> newNullable(assertionVerb: String, subject: T, assertionChecker: IAssertionChecker): IAssertionFactoryNullable<T>
            = AssertionFactoryNullable(assertionVerb, subject, assertionChecker)

        fun throwableFluent(assertionVerb: String, act: () -> Unit): ThrowableFluent
            = ThrowableFluent.create(assertionVerb, act, ThrowingAssertionChecker(REPORTER))

        /**
         * Performs a down cast in case the given {@code assertion} holds.
         *
         * Uses {@link RawString.NULL} as null-representation for failure reporting (in case the {@code assertion} does not hold).
         */
        inline fun <reified TSub : T, T : Any> downCast(assertionVerb: String, subject: T?, assertion: IAssertion, assertionChecker: IAssertionChecker): IAssertionFactory<TSub>
            = downCast(assertionVerb, subject, RawString.NULL, assertion, assertionChecker)

        /**
         * Performs a down cast in case the given {@code assertion} holds.
         *
         * Uses the given {@code nullRepresentation} as null-representation for failure reporting (in case the {@code assertion} does not hold).
         */
        inline fun <reified TSub : T, T : Any> downCast(assertionVerb: String, subject: T?, nullRepresentation: String, assertion: IAssertion, assertionChecker: IAssertionChecker): IAssertionFactory<TSub> {
            return downCast(assertionVerb, subject, nullRepresentation, assertion,
                { AssertionFactory.newCheckImmediately(assertionVerb, subject as TSub, assertionChecker) },
                { },
                assertionChecker)
        }

        /**
         * Performs a down cast in case the given {@code assertion} holds, adds the assertions created by {@ createAssertions} and checks them.
         *
         * Uses {@link RawString.NULL} as null-representation for failure reporting (in case the {@code assertion} does not hold).
         */
        inline fun <reified TSub : T, T : Any> downCast(assertionVerb: String, subject: T?, assertion: IAssertion, assertionChecker: IAssertionChecker, crossinline createAssertions: IAssertionFactory<TSub>.() -> Unit): IAssertionFactory<TSub>
            = downCast(assertionVerb, subject, RawString.NULL, assertion, assertionChecker, createAssertions)

        /**
         * Performs a down cast in case the given {@code assertion} holds, adds the assertions created by {@ createAssertions} and checks them.
         *
         * Uses the given {@code nullRepresentation} as null-representation for failure reporting (in case the {@code assertion} does not hold).
         */
        inline fun <reified TSub : T, T : Any> downCast(assertionVerb: String, subject: T?, nullRepresentation: String, assertion: IAssertion, assertionChecker: IAssertionChecker, crossinline createAssertions: IAssertionFactory<TSub>.() -> Unit): IAssertionFactory<TSub> {
            return downCast(assertionVerb, subject, nullRepresentation, assertion,
                { AssertionFactory.newCheckImmediately(assertionVerb, subject as TSub, assertionChecker) },
                { factory -> factory.createAssertions() },
                assertionChecker)
        }

        /**
         * @deprecated Must be public in order that the overloads with modifier inline can be public as well.
         * It is recommended to use one of those. This method might change its API.
         */
        fun <TSub : T, T : Any> downCast(
            assertionVerb: String,
            subject: T?,
            nullRepresentation: String,
            assertion: IAssertion,
            factoryMethod: () -> IAssertionFactory<TSub>,
            actOnFactory: (IAssertionFactory<TSub>) -> Unit,
            assertionChecker: IAssertionChecker): IAssertionFactory<TSub> {

            if (assertion.holds()) {
                //needs to hold in order that cast can be performed
                val factory = factoryMethod()
                factory.addAssertion(assertion)
                actOnFactory(factory)
                return factory
            }
            assertionChecker.fail(assertionVerb, subject ?: nullRepresentation, assertion)
            throw IllegalStateException("calling ${IAssertionChecker::class.java.simpleName}#${IAssertionChecker::fail.name} should throw an exception, ${assertionChecker::class.java.name} did not")
        }

    }

}

