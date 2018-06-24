package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import kotlin.reflect.KClass

actual class DetailedObjectFormatter actual constructor(
    translator: Translator
) : DetailedObjectFormatterCommon(translator), ObjectFormatter {

    override fun format(kClass: KClass<*>): String {
        TODO("not implemented")
    }

    override fun identityHash(indent: String, any: Any): String {
        TODO("not implemented")
    }
}
