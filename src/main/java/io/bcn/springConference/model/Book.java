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
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false,
            nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique = true)
    private String ISBN;

/*    @OneToMany(mappedBy = "book")
    private List<Conference> conferences;*/

    // https://stackoverflow.com/questions/17298314/java-vaadin-nativeselect-setvalue-not-working/17299605#17299605
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Book)){
            return false;
        }
        Book that = (Book) obj;
        return this.id.equals(that.id) &&
                this.author.equals(that.author) &&
                this.title.equals(that.title) &&
                this.ISBN.equals(that.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, ISBN);
    }
}
