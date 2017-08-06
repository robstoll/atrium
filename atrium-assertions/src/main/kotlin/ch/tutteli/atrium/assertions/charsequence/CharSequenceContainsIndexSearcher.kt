package ch.tutteli.atrium.assertions.charsequence

class CharSequenceContainsIndexSearcher<T: CharSequence> : CharSequenceContainsAssertionCreator.ISearcher<T> {
    override fun search(searchIn: CharSequence, searchFor: Any): Int {
        val expected = searchFor.toString()
        var index = searchIn.indexOf(expected)
        var counter = 0
        while (index >= 0) {
            index = searchIn.indexOf(expected, index + 1)
            ++counter
        }
        return counter
    }
}
