package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.WarningAssertionGroupType
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.specs.AssertionVerbFactory
import kotlin.reflect.KClass

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class TextWarningAssertionGroupFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : TextExplanatoryBasedAssertionGroupFormatterSpec<WarningAssertionGroupType>(
    verbs,
    testeeFactory,
    WarningAssertionGroupType::class,
    WarningAssertionGroupType,
    { ExpectImpl.builder.explanatoryGroup.withWarningType.withAssertions(it).build() },
    describePrefix
)
