package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.it
import ch.tutteli.atrium.api.infix.en_GB.length
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CharSequenceFeatureExtractorSamples {

    @Test
    fun lengthFeature() {
        expect("Hello").length toEqual 5

        fails {
            expect("Hi").length toEqual 3
        }
    }

    @Test
    fun length() {
        expect("Hello") length {
            it toEqual 5
        }

        fails {
            expect("Hi") length {
                it toEqual 3
            }
        }
    }

}
