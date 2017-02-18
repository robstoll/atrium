package ch.tutteli.assertk

class OnlyFailureReporter(private val assertionMessageFormatter: IAssertionMessageFormatter) : IReporter {

    override fun format(sb: StringBuilder, assertion: IAssertion)
        = assertionMessageFormatter.format(sb, assertion, { !it.holds })
}
