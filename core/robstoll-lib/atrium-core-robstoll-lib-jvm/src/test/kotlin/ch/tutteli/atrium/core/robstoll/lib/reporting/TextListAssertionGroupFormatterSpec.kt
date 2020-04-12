package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import org.spekframework.spek2.Spek
import kotlin.reflect.KClass

class TextListAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextListAssertionFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextListAssertionFormatterSpec :
        ch.tutteli.atrium.specs.reporting.TextListAssertionGroupFormatterSpec(
            factoryWithBullet(), "[Atrium's TextList...Spec] "
        )

    object AtriumsSingleAssertionGroupTypeFormatterSpec :
        ch.tutteli.atrium.specs.reporting.SingleAssertionGroupTypeFormatterSpec<ListAssertionGroupType>(
            factoryWithBullet(),
            ListAssertionGroupType::class,
            object : ListAssertionGroupType {},
            DefaultListAssertionGroupType,
            "[Atrium's SingleAssertionGroupType...Spec] "
        )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.specs.reporting.AssertionFormatterSpec(
        factoryWithBullet(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        private fun factoryWithBullet() =
            { bulletPoints: Map<KClass<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
                TextListAssertionGroupFormatter(
                    bulletPoints,
                    assertionFormatterController,
                    TextSameLineAssertionPairFormatter(
                        objectFormatter,
                        translator
                    )
                )
            }
    }
}
