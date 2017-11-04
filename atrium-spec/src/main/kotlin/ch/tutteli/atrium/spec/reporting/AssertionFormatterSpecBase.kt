package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec
import org.jetbrains.spek.api.include

abstract class AssertionFormatterSpecBase(spec: Spec.() -> Unit) : Spek({
    include(wrap(spec))

    afterEachTest {
        sb = StringBuilder()
        methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
    }
}) {
    companion object {
        val separator = System.getProperty("line.separator")!!
        var sb: StringBuilder = StringBuilder()
        var methodObject = AssertionFormatterMethodObject.new(sb, alwaysTrueAssertionFilter)
        val bulletPoint = "*"
        val indentBulletPoint = " ".repeat(bulletPoint.length + 1)
        val listBulletPoint = "--"
        val indentListBulletPoint = " ".repeat(listBulletPoint.length + 1)
        val arrow = "->"
        val indentArrow = " ".repeat(arrow.length + 1)
        val featureBulletPoint = "+++"
        val indentFeatureBulletPoint = " ".repeat(featureBulletPoint.length + 1)
        val bulletPoints
            get() = mapOf(
                RootAssertionGroupType::class.java to "$bulletPoint ",
                IListAssertionGroupType::class.java to "$listBulletPoint ",
                PrefixFeatureAssertionGroupHeader::class.java to "$arrow ",
                IFeatureAssertionGroupType::class.java to "$featureBulletPoint "
            )

        fun createFacade() = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())

        fun createFacade(testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter): IAssertionFormatterFacade
            = createFacade(mapOf(), testeeFactory)

        fun createFacade(bulletPoint: Pair<Class<out IBulletPointIdentifier>, String>, testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter): IAssertionFormatterFacade
            = createFacade(mapOf(bulletPoint), testeeFactory)

        fun createFacade(extendedBulletPoints: Map<Class<out IBulletPointIdentifier>, String>, testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter): IAssertionFormatterFacade {
            val facade = createFacade()
            facade.register { testeeFactory(extendedBulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
            facade.register { AtriumFactory.newTextListAssertionGroupFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
            facade.register { AtriumFactory.newTextFeatureAssertionGroupFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
            facade.register { AtriumFactory.newTextFallbackAssertionFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
            return facade
        }
    }
}
