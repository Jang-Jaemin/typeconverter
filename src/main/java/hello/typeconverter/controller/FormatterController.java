//  스프링이제공하는기본포맷터
//  스프링은 자바에서 기본으로 제공하는 타입들에 대해 수 많은 포맷터를 기본으로 제공한다.
//  IDE에서 Formatter 인터페이스의 구현클래스를 찾아보면 수 많은 날짜나 시간 관련 포맷터가 제공되는것을 확인할 수 있다.
//  그런데 포맷터는 기본 형식이 지정되어있기 때문에, 객체의 각 필드마다 다른형식으로 포맷을 지정하기는 어렵다.
//  스프링은 이런 문제를 해결하기위해 애노테이션 기반으로 원하는 형식을 지정해서 사용할 수 있 는매우 유용한 포맷터 두가지를 기본으로제공한다.

//  @NumberFormat: 숫자관련형식지정포맷터사용, NumberFormatAnnotationFormatterFactory
//  @DateTimeFormat: 날짜관련형식지정포맷터사용,
//  Jsr310DateTimeFormatAnnotationFormatterFactory

package hello.typeconverter.controller;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class FormatterController {

    @GetMapping("/formatter/edit")
    public String formatterForm(Model model) {
        Form form = new Form();
        form.setNumber(10000);
        form.setLocalDateTime(LocalDateTime.now());
        model.addAttribute("form", form);
        return "formatter-form";
    }

    @PostMapping("/formatter/edit")
    public String formatterEdit(@ModelAttribute Form form) {
        return "formatter-view";
    }

    @Data
    static class Form {
        @NumberFormat(pattern = "###,###")
        private Integer number;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime localDateTime;
    }
}


//  마지막 < 정리 >
//  컨버터를사용하든, 포맷터를사용하든등록방법은다르지만, 사용할때는컨버전서비스를통해서일관성 있게사용할수있다.

//  메시지컨버터( HttpMessageConverter)에는컨버전서비스가적용되지않는다.
//  특히객체를 JSON으로변환할때메시지컨버터를사용하면서이부분을많이오해하는데, HttpMessageConverter의역할은 HTTP 메시지바디의내용을객체로변환하거나객체를 HTTP 메시지 바디에입력하는것이다.
//  예를들어서 JSON을객체로변환하는메시지컨버터는내부에서 Jackson 같은 라이브러리를사용한다.
//  객체를 JSON으로변환한다면그결과는이라이브러리에달린것이다.
//  따라서 JSON 결과로만들어지는숫자나날짜포맷을변경하고싶으면해당라이브러리가제공하는설정을통해서 포맷을지정해야한다.
//  결과적으로이것은컨버전서비스와전혀관계가없다.
//  컨버전서비스는@RequestParam, @ModelAttribute, @PathVariable, 뷰템플릿등에서사용할수 있다.