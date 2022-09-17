//  HelloController - 문자타입을숫자타입으로변경

package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data");//문자 타입 조회, HTTP 요청파라미터는모두문자로처리된다.
        // 따라서 요청파라미터를 자바에서 다른타입으로 변환해서 사용하고 싶으면 다음과 같이 숫자타입으로 변환하는 과정을 거쳐야한다.
        Integer intValue = Integer.valueOf(data); //문자 타입을 숫자 타입으로 변경 <data>
        System.out.println("intValue = " + intValue);
        return "ok";
    }
    
    //  HelloController - 추가
    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        System.out.println("data = " + data);
        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        System.out.println("ipPort IP = " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());
        return "ok";
    }
}
