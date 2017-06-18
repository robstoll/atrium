package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import java.util.*

internal class AssertionFormatterController : IAssertionFormatterController {
    private val assertionFormatters = ArrayDeque<IAssertionFormatter>()

    override fun format(assertion: IAssertion, methodObject: AssertionFormatterMethodObject) {
        if (!methodObject.assertionFilter(assertion)) return

        val assertionFormatter = assertionFormatters
            .firstOrNull { it.canFormat(assertion) }
            ?: IAssertionFormatterController.noSuitableAssertionFormatterFound(assertion)

        when (assertion) {
            is IAssertionGroup -> formatGroup(assertion, assertionFormatter, methodObject)
            else -> assertionFormatter.format(assertion, methodObject)
        }
    }

    fun formatGroup(assertionGroup: IAssertionGroup, assertionFormatter: IAssertionFormatter, methodObject: AssertionFormatterMethodObject) {
        assertionFormatter.formatGroup(assertionGroup, methodObject) { formatAssertionInGroup ->
            assertionGroup.assertions
                .filter { methodObject.assertionFilter(it) }
                .forEach(formatAssertionInGroup)
        }
    }

    override fun register(assertionFormatter: IAssertionFormatter) {
        assertionFormatters.add(assertionFormatter)
    }
}
