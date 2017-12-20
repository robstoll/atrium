package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IListAssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory

abstract class TextListAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, ITranslator) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextListBasedAssertionGroupFormatterSpec<IListAssertionGroupType>(
    verbs,
    testeeFactory,
    IListAssertionGroupType::class.java,
    ListAssertionGroupType,
    object : IListAssertionGroupType {},
    describePrefix)
