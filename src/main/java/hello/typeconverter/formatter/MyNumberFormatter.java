//  포맷터 - Formatter
//  Converter는입력과출력타입에제한이없는, 범용타입변환기능을제공한다.
//  이번에는일반적인웹애플리케이션환경을생각해보자. 불린타입을숫자로바꾸는것같은범용기능 보다는개발자입장에서는
//  문자를다른타입으로변환하거나, 다른타입을문자로변환하는상황이대부분이다.
//  앞서살펴본예제들을떠올려보면문자를다른객체로변환하거나객체를문자로변환하는일이 대부분이다.

//  웹애플리케이션에서객체를문자로, 문자를객체로변환하는예
//  화면에숫자를출력해야하는데, Integer String 출력시점에숫자1000 문자"1,000" 이렇게 1000 단위에쉼표를넣어서출력하거나, 또는"1,000"라는문자를1000이라는숫자로변경해야한다.
//  날짜객체를문자인"2021-01-01 10:50:11"와같이출력하거나또는그반대의상황

//  Locale
//  여기에추가로날짜숫자의표현방법은Locale 현지화정보가사용될수있다.

//  이렇게객체를특정한포멧에맞추어문자로출력하거나또는그반대의역할을하는것에특화된기능이 바로포맷터( Formatter)이다.
//  포맷터는컨버터의특별한버전으로이해하면된다.

//  Converter vs Formatter Converter는범용(객체 객체)
//  Formatter는문자에특화(객체 문자, 문자 객체) + 현지화(Locale) Converter의특별한버전

//  숫자1000을문자"1,000"으로그러니까, 1000 단위로쉼표가들어가는포맷을적용한다.

package hello.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {


    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        log.info("text={}, locale={}", text, locale);
        //"1,000" -> 1000
        NumberFormat format = NumberFormat.getInstance(locale);
        return format.parse(text);
    }

    @Override
    public String print(Number object, Locale locale) {
        log.info("object={}, locale={}", object, locale);
        return NumberFormat.getInstance(locale).format(object);
    }
}

//  "1,000"처럼숫자중간의쉼표를적용하려면자바가기본으로제공하는NumberFormat 객체를사용하면 된다.
//  이객체는Locale 정보를활용해서나라별로다른숫자포맷을만들어준다.
//  parse()를사용해서문자를숫자로변환한다. 참고로Number 타입은Integer, Long과같은숫자 타입의부모클래스이다.
//  print()를사용해서객체를문자로변환한다