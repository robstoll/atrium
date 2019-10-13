package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.CharSequenceAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*


class CharSequenceAssertionsImpl : CharSequenceAssertions {

    override fun <T : CharSequence> containsBuilder(subjectProvider: SubjectProvider<T>) =
        _containsBuilder(subjectProvider)

    override fun <T : CharSequence> containsNotBuilder(subjectProvider: SubjectProvider<T>) =
        _containsNotBuilder(subjectProvider)

    
    override fun startsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence) =
        _startsWith(subjectProvider, expected)

    override fun startsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence) =
        _startsNotWith(subjectProvider, expected)

    override fun endsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence) =
        _endsWith(subjectProvider, expected)

    override fun endsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence) =
        _endsNotWith(subjectProvider, expected)

    override fun isEmpty(subjectProvider: SubjectProvider<CharSequence>) = _isEmpty(subjectProvider)

    override fun isNotEmpty(subjectProvider: SubjectProvider<CharSequence>) = _isNotEmpty(subjectProvider)

    override fun isNotBlank(subjectProvider: SubjectProvider<CharSequence>) = _isNotBlank(subjectProvider)

    override fun <T : CharSequence> matches(assertionContainer: Expect<T>, expected: Regex) = _matches(assertionContainer, expected)

    override fun <T : CharSequence> mismatches(assertionContainer: Expect<T>, expected: Regex) = _mismatches(assertionContainer, expected)
}
