package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.RootAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.specs.describeFunTemplate
import io.mockk.*;
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class AssertionFormatterSpec(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val controller = mockk<AssertionFormatterController>()
    val testee = testeeFactory(
        mapOf(), controller,
        ToStringObjectFormatter
    )

    var sb = StringBuilder()
    var parameterObject = AssertionFormatterParameterObject.new(
        sb,
        alwaysTrueAssertionFilter
    )
    afterEachTest {
        sb = StringBuilder()
        parameterObject = AssertionFormatterParameterObject.new(
            sb,
            alwaysTrueAssertionFilter
        )
    }

    describeFun(testee::format.name) {
        it("throws an UnsupportedOperationException if ${AssertionGroup::class.simpleName} is passed") {

            expect {
                testee.format(object : AssertionGroup {
                    //TODO 1.3.0 replace with Proof remove suppression
                    @Suppress("DEPRECATION")
                    override val description = ch.tutteli.atrium.reporting.translating.Untranslatable("test")
                    override val type = RootAssertionGroupType
                    override val representation = 1
                    override val assertions: List<Assertion> = emptyList()
                }, parameterObject)
            }.toThrow<UnsupportedOperationException> { message { toEqual(AssertionFormatter.CALL_FORMAT_GROUP) } }
            expect(sb).toBeEmpty()
        }
    }
})
