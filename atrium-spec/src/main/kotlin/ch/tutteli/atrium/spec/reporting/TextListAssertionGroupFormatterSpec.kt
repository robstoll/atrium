package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.ListAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.spec.IAssertionVerbFactory

abstract class TextListAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextListBasedAssertionGroupFormatterSpec<ListAssertionGroupType>(
    verbs,
    testeeFactory,
    ListAssertionGroupType::class.java,
    DefaultListAssertionGroupType,
    object : ListAssertionGroupType {},
    describePrefix)
