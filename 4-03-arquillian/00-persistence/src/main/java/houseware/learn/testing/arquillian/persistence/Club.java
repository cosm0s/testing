package houseware.learn.testing.arquillian.persistence;

import lombok.Data;

import javax.persistence.*;

/**
 * @author fphilip@houseware.es
 */
@Data
@Entity
public class Club {
    @Id
    @Column
    private String id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private League league;

}
