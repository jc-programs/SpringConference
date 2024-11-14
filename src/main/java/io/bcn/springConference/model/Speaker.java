package io.bcn.springConference.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "speakers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Speaker {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false,
            nullable = false)
    private UUID id;
    private String name;
    private String bio;
    private String email;

//    @ManyToOne
//    @JoinColumn(name = "conference_id")
//    private Conference conference;
}
