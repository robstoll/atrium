package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.IListAssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory

abstract class TextListAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (String, IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextListBasedAssertionGroupFormatterSpec<IListAssertionGroupType>(
    verbs,
    testeeFactory,
    IListAssertionGroupType::class.java,
    ListAssertionGroupType,
    object : IListAssertionGroupType {},
    0,
    describePrefix)
