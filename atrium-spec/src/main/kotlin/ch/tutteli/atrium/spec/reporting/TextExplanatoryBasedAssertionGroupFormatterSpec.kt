package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

abstract class TextExplanatoryBasedAssertionGroupFormatterSpec<T: IExplanatoryAssertionGroupType>(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, IAssertionFormatterController) -> IAssertionFormatter,
    assertionGroupTypeClass: Class<T>,
    anonymousAssertionGroupType: T,
    groupFactory: (List<IAssertion>) -> IAssertionGroup,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : ch.tutteli.atrium.spec.reporting.IndentBasedAssertionGroupFormatterSpec<T>(
        verbs,
        testeeFactory,
        assertionGroupTypeClass,
        anonymousAssertionGroupType,
        groupFactory,
        describePrefix
    ) {})


    //TODO write spec for use case where assertions have been collected where subject will throw an exception
})
