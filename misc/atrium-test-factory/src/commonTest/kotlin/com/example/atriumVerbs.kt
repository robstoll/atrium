@file:Suppress("UNUSED_PARAMETER", "UnusedReceiverParameter")

package com.example

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping

// dummy implementations used in samples
fun <T> expect(subject: T): Expect<T> = TODO()
fun <T> expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> = TODO()
fun expectGrouped(description: String, groupingActions: ExpectGrouping.() -> Unit): ExpectGrouping = TODO()
fun <T> ExpectGrouping.expect(subject: T): Expect<T> = TODO()
fun <T> ExpectGrouping.expect(subject: T, expectationCreator: Expect<T>.() -> Unit): Expect<T> = TODO()
