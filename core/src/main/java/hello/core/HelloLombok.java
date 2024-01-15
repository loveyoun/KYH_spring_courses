package hello.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class HelloLombok {

    private String name;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("helloLombokName");

        // ToString
        System.out.println("helloLombok = " + helloLombok);

        System.out.println("name = " + helloLombok.getName());
    }

}
