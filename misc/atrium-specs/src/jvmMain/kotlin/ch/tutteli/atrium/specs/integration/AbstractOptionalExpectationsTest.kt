package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionOptionalAssertion
import java.util.*

@Suppress("FunctionName")
abstract class AbstractOptionalExpectationsTest(
    private val toBeEmptySpec: Fun0<Optional<Int>>,
    private val toBePresentFeatureSpec: Feature0<Optional<Int>, Int>,
    private val toBePresentSpec: Fun1<Optional<Int>, Expect<Int>.() -> Unit>,
) : ExpectationFunctionBaseTest() {

    private val emptyValue: Optional<Int> = Optional.empty()
    private val presentValue: Optional<Int> = Optional.of(2)

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toBeEmptySpec.forSubjectLessTest(),
            toBePresentFeatureSpec.forSubjectLessTest(),
            toBePresentSpec.forSubjectLessTest { toEqual(1) }
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            Optional.of(2),
            toBePresentSpec.forExpectationCreatorTest("$toEqualDescr: 2") { toEqual(2) }
        )
    )

    @TestFactory
    fun toBeEmpty_and_toBePresent() = testFactory(
        toBeEmptySpec,
        toBePresentFeatureSpec,
        toBePresentSpec
    ) {
        val toBeEmptyFun = toBeEmptySpec.lambda
        val toBePresentFunctions = unifySignatures(toBePresentFeatureSpec, toBePresentSpec)

        describe("subject is $emptyValue") {
            it("${toBeEmptySpec.name} - does not throw") {
                expect(emptyValue).toBeEmptyFun()
            }
            toBePresentFunctions.forEach { (name, toBePresentFun, hasExtraHint) ->
                it("$name - throws an AssertionError" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        expect(emptyValue).toBePresentFun { toEqual(2) }
                    }.toThrow<AssertionError> {
                        messageToContain(DescriptionOptionalAssertion.IS_NOT_PRESENT.getDefault())
                        if (hasExtraHint) messageToContain("$toEqualDescr: 2")
                    }
                }
            }
        }

        describe("subject is $presentValue") {
            it("${toBeEmptySpec.name} - throws an AssertionError") {
                expect {
                    expect(presentValue).toBeEmptyFun()
                }.toThrow<AssertionError> {
                    messageToContain("$toBeDescr: ${DescriptionOptionalAssertion.EMPTY.getDefault()}")
                }
            }

            toBePresentFunctions.forEach { (name, toBePresentFun, _) ->
                it("$name - can perform sub-assertion which holds") {
                    expect(presentValue).toBePresentFun { toEqual(2) }
                }
            }
        }
    }
}
