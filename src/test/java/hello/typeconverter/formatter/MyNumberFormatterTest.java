package hello.typeconverter.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MyNumberFormatterTest {

    MyNumberFormatter formatter = new MyNumberFormatter();

    @Test
    void parse() throws ParseException {
        Number result = formatter.parse("1,000", Locale.KOREA);
        assertThat(result).isEqualTo(1000L); //Long 타입 주의
    }

    @Test
    void print() {
        String result = formatter.print(1000, Locale.KOREA);
        assertThat(result).isEqualTo("1,000");
    }
}

//  parse()의결과가Long이기때문에isEqualTo(1000L)을통해비교할때마지막에L을넣어주어야 한다.

//  참고
//  스프링은용도에따라다양한방식의포맷터를제공한다. > Formatter 포맷터
//  AnnotationFormatterFactory 필드의타입이나애노테이션정보를활용할수있는포맷터이다.