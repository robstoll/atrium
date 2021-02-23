package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.text.TextAssertionFormatter

interface TextAssertionFormatterFactory {
    fun build(assertionFormatterController: AssertionFormatterController): TextAssertionFormatter

    companion object {
        operator fun invoke(
            f: (AssertionFormatterController) -> TextAssertionFormatter
        ): TextAssertionFormatterFactory = DefaultTextAssertionFormatterFactory(f)
    }
}

private class DefaultTextAssertionFormatterFactory(
    private val f: (AssertionFormatterController) -> TextAssertionFormatter
) : TextAssertionFormatterFactory {

    override fun build(assertionFormatterController: AssertionFormatterController): TextAssertionFormatter =
        f(assertionFormatterController)
}

