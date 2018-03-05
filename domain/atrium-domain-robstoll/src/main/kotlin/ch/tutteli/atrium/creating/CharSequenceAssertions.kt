package ch.tutteli.atrium.creating

/**
 * Robstoll's implementation of [ICharSequenceAssertions].
 */
object CharSequenceAssertions: ICharSequenceAssertions {

    override fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>)
        = _containsBuilder(plant)

    override fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>)
        = _containsNotBuilder(plant)


    override fun <T : CharSequence> startsWith(plant: AssertionPlant<T>, expected: CharSequence)
        = _startsWith(plant, expected)

    override fun <T : CharSequence> startsNotWith(plant: AssertionPlant<T>, expected: CharSequence)
        = _startsNotWith(plant, expected)

    override fun <T : CharSequence> endsWith(plant: AssertionPlant<T>, expected: CharSequence)
        = _endsWith(plant, expected)

    override fun <T : CharSequence> endsNotWith(plant: AssertionPlant<T>, expected: CharSequence)
        = _endsNotWith(plant, expected)

    override fun <T : CharSequence> isEmpty(plant: AssertionPlant<T>)
        = _isEmpty(plant)

    override fun <T : CharSequence> isNotEmpty(plant: AssertionPlant<T>)
        = _isNotEmpty(plant)
}
