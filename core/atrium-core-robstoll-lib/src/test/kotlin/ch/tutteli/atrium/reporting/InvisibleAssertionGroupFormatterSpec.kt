package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultInvisibleAssertionGroupType
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.reporting.translating.Translator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class InvisibleAssertionGroupFormatterSpec : Spek({

    include(AtriumsInvisibleAssertionGroupFormatterSpec)
    include(AtriumsEmptyNameAndSubjectAssertionGroupFormatterSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsInvisibleAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.InvisibleAssertionGroupFormatterSpec(
        AssertionVerbFactory, ::InvisibleAssertionGroupFormatter, "[Atrium's InvisibleGroup...Spec] ")

    object AtriumsEmptyNameAndSubjectAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.EmptyNameAndSubjectAssertionGroupFormatterSpec<InvisibleAssertionGroupType>(
        AssertionVerbFactory, ::InvisibleAssertionGroupFormatter,
        InvisibleAssertionGroupType::class.java,
        DefaultInvisibleAssertionGroupType,
        object : InvisibleAssertionGroupType {},
        "[Atrium's EmptyNameAndSubject...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<InvisibleAssertionGroupType>(
        AssertionVerbFactory, factory(),
        InvisibleAssertionGroupType::class.java,
        DefaultInvisibleAssertionGroupType,
        object : InvisibleAssertionGroupType {},
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's AssertionFormatterSpec] ")

    companion object {
        fun factory() = { _: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, _: ObjectFormatter, _: Translator ->
            InvisibleAssertionGroupFormatter(assertionFormatterController)
        }
    }
}
