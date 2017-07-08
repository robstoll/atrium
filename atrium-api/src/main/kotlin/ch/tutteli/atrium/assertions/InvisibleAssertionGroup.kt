package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

class InvisibleAssertionGroup(override val assertions: List<IAssertion>) : IAssertionGroup {
    override val name: ITranslatable = Untranslatable("")
    override val type: IAssertionGroupType = InvisibleAssertionGroupType
    override val subject: Any = RawString("")

    /**
     * @suppress
     */
    override fun toString(): String {
        return InvisibleAssertionGroup::class.simpleName + " " + assertions
    }
}
