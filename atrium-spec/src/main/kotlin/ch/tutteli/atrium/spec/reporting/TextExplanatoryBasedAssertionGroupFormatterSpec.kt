package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

abstract class TextExplanatoryBasedAssertionGroupFormatterSpec<T : IExplanatoryAssertionGroupType>(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    assertionGroupTypeClass: Class<T>,
    anonymousAssertionGroupType: T,
    groupFactory: (List<IAssertion>) -> IAssertionGroup,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : ch.tutteli.atrium.spec.reporting.TextIndentBasedAssertionGroupFormatterSpec<T>(
        verbs,
        testeeFactory,
        assertionGroupTypeClass,
        anonymousAssertionGroupType,
        groupFactory,
        describePrefix
    ) {})
})
