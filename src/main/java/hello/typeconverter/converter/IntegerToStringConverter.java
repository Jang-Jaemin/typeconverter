//  IntegerToStringConverter - 숫자를문자로변환하는타입컨버터
//  여기서는 숫자를 문자로 변환하는 타입 컨버터이다.
//  앞의 컨버터들과는 반대의 일을 한다.
//  이번에는숫자가 입력되기때문에소스가Integer가된다.
//  String.valueOf(source)를사용해서문자로변경한다음 변경된문자를반환하면된다.

package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IntegerToStringConverter implements Converter<Integer, String> {

    @Override
    public String convert(Integer source) {
        log.info("convert source={}", source);
        return String.valueOf(source);
    }
}
