package wsb.homework.cars;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CarRepository {

    static Long currentId = 5L;

    static final List<Car> cars = new LinkedList<>(Arrays.asList(
            new Car(1L,"Avensis","Toyota", 3500.0),
            new Car(2L,"Z5","BMW", 13500.0),
            new Car(3L,"305","Peugeot", 4500.0),
            new Car(4L,"X6","Mazda", 6500.0)
    ));

    List<Car> find(){
        return cars;
    }

    Car find(Long id) {
        return cars.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    Car save(Car car) {
        car.id = currentId++;
        cars.add(car);
        return car;
    }

    Car update(Car car, Long id) {
        Car carToUpdate = find(car.getId());
        carToUpdate.setName(car.getName());
        carToUpdate.setProducer(car.getProducer());
        carToUpdate.setPrice(car.getPrice());
        return carToUpdate;
    }

    void delete(Long id) {
        Car movie = find(id);
        cars.remove(movie);
    }
}
