package hello.hellospring.domain;

import javax.persistence.*;

//JPA
//ORM(Object Relational Mapping) 객체(Object)와 관계형 데이터베이스(Realtional database)를 매핑한다
//Annotation 으로 매핑을 한다.
//@Entity : JPA 가 관리하는 Entity
//@Id : PK (Primary Key)
//@GeneratedValue : Sequence
//GenerationType.IDENTITY : DB에서 알아서 Sequnce를 생성해주는 조건
//@Column(name = "username") : DB 칼럼명과 매핑해주는 name
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
