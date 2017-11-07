package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.translating.ITranslatable
import kotlin.reflect.KClass

class DownCastBuilderSpec : ch.tutteli.atrium.spec.creating.DownCastBuilderSpec(
    AssertionVerbFactory, Companion::create) {
    companion object {
        fun create(description: ITranslatable, subclass: KClass<Int>, subjectPlant: IBaseAssertionPlant<Number?, *>, createAssertions: IAssertionPlant<Int>.() -> Unit): IDownCastBuilder<Number, Int>
            = DownCastBuilder(description, subclass, subjectPlant, createAssertions)
    }
}
