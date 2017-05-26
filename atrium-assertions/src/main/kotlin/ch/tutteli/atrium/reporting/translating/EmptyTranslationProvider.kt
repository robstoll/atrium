package ch.tutteli.atrium.reporting.translating

import java.util.*

object EmptyTranslationProvider : ITranslationProvider {
    override fun get(locale: Locale) = emptyMap<ITranslatable, String>()
}
