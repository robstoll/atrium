package ch.tutteli.atrium.api.fluent.java;

import custom.CustomBlock;
import custom.CustomException;
import org.junit.jupiter.api.Test;

import static ch.tutteli.atrium.api.fluent.java.Expects.expect;
import static custom.CustomExpects.expect;


public class JavaSampleTest {

    @Test
    public void toBeLessThan() {
        expect(1).toBeLessThan(3);
    }

    @Test
    public void feature() {
        expect(new Person()).feature("firstname", Person::getFirstname, ExpectFactories::expectString, it -> {
            it.toEqual("Robert");
        });

        expect(new Person()).feature("firstname", Person::getFirstname)
            .asInstanceOf(ExpectFactories::expectString)
            .toStartWith("Rob");
    }

    @Test
    public void toThrow() {
        expect((Block) () -> {
            throw new IllegalStateException("bla");
        }).toThrow(IllegalStateException.class);

        expect((CustomBlock) () -> {
            throw new CustomException("ERR-606");
        }).toThrowCustomException().getErrorCode().toEqual("ERR-606");
    }
}

class Person {

    String getFirstname() {
        return "Robert";
    }

    String getLastname() {
        return "Stoll";
    }
}
