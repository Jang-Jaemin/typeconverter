//  컨버전서비스 - ConversionService
//  이렇게타입컨버터를하나하나직접찾아서타입변환에사용하는것은매우불편하다.
//  그래서스프링은 개별컨버터를모아두고그것들을묶어서편리하게사용할수있는기능을제공하는데,
//  이것이바로컨버전 서비스( ConversionService)이다.

package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        //등록
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        //사용
        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");

        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        String ipPortString = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(ipPortString).isEqualTo("127.0.0.1:8080");

    }
}

//  컨버전 서비스 인터페이스는 단순히 컨버팅이가능한가? 확인하는 기능과, 컨버팅 기능을 제공한다.

//  등록과사용분리
//  컨버터를 등록할때는 StringToIntegerConverter 같은 타입 컨버터를 명확하게 알아야한다.
//  반면에 컨버터를 사용하는 입장에서는 타입 컨버터를 전혀 몰라도 된다.
//  타입 컨버터들은 모두 컨버전 서비스 내부에 숨어서 제공된다.
//  따라서 타입을 변환을 원하는 사용자는 컨버전 서비스 인터페이스에만 의존하면 된다.
//  물론 컨버전 서비스를 등록하는 부분과 사용하는 부분을 분리하고 의존관계 주입을 사용해야한다.

//  컨버전서비스사용
//  Integer value = conversionService.convert("10", Integer.class)

//  인터페이스분리원칙 - ISP(Interface Segregation Principle)
//  인터페이스분리원칙은클라이언트가자신이이용하지않는메서드에의존하지않아야한다.

//  DefaultConversionService는다음두인터페이스를구현했다.
//  ConversionService: 컨버터사용에초점 ConverterRegistry: 컨버터등록에초점

//  이렇게인터페이스를분리하면컨버터를사용하는클라이언트와컨버터를등록하고관리하는클라이언트의 관심사를명확하게분리할수있다.
//  특히컨버터를사용하는클라이언트는ConversionService만의존하면되므로, 컨버터를어떻게등록하고관리하는지는전혀몰라도된다. 결과적으로컨버터를 사용하는클라이언트는꼭필요한메서드만알게된다.
//  이렇게인터페이스를분리하는것을ISP라한다.

//  스프링은내부에서ConversionService를사용해서타입을변환한다.
//  예를들어서앞서살펴본 @RequestParam 같은곳에서이기능을사용해서타입을변환한다.
//  이제컨버전서비스를스프링에적용해보자.