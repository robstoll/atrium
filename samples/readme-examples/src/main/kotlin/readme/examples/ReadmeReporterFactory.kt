package readme.examples

import ch.tutteli.atrium.core.robstoll.lib.reporting.AbstractDetailedObjectFormatter
import ch.tutteli.atrium.core.robstoll.lib.reporting.DetailedObjectFormatterCommon
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.atrium.reporting.translating.Translator
import kotlin.reflect.KClass

class ReadmeReporterFactory : ReporterFactory {
    override val id: String = ID

    override fun create(): Reporter {
        return ReporterBuilder.create()
            .withoutTranslationsUseDefaultLocale()
            .withObjectFormatter { translator -> ReadmeObjectFormatter(translator) }
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withTextCapabilities()
            .withMultipleAdjusters {
                withRemoveRunnerAtriumErrorAdjuster()
                withRemoveAtriumFromAtriumErrorAdjuster()
            }
            .withOnlyFailureReporter()
            .build()
    }

    companion object {
        const val ID: String = "ReadmeReporter"
    }
}

class ReadmeObjectFormatter(translator: Translator) : AbstractDetailedObjectFormatter(translator) {

    override fun identityHash(indent: String, any: Any): String =
        "$indent<1234789>" // dummy hash so it does not change all the time
}
