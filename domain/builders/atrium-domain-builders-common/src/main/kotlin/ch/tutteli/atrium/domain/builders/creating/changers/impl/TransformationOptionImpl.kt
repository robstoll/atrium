package ch.tutteli.atrium.domain.builders.creating.changers.impl

import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder

class TransformationOptionImpl<T>(
    override val checkOption: SubjectChangerBuilder.CheckOption<T>,
    override val canBeTransformed: (T) -> Boolean
) : SubjectChangerBuilder.TransformationOption<T> {

    override fun <R> withTransformation(transformation: (T) -> R): SubjectChangerBuilder.SubAssertionOption<T, R> =
        SubjectChangerBuilder.SubAssertionOption.create(checkOption, canBeTransformed, transformation)
}
