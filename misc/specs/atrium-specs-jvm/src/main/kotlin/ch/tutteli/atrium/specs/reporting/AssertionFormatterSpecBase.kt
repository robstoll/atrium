package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec
import org.jetbrains.spek.api.include
import kotlin.reflect.KClass

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class AssertionFormatterSpecBase(spec: Spec.() -> Unit) : Spek({
    include(wrap(spec))

    afterEachTest {
        sb = StringBuilder()
        parameterObject = AssertionFormatterParameterObject.new(
            sb,
            alwaysTrueAssertionFilter
        )
    }
}) {
    companion object {
        val separator = System.getProperty("line.separator")!!
        var sb: StringBuilder = StringBuilder()
        var parameterObject = AssertionFormatterParameterObject.new(
            sb,
            alwaysTrueAssertionFilter
        )
        const val bulletPoint = "*"
        const val listBulletPoint = "--"
        val indentListBulletPoint = " ".repeat(listBulletPoint.length + 1)
        const val arrow = "->"
        val indentArrow = " ".repeat(arrow.length + 1)
        const val featureBulletPoint = "+++"
        val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length + 1)
        val bulletPoints get() = mapOf(
            RootAssertionGroupType::class to "$bulletPoint ",
            ListAssertionGroupType::class to "$listBulletPoint ",
            PrefixFeatureAssertionGroupHeader::class to "$arrow ",
            FeatureAssertionGroupType::class to "$featureBulletPoint "
        )

        fun createFacade() = coreFactory.newAssertionFormatterFacade(coreFactory.newAssertionFormatterController())

        fun createFacade(testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter): AssertionFormatterFacade
            =
            createFacade(mapOf(), testeeFactory)

        fun createFacade(bulletPoint: Pair<KClass<out BulletPointIdentifier>, String>, testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter): AssertionFormatterFacade
            = createFacade(
            mapOf(bulletPoint),
            testeeFactory
        )

        fun createFacade(extendedBulletPoints: Map<KClass<out BulletPointIdentifier>, String>, testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter): AssertionFormatterFacade {
            val facade = createFacade()
            facade.register { testeeFactory(extendedBulletPoints, it,
                ToStringObjectFormatter, UsingDefaultTranslator()) }
            facade.register { coreFactory.newTextListAssertionGroupFormatter(
                bulletPoints, it,
                ToStringObjectFormatter, UsingDefaultTranslator()) }
            facade.register { coreFactory.newTextFeatureAssertionGroupFormatter(
                bulletPoints, it,
                ToStringObjectFormatter, UsingDefaultTranslator()) }
            facade.register { coreFactory.newTextFallbackAssertionFormatter(
                bulletPoints, it,
                ToStringObjectFormatter, UsingDefaultTranslator()) }
            return facade
        }
    }
}
