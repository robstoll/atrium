package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionCollectionAssertion.EMPTY
import ch.tutteli.atrium.assertions.DescriptionCollectionAssertion.HAS_SIZE
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

fun <T : Collection<*>> _hasSize(plant: IAssertionPlant<T>, size: Int): IAssertion
    = BasicAssertion(HAS_SIZE, size, { plant.subject.size == size })

fun <T : Collection<*>> _isEmpty(plant: IAssertionPlant<T>): IAssertion
    = BasicAssertion(DescriptionBasic.IS, TranslatableRawString(EMPTY), { plant.subject.isEmpty() })
