//  사용자정의타입컨버터
//  타입컨버터이해를돕기위해조금다른컨버터를준비해보았다.
//  127.0.0.1:8080과같은 IP, PORT를입력하면 IpPort 객체로변환하는컨버터를만들어보자.
//  롬복의@EqualsAndHashCode를넣으면모든필드를사용해서equals(), hashcode()를생성한다.
//  따라서모든필드의값이같다면a.equals(b)의결과가참이된다.

package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class IpPort {

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
