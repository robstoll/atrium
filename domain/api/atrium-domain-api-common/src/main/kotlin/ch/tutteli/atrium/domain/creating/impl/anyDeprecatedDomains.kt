@file:Suppress("DEPRECATION" /* TODO remove file with 1.0.0 */)

package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.SubjectProviderDomain

internal class SubjectProviderDomainImpl<T>(override val subjectProvider: SubjectProvider<T>) :
    SubjectProviderDomain<T>
