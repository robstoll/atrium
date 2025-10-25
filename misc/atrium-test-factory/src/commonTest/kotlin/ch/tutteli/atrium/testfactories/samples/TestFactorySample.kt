package ch.tutteli.atrium.testfactories.samples

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.expect.ExpectTestExecutable
import ch.tutteli.atrium.testfactories.TestFactoryBuilder
import ch.tutteli.atrium.testfactories.expect.createExpectTestExecutableFactory
import ch.tutteli.atrium.testfactories.testFactoryTemplate
import com.example.expect

class TestFactorySample {

    fun testFactoryTemplate() {
        // usually defined as an object with a name instead of an anonymous object, only anonymous here because we are
        // in a function
        val myVerbs = object : ExpectationVerbs {
            override fun <T> expect(subject: T): Expect<T> =
                // here (same same below) you delegate to your own expectation verb implementation
                com.example.expect(subject)

            override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
                com.example.expect(subject, expectationCreator)

            override val defaultExpectGroupDescription: String = "my expectations"

            override fun expectGrouped(
                description: String,
                groupingActions: ExpectGrouping.() -> Unit
            ): ExpectGrouping = com.example.expectGrouped(description, groupingActions)


            override fun <T> expectInExpectGrouped(expectGrouping: ExpectGrouping, subject: T): Expect<T> {
                // extension functions cannot be called with fully qualified name, i.e we have defined an
                // `import com.example.expect` where `expect` is `fun ExpectGrouping.expect(...)`
                return expectGrouping.expect(subject)
            }

            override fun <T> expectInExpectGrouped(
                expectGrouping: ExpectGrouping,
                subject: T,
                expectationCreator: Expect<T>.() -> Unit
            ): Expect<T> = expectGrouping.expect(subject, expectationCreator)
        }

        // here we define that we want to use `expect` as expectation verb inside the TestFactoryBuilder by using
        // ExpectTestExecutable.
        fun testFactory(setup: TestFactoryBuilder<ExpectTestExecutable>.() -> Unit) =
            testFactoryTemplate(setup, createExpectTestExecutableFactory(myVerbs))
    }

    fun testFactoryTemplateVarag() {
        // usually defined as an object with a name instead of an anonymous object, only anonymous here because we
        // are in a function
        val myVerbs = object : ExpectationVerbs {
            override fun <T> expect(subject: T): Expect<T> = com.example.expect(subject)

            override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
                com.example.expect(subject, expectationCreator)

            override val defaultExpectGroupDescription: String = "my expectations"
            override fun expectGrouped(
                description: String,
                groupingActions: ExpectGrouping.() -> Unit
            ): ExpectGrouping = com.example.expectGrouped(description, groupingActions)

            override fun <T> expectInExpectGrouped(expectGrouping: ExpectGrouping, subject: T): Expect<T> {
                // extension functions cannot be called with fully qualified name, i.e we have defined an
                // `import com.example.expect` where `expect` is `fun ExpectGrouping.expect(...)`
                return expectGrouping.expect(subject)
            }

            override fun <T> expectInExpectGrouped(
                expectGrouping: ExpectGrouping,
                subject: T,
                expectationCreator: Expect<T>.() -> Unit
            ): Expect<T> = expectGrouping.expect(subject, expectationCreator)
        }

        // here we define that we want to use `expect` as expectation verb inside the TestFactoryBuilder by using
        // ExpectTestExecutable.
        fun testFactory(
            setup: TestFactoryBuilder<ExpectTestExecutable>.() -> Unit,
            vararg otherSetups: TestFactoryBuilder<ExpectTestExecutable>.() -> Unit
        ) = testFactoryTemplate(setup, otherSetups, createExpectTestExecutableFactory(myVerbs))
    }

    fun testFactoryTemplateList() {
        // usually defined as an object with a name instead of an anonymous object, only anonymous here because we
        // are in a function
        val myVerbs = object : ExpectationVerbs {
            override fun <T> expect(subject: T): Expect<T> = com.example.expect(subject)

            override fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> =
                com.example.expect(subject, expectationCreator)

            override val defaultExpectGroupDescription: String = "my expectations"
            override fun expectGrouped(
                description: String,
                groupingActions: ExpectGrouping.() -> Unit
            ): ExpectGrouping = com.example.expectGrouped(description, groupingActions)

            override fun <T> expectInExpectGrouped(expectGrouping: ExpectGrouping, subject: T): Expect<T> {
                // extension functions cannot be called with fully qualified name, i.e we have defined an
                // `import com.example.expect` where `expect` is `fun ExpectGrouping.expect(...)`
                return expectGrouping.expect(subject)
            }

            override fun <T> expectInExpectGrouped(
                expectGrouping: ExpectGrouping,
                subject: T,
                expectationCreator: Expect<T>.() -> Unit
            ): Expect<T> = expectGrouping.expect(subject, expectationCreator)
        }

        // here we define that we want to use `expect` as expectation verb inside the TestFactoryBuilder by using
        // ExpectTestExecutable.
        fun testFactory(
            setups: List<TestFactoryBuilder<ExpectTestExecutable>.() -> Unit>,
        ) = testFactoryTemplate(setups, createExpectTestExecutableFactory(myVerbs))
    }
}
