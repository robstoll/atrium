package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory

abstract class TextExplanatoryAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (String, IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextListBasedAssertionGroupFormatterSpec<IExplanatoryAssertionGroupType>(
    verbs,
    testeeFactory,
    IExplanatoryAssertionGroupType::class.java,
    ExplanatoryAssertionGroupType,
    object : IExplanatoryAssertionGroupType {},
    2,
    describePrefix)
