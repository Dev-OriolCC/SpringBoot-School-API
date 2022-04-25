package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
