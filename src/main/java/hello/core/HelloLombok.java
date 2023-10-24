package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("롬복");
        helloLombok.setAge(30);

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombokß = " + helloLombok);
    }
    // 롬복이 getter, setter를 자동으로 생성, 생성자 관련도 지원 여러 기능이 있으니 필요하면 찾아보기
}
