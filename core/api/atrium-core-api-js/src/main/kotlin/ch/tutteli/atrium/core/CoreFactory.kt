package ch.tutteli.atrium.core

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.ReportingAssertionPlantNullable
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.core.newReportingPlantNullable as newReportingPlantNullableFromCommon

actual interface CoreFactory : CoreFactoryCommon {

    // we need to define the following methods here so that we can retain binary backward compatibility in JVM
    // => Kotlin generates an object called CoreFactory$DefaultImpls due to the optional parameters
    // hence we need to place the methods here and cannot move them to CoreFactoryCommon as well

    /**
     * Creates a [ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking,
     * uses [subjectProvider] as [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
     * [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid side effects
     * (the provider is most likely called more than once).
     *
     * Notice, this method will be moved to [CoreFactoryCommon] with 0.10.0.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subjectProvider Used as [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
     *   also as [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * @param reporter The reporter which will be used for a [newThrowingAssertionChecker].
     *
     *
     * @return The newly created assertion plant.
     */
    @Suppress("DEPRECATION")
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead; will be removed with 1.0.0\"",
        ReplaceWith(
            "ExpectImpl\n" +
                ".assertionVerbBuilder(\n" +
                "// define the subject here instead of subjectProvider(), for instance just `subject` instead of `{ subject }`\n" +
                "// in case you have a transformation from an existing subject, then use this.maybeSubject.map { transform(it) }\n" +
                "subjectProvider()\n" +
                ")\n" +
                ".withVerb(assertionVerb)\n" +
                ".withCustomReporter(reporter)\n" +
                ".build()",
            "ch.tutteli.atrium.domain.builders.ExpectImpl"
        )
    )
    fun <T : Any?> newReportingPlantNullable(
        assertionVerb: Translatable,
        subjectProvider: () -> T,
        reporter: Reporter,
        nullRepresentation: Any = RawString.NULL
    ): ReportingAssertionPlantNullable<T> = newReportingPlantNullableFromCommon(
        assertionVerb, subjectProvider, reporter, nullRepresentation
    )

    /**
     * Creates a [ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It uses the given [assertionChecker] for assertion checking, uses [subjectProvider] as
     * [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.subjectProvider] but also as
     * [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * Notice that [evalOnce] is applied to the given [subjectProvider] to avoid side effects
     * (the provider is most likely called more than once).
     *
     * Notice, this method will be moved to [CoreFactoryCommon] with 0.10.0.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subjectProvider Used as [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.subjectProvider] but
     *   also as [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.representationProvider].
     * @param assertionChecker The checker which will be used to check [Assertion]s.
     *   (see [ch.tutteli.atrium.creating.AssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    @Suppress("DEPRECATION")
    @Deprecated(
        "Switch to Expect instead of Assert, thus use newReportingAssertionContainer instead; will be removed with 1.0.0\"",
        ReplaceWith(
            "this.newReportingAssertionContainer(\n" +
                "assertionVerb,\n" +
                "// define the subject here instead of subjectProvider(), for instance just `subject` instead of `{ subject }`\n" +
                "// in case you have a transformation from an existing subject, then use this.maybeSubject.map { transform(it) }\n" +
                "Some(subjectProvider()),\n" +
                "assertionChecker\n" +
                ")",
            "ch.tutteli.atrium.core.coreFactory",
            "ch.tutteli.atrium.core.Some",
            "ch.tutteli.atrium.domain.builders.ExpectImpl"
        )
    )
    fun <T : Any?> newReportingPlantNullable(
        assertionVerb: Translatable,
        subjectProvider: () -> T,
        assertionChecker: AssertionChecker,
        nullRepresentation: Any = RawString.NULL
    ): ReportingAssertionPlantNullable<T> = newReportingPlantNullableFromCommon(
        assertionVerb, subjectProvider, assertionChecker, nullRepresentation
    )
}
