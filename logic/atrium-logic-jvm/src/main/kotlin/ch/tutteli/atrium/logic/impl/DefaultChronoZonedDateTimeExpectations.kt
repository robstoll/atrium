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
    ): Assertion = container.isBefore(parseZonedDateTime(expected))

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isBeforeOrEqual(parseZonedDateTime(expected))

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isAfter(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isAfter(parseZonedDateTime(expected))

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isAfterOrEqual(parseZonedDateTime(expected))

    override fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isEqual(parseZonedDateTime(expected))

    private fun parseZonedDateTime(data: String): ZonedDateTime {
        val formatter = DateTimeFormatterBuilder()
            .parseCaseSensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .optionalStart().appendLiteral('T').append(DateTimeFormatter.ISO_LOCAL_TIME).optionalEnd()
            .appendOffsetId()
            .toFormatter()

        val parsed = formatter.parseBest(
            data,
            TemporalQuery { temporal -> ZonedDateTime.from(temporal) },
            TemporalQuery { temporal -> LocalDateTime.from(temporal) },
            TemporalQuery { temporal -> LocalDate.from(temporal) }
        )

        return when (parsed) {
            is LocalDate -> {
                when {
                    /**
                     * Covers the input: yyyy-mm-ddZ
                     */
                    "Z" in data -> {
                        parsed.atStartOfDay(ZoneOffset.UTC)
                    }
                    /**
                     * Covers the input: yyyy-mm-dd+hh:mm
                     */
                    "+" in data -> {
                        val parts = data.split("+")
                        parsed.atStartOfDay(ZoneOffset.of("+${parts.last()}"))
                    }
                    /**
                     * Covers the input: yyyy-mm-dd-hh:mm
                     */
                    "-" in data -> {
                        val parts = data.split("-")
                        parsed.atStartOfDay(ZoneOffset.of("-${parts.last()}"))
                    }
                    /**
                     * Generate an appropriate exception if the zone offset is not known
                     */
                    else -> ZonedDateTime.parse(data)
                }
            }
            is LocalDateTime -> parsed.atZone(ZoneOffset.UTC)
            is ZonedDateTime -> parsed
            /**
             * If neither case should match, than we can still try to parse the [data]
             * with the native implementation to generate an exception which is more descriptive.
             *
             * In reality this case won't be invoked, because [DateTimeFormatter.parseBest]
             * would throw a [java.time.format.DateTimeParseException] either way and stop the execution flow at this point.
             */
            else -> ZonedDateTime.parse(data)
        }
    }
}
