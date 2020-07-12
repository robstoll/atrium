package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.containsNot
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.specs.describeFunTemplate
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class EmptyNameAndSubjectAssertionGroupFormatterSpec<T : AssertionGroupType>(
    testeeFactory: (AssertionFormatterController) -> AssertionFormatter,
    assertionGroupClass: KClass<T>,
    assertionGroupType: T,
    anonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val testee = testeeFactory(coreFactory.newAssertionFormatterController())

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }

    val testString = "bla blu"
    val testSubject = "testSubject"
    describeFun(AssertionFormatter::formatGroup.name) {

        context("has ${AssertionGroup::description.name} and ${AssertionGroup::representation.name}") {
            mapOf(
                "object: ${assertionGroupClass.simpleName}" to anonymousAssertionGroupType,
                "${assertionGroupType::class.simpleName}" to assertionGroupType
            ).forEach { (typeRepresentation, type) ->
                context("formatting an ${AssertionGroup::class.simpleName} of type $typeRepresentation") {
                    it("does not include ${AssertionGroup::description.name} nor ${AssertionGroup::representation.name}") {
                        val assertionGroup = assertionBuilder.customType(type)
                            .withDescriptionAndRepresentation(TestDescription.TEST_NAME, testSubject)
                            .withAssertions(listOf())
                            .build()
                        val parameterObject = AssertionFormatterParameterObject.new(
                            sb,
                            alwaysTrueAssertionFilter
                        )
                        testee.formatGroup(assertionGroup, parameterObject, { _, _ -> sb.append(testString) })
                        expect(sb.toString())
                            .containsNot(TestDescription.TEST_NAME.getDefault())
                            .containsNot(testSubject)
                            .contains(testString)
                    }
                }
            }
        }
    }
}) {
    enum class TestDescription(override val value: String) : StringBasedTranslatable {
        TEST_NAME("testName"),
    }
}
