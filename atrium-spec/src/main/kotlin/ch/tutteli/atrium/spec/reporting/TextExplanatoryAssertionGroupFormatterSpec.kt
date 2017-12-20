package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.IAssertionVerbFactory

abstract class TextExplanatoryAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextExplanatoryBasedAssertionGroupFormatterSpec<IExplanatoryAssertionGroupType>(
    verbs,
    testeeFactory,
    IExplanatoryAssertionGroupType::class.java,
    object : IExplanatoryAssertionGroupType {},
    { ExplanatoryAssertionGroup(ExplanatoryAssertionGroupType, it) },
    describePrefix
)
