package Faniot.ControlSensores.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sensor")
@SQLDelete(sql = "UPDATE sensor SET deleted = true WHERE id = ?")
@Getter
@Setter
@NoArgsConstructor
public class Sensor {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;


}
