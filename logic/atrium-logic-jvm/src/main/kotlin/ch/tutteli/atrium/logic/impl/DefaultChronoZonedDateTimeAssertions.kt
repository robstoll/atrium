@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.*
import java.time.*
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.TemporalQuery

class DefaultChronoZonedDateTimeAssertions : ChronoZonedDateTimeAssertions {
    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBefore(
        container: AssertionContainer<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(IS_BEFORE, expected) { it.isBefore(expected) }

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(IS_BEFORE_OR_EQUAL, expected) {
        it.isBefore(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isAfter(
        container: AssertionContainer<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(IS_AFTER, expected) { it.isAfter(expected) }

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(IS_AFTER_OR_EQUAL, expected) {
        it.isAfter(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isEqual(
        container: AssertionContainer<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(IS_EQUAL_TO, expected) {
        it.isEqual(expected)
    }

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBefore(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion {
        return container.isBefore(ZonedDateTime.parse(expected))
    }

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion {
        return container.isBeforeOrEqual(ZonedDateTime.parse(expected))
    }

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isAfter(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion {
        return container.isAfter(parseZonedDateTime(expected))
    }

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion {
        return container.isAfterOrEqual(parseZonedDateTime(expected))
    }

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion {
        return container.isEqual(parseZonedDateTime(expected))
    }

    private fun parseZonedDateTime(data: String): ZonedDateTime {
        val formatter = DateTimeFormatterBuilder()
            .parseCaseSensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .optionalStart().appendLiteral('T').append(DateTimeFormatter.ISO_LOCAL_TIME).optionalEnd()
            .optionalStart().appendLiteral('Z').optionalStart().appendOffsetId().optionalEnd().optionalEnd()
            .toFormatter()

        val parsed = formatter.parseBest(
            data,
            TemporalQuery { temporal -> ZonedDateTime.from(temporal) },
            TemporalQuery { temporal -> LocalDateTime.from(temporal) },
            TemporalQuery { temporal -> LocalDate.from(temporal) }
        )

        return when (parsed) {
            is LocalDate -> {
                val parts = data.split("Z")
                val zoneOffset = if (parts.size > 1) parts.last() else null
                zoneOffset.let {
                    if (it == null)
                        parsed.atStartOfDay(ZoneId.of("Z"))
                    else
                        parsed.atStartOfDay(ZoneOffset.of(it))
                }
            }
            is LocalDateTime -> parsed.atZone(ZoneId.of("Z"))
            is ZonedDateTime -> parsed
            else -> TODO("Throw a DateTimeParseException? Some information is missing though, like the errorIndex")
        }
    }
}
