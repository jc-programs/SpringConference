package io.bcn.springConference.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
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


    // https://stackoverflow.com/questions/17298314/java-vaadin-nativeselect-setvalue-not-working/17299605#17299605
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Speaker speaker)) return false;
        return Objects.equals(id, speaker.id) && Objects.equals(name, speaker.name) &&
                Objects.equals(bio, speaker.bio) && Objects.equals(email, speaker.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bio, email);
    }
}
