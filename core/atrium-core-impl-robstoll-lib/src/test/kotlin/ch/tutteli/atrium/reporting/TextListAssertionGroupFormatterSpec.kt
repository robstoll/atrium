package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.reporting.translating.Translator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class TextListAssertionGroupFormatterSpec : Spek({

    include(AtriumsTextListAssertionFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsTextListAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.TextListAssertionGroupFormatterSpec(
        AssertionVerbFactory, factoryWithBullet(), "[Atrium's TextList...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<ListAssertionGroupType>(
        AssertionVerbFactory, factoryWithBullet(),
        ListAssertionGroupType::class.java,
        object : ListAssertionGroupType {},
        DefaultListAssertionGroupType,
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factoryWithBullet(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        internal fun factoryWithBullet() = { bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator ->
            TextListAssertionGroupFormatter(bulletPoints, assertionFormatterController, TextSameLineAssertionPairFormatter(objectFormatter, translator))
        }
    }
}
