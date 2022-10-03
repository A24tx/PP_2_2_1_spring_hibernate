package hiber.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private User user;

    public Car() {
        //
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getSeries() {
        return series;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return getId() == car.getId() && getSeries() == car.getSeries() && getModel().equals(car.getModel()) && Objects.equals(user, car.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getModel(), getSeries(), user);
    }

}
