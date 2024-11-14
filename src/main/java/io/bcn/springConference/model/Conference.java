package io.bcn.springConference.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "conferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conference {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false,
            nullable = false)
    private UUID id;
    private String conference;
    private String title;
    private Date date;
    private String youtube;
    private String content;
    private int duration;
    private String room;

    @ManyToOne
    @JoinColumn(name="book_id", nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name="speaker_id", nullable = false)
    private Speaker speaker;

}
