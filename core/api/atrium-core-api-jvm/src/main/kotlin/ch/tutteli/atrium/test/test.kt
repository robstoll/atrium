@file:Suppress("KDocMissingDocumentation")

package ch.tutteli.atrium.test

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.kbox.appendToStringBuilder

interface Expect<T> {
    val subject: T
    fun reportFailure(expects: List<Expect<*>>)
    fun getAssertions(): List<Assertion>
    val node: NodeExpect<*>
}

interface CharSequenceAssertions {

    fun startsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion
    fun startsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion
    fun endsWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion
    fun endsNotWith(subjectProvider: SubjectProvider<CharSequence>, expected: CharSequence): Assertion
    fun isEmpty(subjectProvider: SubjectProvider<CharSequence>): Assertion
    fun isNotEmpty(subjectProvider: SubjectProvider<CharSequence>): Assertion
    fun isNotBlank(subjectProvider: SubjectProvider<CharSequence>): Assertion
    fun <T : CharSequence> matches(assertionContainer: Expect<T>, expected: Regex): Assertion
    fun <T : CharSequence> mismatches(assertionContainer: Expect<T>, expected: Regex): Assertion
}

class RootExpectImpl<T>(override val subject: T) : RootExpect<T> {
    val description = "expect"
    val representation = subject
    override val node = this

    override fun reportFailure(expects: List<Expect<*>>) {
        println("expects: $expects")
        val sb = StringBuilder()
        expects.appendToStringBuilder(sb, "\n") { expect ->
            println("da: $expect")
            expect.getAssertions().appendToStringBuilder(sb, "\n  ") {
                sb.append(it)
            }
        }
        throw AssertionError(sb.toString())
    }

    override fun getAssertions(): List<Assertion> = throw UnsupportedOperationException()
}

abstract class LeaveExpect<T>(
    override val node: NodeExpect<*>,
    override val subject: T,
    val assertion: List<Assertion>
) : Expect<T> {
    override fun reportFailure(expects: List<Expect<*>>): Unit = throw IllegalStateException("bla")
    override fun getAssertions(): List<Assertion> = assertion
}

class ExpectImpl<T>(previous: NodeExpect<*>, subject: T, assertion: List<Assertion>) :
    LeaveExpect<T>(previous, subject, assertion)

class ExplainingExpect<T>(previous: NodeExpect<*>, subject: T, assertion: List<Assertion>) :
    LeaveExpect<T>(previous, subject, assertion) {
    init {
        previous.reportFailure(listOf(this))
    }
}

interface NodeExpect<T> : Expect<T>
interface RootExpect<T> : NodeExpect<T>
interface FeatureExpect<T> : NodeExpect<T>

class FeatureExpectImpl<T>(override val node: NodeExpect<*>, override val subject: T) : FeatureExpect<T> {
    override fun reportFailure(expects: List<Expect<*>>) {
        println("here: $expects / $this = $node")
        node.reportFailure(expects + this)
    }

    override fun getAssertions(): List<Assertion> = listOf()

}

interface Config<T> {
    infix fun withRepresentation(s: String)
    infix fun withSubjectBasedRepresentation(provider: (T) -> String)
    val o: Config<T>
}

infix fun <T> RootExpect<T>._override(c: Config<T>.() -> Unit): Expect<T> = TODO()
fun <T> expect(subject: T): RootExpect<T> = RootExpectImpl(subject)

infix fun <T> Expect<T>.toBe(t: T): Expect<T> {
    val assertion = assertionBuilder.createDescriptive("bla", t) { subject == t }
    val factory: (NodeExpect<*>, T, List<Assertion>) -> Expect<T> =
        if (assertion.holds()) ::ExpectImpl else ::ExplainingExpect

    return when (this) {
        is NodeExpect -> factory(this, subject, listOf(assertion))
        is LeaveExpect -> factory(this.node, subject, getAssertions() + assertion)
        else -> throw IllegalStateException("non supported ")
    }
}


infix fun <T> Expect<T>.feature(i: Int): FeatureExpect<Int> = FeatureExpectImpl(this.node, i)

fun <T> FeatureExpect<T>._override(c: Config<T>.() -> Unit): Expect<T> = TODO()

inline fun <reified T> withImpl(f: (T) -> T) {}

fun main(args: Array<String>) {
    val e1 = expect(1)
        .toBe(1)
        .toBe(1)
        .feature(2)
        .toBe(2)
        .feature(1)
        .toBe(3)


//        ._override { withRepresentation("a") }
//        .toBe(listOf())
//        .feature(1)
//        ._override {
//            withSubjectBasedRepresentation { it.joinToString { "bla" } }
//        }
//        .toBe(listOf(2))
}

interface A<T>
class B<T>(int: Int, t: T, l: List<String>) : A<T>

fun <T> B<T>.test() {
    val factory: (Int, T, List<String>) -> A<T> = ::B
}
