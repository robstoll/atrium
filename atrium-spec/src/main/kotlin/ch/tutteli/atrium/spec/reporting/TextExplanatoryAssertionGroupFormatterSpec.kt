package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroup
import ch.tutteli.atrium.assertions.IExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

abstract class TextExplanatoryAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (String, IAssertionFormatterController) -> IAssertionFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : ch.tutteli.atrium.spec.reporting.IndentBasedAssertionGroupFormatterSpec<IExplanatoryAssertionGroupType>(
        verbs,
        testeeFactory,
        IExplanatoryAssertionGroupType::class.java,
        object : IExplanatoryAssertionGroupType {},
        { ExplanatoryAssertionGroup(it) },
        2,
        describePrefix
    ) {})


    //TODO write spec for use case where assertions have been collected where subject will throw an exception
})
