package hello.hellospring.controller;

public class MemberForm {

    private String name;    //html name="name"과 매칭. "name" == key

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
