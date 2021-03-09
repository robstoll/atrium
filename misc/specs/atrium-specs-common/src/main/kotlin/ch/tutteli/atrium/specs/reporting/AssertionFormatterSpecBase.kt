package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.impl.AssertionFormatterControllerBasedFacade
import ch.tutteli.atrium.reporting.impl.DefaultTextAssertionFormatterController
import ch.tutteli.atrium.reporting.text.TextAssertionPairFormatter
import ch.tutteli.atrium.reporting.text.impl.TextFallbackAssertionFormatter
import ch.tutteli.atrium.reporting.text.impl.TextFeatureAssertionGroupFormatter
import ch.tutteli.atrium.reporting.text.impl.TextListAssertionGroupFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import kotlin.reflect.KClass

abstract class AssertionFormatterSpecBase(spec: Root.() -> Unit) : Spek({
    include(object: Spek(spec) {})

    afterEachTest {
        sb = StringBuilder()
        parameterObject = AssertionFormatterParameterObject.new(
            sb,
            alwaysTrueAssertionFilter
        )
    }
}) {
    companion object {
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
        val bulletPoints
            get() = mapOf(
                RootAssertionGroupType::class to "$bulletPoint ",
                ListAssertionGroupType::class to "$listBulletPoint ",
                PrefixFeatureAssertionGroupHeader::class to "$arrow ",
                FeatureAssertionGroupType::class to "$featureBulletPoint "
            )

        fun createFacade() = AssertionFormatterControllerBasedFacade(DefaultTextAssertionFormatterController())

        fun createFacade(
            bulletPoint: Pair<KClass<out BulletPointIdentifier>, String>,
            testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter
        ): AssertionFormatterFacade = createFacade(
            mapOf(bulletPoint),
            testeeFactory
        )

        fun createFacade(
            extendedBulletPoints: Map<KClass<out BulletPointIdentifier>, String>,
            testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter
        ): AssertionFormatterFacade {
            val facade = createFacade()
            val sameLineTextAssertionPairFormatter =
                TextAssertionPairFormatter.newSameLine(ToStringObjectFormatter, UsingDefaultTranslator())
            facade.register {
                testeeFactory(
                    extendedBulletPoints, it,
                    ToStringObjectFormatter, UsingDefaultTranslator()
                )
            }
            facade.register {
                TextListAssertionGroupFormatter(bulletPoints, it, sameLineTextAssertionPairFormatter)
            }
            facade.register {
                TextFeatureAssertionGroupFormatter(bulletPoints, it, sameLineTextAssertionPairFormatter)
            }
            facade.register {
                TextFallbackAssertionFormatter(
                    bulletPoints,
                    it,
                    sameLineTextAssertionPairFormatter,
                    ToStringObjectFormatter
                )
            }
            return facade
        }
    }
}
