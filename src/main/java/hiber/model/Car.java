package hiber.model;


import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car")
    @JoinColumn(name = "user_id")
    private User user;

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    //Getters
    public int getSeries() {
        return series;
    }
    public String getModel() {
        return model;
    }
    public User getUser() {
        return user;
    }
    public int getId() {
        return id;
    }

    //Setters
    public void setSeries(int series) {
        this.series = series;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return this.model + ", " + this.series;
    }
}