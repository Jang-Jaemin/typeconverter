//  StringToIntegerConverter - 문자를숫자로변환하는타입컨버터
//  String   Integer로변환하기때문에소스가String이된다. 이문자를
//  Integer.valueOf(source)를사용해서숫자로변경한다음에변경된숫자를반환하면된다.

package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        log.info("convert source={}", source);
        return Integer.valueOf(source);
    }
}
