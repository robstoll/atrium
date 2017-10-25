package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assertions.IInvisibleAssertionGroupType
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.reporting.translating.ITranslator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

class InvisibleAssertionGroupFormatterSpec : Spek({

    include(AtriumsInvisibleAssertionGroupFormatterSpecSpec)
    include(AtriumsSingleAssertionGroupTypeFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

}) {
    object AtriumsInvisibleAssertionGroupFormatterSpecSpec : ch.tutteli.atrium.spec.reporting.InvisibleAssertionGroupFormatterSpec(
        AssertionVerbFactory, ::InvisibleAssertionGroupFormatter, "[Atrium's InvisibleGroup...Spec] ")

    object AtriumsSingleAssertionGroupTypeFormatterSpec : ch.tutteli.atrium.spec.reporting.SingleAssertionGroupTypeFormatterSpec<IInvisibleAssertionGroupType>(
        AssertionVerbFactory, factory(),
        IInvisibleAssertionGroupType::class.java,
        InvisibleAssertionGroupType,
        object : IInvisibleAssertionGroupType {},
        "[Atrium's SingleAssertionGroupType...Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's AssertionFormatterSpec] ")

    companion object {
        fun factory() = { assertionFormatterController: IAssertionFormatterController, _: IObjectFormatter, _: ITranslator ->
            InvisibleAssertionGroupFormatter(assertionFormatterController)
        }
    }
}
