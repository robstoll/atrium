@file:Suppress("NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.creating.ExpectDomain

inline fun <T> ExpectDomain<T>.changeSubject() = SubjectChangerBuilder.create(expect)
