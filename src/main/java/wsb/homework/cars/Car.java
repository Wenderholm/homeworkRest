package wsb.homework.cars;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Car {
    Long id;
    String name;
    String producer;
    Double price;


    public Car(Long id, String name, String producer, Double price) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.price = price;
    }
}
