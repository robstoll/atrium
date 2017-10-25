package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatterMethodObject
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.it

abstract class SingleAssertionGroupTypeFormatterSpec<out T : IAssertionGroupType>(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter,
    supportedAssertionGroupTypeClass: Class<T>,
    supportedAssertionGroupType: T,
    supportedAnonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val testee = testeeFactory(AtriumFactory.newAssertionFormatterController(), ToStringObjectFormatter, UsingDefaultTranslator())

    val unsupportedAssertion = object : IAssertion {
        override fun holds() = false
    }
    val unsupportedAssertionGroup = AssertionGroup(object : IAssertionGroupType {}, Untranslatable(""), 1, listOf())
    val supportedAssertionGroupWithAnonymousType = AssertionGroup(supportedAnonymousAssertionGroupType, Untranslatable(""), 1, listOf())
    val supportedAnonymousAssertionGroupWithAnonymousType = object : IAssertionGroup {
        override val name = Untranslatable("test")
        override val type = supportedAnonymousAssertionGroupType
        override val subject = 1
        override val assertions: List<IAssertion> = emptyList()
    }
    val supportedAssertionGroup = AssertionGroup(supportedAssertionGroupType, Untranslatable(""), 1, listOf())
    val supportedAnonymousAssertionGroup = object : IAssertionGroup {
        override val name = Untranslatable("test")
        override val type = supportedAssertionGroupType
        override val subject = 1
        override val assertions: List<IAssertion> = emptyList()
    }

    var sb = StringBuilder()
    var methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
    afterEachTest {
        sb = StringBuilder()
        methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
    }

    prefixedDescribe("fun ${testee::canFormat.name}") {
        it("returns true for an ${IAssertionGroup::class.simpleName} with type object: ${supportedAssertionGroupTypeClass.simpleName}") {
            val result = testee.canFormat(supportedAnonymousAssertionGroupWithAnonymousType)
            verbs.checkImmediately(result).isTrue()
        }
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${supportedAssertionGroupTypeClass.simpleName}") {
            val result = testee.canFormat(supportedAssertionGroupWithAnonymousType)
            verbs.checkImmediately(result).isTrue()
        }
        it("returns true for an ${IAssertionGroup::class.simpleName} with type ${supportedAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(supportedAnonymousAssertionGroup)
            verbs.checkImmediately(result).isTrue()
        }
        it("returns true for an ${AssertionGroup::class.simpleName} with type ${supportedAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(supportedAssertionGroup)
            verbs.checkImmediately(result).isTrue()
        }

        it("returns false for an ${IAssertionGroup::class.simpleName} with type object: ${IAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(unsupportedAssertionGroup)
            verbs.checkImmediately(result).isFalse()
        }

        it("returns false for an object : ${IAssertion::class.simpleName}") {
            val result = testee.canFormat(unsupportedAssertion)
            verbs.checkImmediately(result).isFalse()
        }
    }

    prefixedDescribe("fun ${testee::formatNonGroup.name}") {
        it("throws an UnsupportedOperationException") {
            verbs.checkException {
                testee.formatNonGroup(unsupportedAssertion, methodObject)
            }.toThrow<UnsupportedOperationException>().message.contains(supportedAssertionGroupTypeClass.name)
            verbs.checkImmediately(sb).isEmpty()
        }
    }

    prefixedDescribe("fun ${testee::formatGroup.name}") {
        it("throws an UnsupportedOperationException for an ${IAssertionGroup::class.simpleName} with type object: ${IAssertionGroupType::class.simpleName}") {
            verbs.checkException {
                testee.formatGroup(unsupportedAssertionGroup, methodObject, {})
            }.toThrow<UnsupportedOperationException>().message.contains(supportedAssertionGroupTypeClass.name)
            verbs.checkImmediately(sb).isEmpty()
        }

        it("does not throw if an ${IAssertionGroup::class.simpleName} of type object: ${supportedAssertionGroupTypeClass.simpleName} is passed") {
            testee.formatGroup(supportedAnonymousAssertionGroupWithAnonymousType, methodObject, {})
        }

        it("does not throw if an ${IAssertionGroup::class.simpleName} of type ${supportedAssertionGroup::class.simpleName} is passed") {
            testee.formatGroup(supportedAnonymousAssertionGroup, methodObject, {})
        }

        it("does not throw if an ${AssertionGroup::class.simpleName} of type object: ${supportedAssertionGroupTypeClass.simpleName} is passed") {
            testee.formatGroup(supportedAssertionGroupWithAnonymousType, methodObject, {})
        }

        it("does not throw if an ${AssertionGroup::class.simpleName} of type  type ${supportedAssertionGroup::class.simpleName}  is passed") {
            testee.formatGroup(supportedAssertionGroup, methodObject, {})
        }
    }
})
