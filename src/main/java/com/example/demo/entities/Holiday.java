package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {

    @Id
    private final String day;

    public Holiday() {
        this.day = "null";
        this.reason = "null";
        this.type = Type.FESTIVAL;
    }

    private final String reason;

    @Enumerated(EnumType.STRING)
    private final Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }


}
