package readme.examples

import ch.tutteli.atrium.core.robstoll.lib.reporting.DetailedObjectFormatterCommon
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.atrium.reporting.translating.Translator
import kotlin.reflect.KClass

class ReadmeReporterFactory : ReporterFactory {
    override val id: String = ID

    override fun create(): Reporter {
        return ExpectImpl.reporterBuilder
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
        const val ID = "ReadmeReporter"
    }
}

class ReadmeObjectFormatter(translator: Translator) : DetailedObjectFormatterCommon(translator) {
    override fun format(value: Any?): String = when (value) {
        is Class<*> -> format(value)
        else -> super.format(value)
    }

    private fun format(clazz: Class<*>) = "${clazz.simpleName} (${clazz.name})"

    override fun format(kClass: KClass<*>): String {
        val kotlinClass = "${kClass.simpleName} (${kClass.qualifiedName})"
        return when {
            kClass.qualifiedName == kClass.java.name -> kotlinClass
            kClass.java.isPrimitive -> "$kotlinClass -- Class: ${kClass.java.simpleName}"
            else -> "$kotlinClass -- Class: ${format(kClass.java)}"
        }
    }

    override fun identityHash(indent: String, any: Any): String =
        "$indent<1234789>" // dummy hash so it does not change all the time
}
