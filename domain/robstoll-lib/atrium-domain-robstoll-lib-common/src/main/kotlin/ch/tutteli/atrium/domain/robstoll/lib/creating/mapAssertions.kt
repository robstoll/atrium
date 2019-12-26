@file:JvmMultifileClass
@file:JvmName("MapAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.IS_NOT
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion.EMPTY
import ch.tutteli.atrium.translations.DescriptionMapAssertion.CONTAINS_KEY
import ch.tutteli.atrium.translations.DescriptionMapAssertion.CONTAINS_NOT_KEY
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

fun <K> _containsKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K): Assertion =
    assertionBuilder.createDescriptive(subjectProvider, CONTAINS_KEY, key) { it.containsKey(key) }

fun <K> _containsNotKey(subjectProvider: SubjectProvider<Map<out K, *>>, key: K): Assertion =
    assertionBuilder.createDescriptive(subjectProvider, CONTAINS_NOT_KEY, key) { it.containsKey(key).not() }


fun _isEmpty(subjectProvider: SubjectProvider<Map<*, *>>): Assertion =
    assertionBuilder.createDescriptive(subjectProvider, IS, RawString.create(EMPTY)) { it.isEmpty() }

fun _isNotEmpty(subjectProvider: SubjectProvider<Map<*, *>>): Assertion =
    assertionBuilder.createDescriptive(subjectProvider, IS_NOT, RawString.create(EMPTY)) { it.isNotEmpty() }
