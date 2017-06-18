package ch.tutteli.atrium.reporting


import ch.tutteli.atrium.*
import ch.tutteli.atrium.AssertionVerb.ASSERT
import ch.tutteli.atrium.DescriptionAnyAssertion.*
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.reporting.ToStringObjectFormatter
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

object SameLineAssertionMessageFormatterSpec : Spek({
    include(ch.tutteli.atrium.spec.reporting.SameLineAssertionMessageFormatterSpec(
        AssertionVerbFactory, ::SameLineAssertionFormatter))

    //TODO rewrite, not all tests should be integration test, some could be unit-tests
    val testee = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
    testee.register({SameLineAssertionFormatter(it, ToStringObjectFormatter(), UsingDefaultTranslator())})

    val alwaysTrueAssertionFilter: (IAssertion) -> Boolean = { true }
    val alwaysTrueMessageFilter: (Message) -> Boolean = { true }

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }
    val separator = System.getProperty("line.separator")!!

    fun createRootAssertionGroup(name: ITranslatable, subject: Any, assertions: List<IAssertion>)
        = AssertionGroup(RootAssertionGroupType, name, subject, assertions)

    fun createFeatureAssertionGroup(name: ITranslatable, subject: Any, assertions: List<IAssertion>)
        = AssertionGroup(FeatureAssertionGroupType, name, subject, assertions)

    context("a ${IAssertionGroup::class.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
        it("includes the group name and its representation") {
            testee.format(createRootAssertionGroup(ASSERT, "subject", listOf(
                OneMessageAssertion(TO_BE, "bli", false),
                OneMessageAssertion(NOT_TO_BE, "bye", false)
            )), sb, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).startsWith("assert: subject$separator" +
                "${TO_BE.getDefault()}: bli$separator${NOT_TO_BE.getDefault()}: bye")
        }
    }

    context("a ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
        val arrow = "-> "
        val arrowLength = arrow.length
        it("starts feature name with '$arrow' followed by representation") {
            testee.format(createFeatureAssertionGroup(Untranslatable("name"), "robert", listOf(
                OneMessageAssertion(TO_BE, "robert", true),
                OneMessageAssertion(NOT_TO_BE, "bert", true)
            )), sb, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).startsWith("${arrow}name: robert")
        }

        val indent = " ".repeat(arrowLength)

        it("indents assertions by $arrowLength spaces") {
            testee.format(createFeatureAssertionGroup(Untranslatable("name"), "robert", listOf(
                OneMessageAssertion(TO_BE, "robert", true),
                OneMessageAssertion(NOT_TO_BE, "bert", true)
            )), sb, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).contains("$indent${TO_BE.getDefault()}: robert$separator"
                + "$indent${NOT_TO_BE.getDefault()}: bert")
        }

        context("in an ${IAssertionGroup::class.java.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
            it("does only indent the assertions but not the feature name") {
                val message = Message(IS_SAME, "whatever", false)
                testee.format(createRootAssertionGroup(ASSERT, message, listOf(
                    createFeatureAssertionGroup(Untranslatable(message::description.name), message.description, listOf(
                        OneMessageAssertion(TO_BE, "a", true),
                        OneMessageAssertion(NOT_TO_BE, "description", true)
                    )),
                    createFeatureAssertionGroup(Untranslatable(message::representation.name), message.representation, listOf(
                        OneMessageAssertion(TO_BE, "whatever", true)
                    ))
                )), sb, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
                assert(sb.toString()).toBe("assert: " + message + separator
                    + "-> description: $IS_SAME$separator"
                    + "$indent${TO_BE.getDefault()}: a$separator"
                    + "$indent${NOT_TO_BE.getDefault()}: description$separator"
                    + "-> representation: whatever$separator"
                    + "$indent${TO_BE.getDefault()}: whatever"
                )
            }

            context("in another ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
                it("does indent the feature and double-intends its assertions") {
                    val message = Message(IS_SAME, "whatever", false)
                    testee.format(createRootAssertionGroup(ASSERT, message, listOf(
                        createFeatureAssertionGroup(Untranslatable(message::description.name), message.description, listOf(
                            OneMessageAssertion(TO_BE, "a", true),
                            createFeatureAssertionGroup(Untranslatable(message::description::toString.name), message.description, listOf(
                                OneMessageAssertion(NOT_TO_BE, "a description", true)
                            ))
                        ))
                    )), sb, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
                    assert(sb.toString()).toBe("assert: " + message + separator
                        + "-> description: $IS_SAME$separator"
                        + "$indent${TO_BE.getDefault()}: a$separator"
                        + "$indent-> toString: $IS_SAME$separator"
                        + "$indent$indent${NOT_TO_BE.getDefault()}: a description"
                    )
                }
            }
        }
    }

})


