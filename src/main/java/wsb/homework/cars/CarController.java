package wsb.homework.cars;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("cars")
@RestController
public class CarController {

    final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    List<Car> index() {
        return carRepository.find();
    }

    @GetMapping("{id}")
    Car show(@PathVariable Long id) {
        return carRepository.find(id);
    }

    @PutMapping
    Car update(@RequestBody Car car) {
        return carRepository.update(car);
    }

    @PostMapping
    Car save(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @DeleteMapping("{id}")
    String delete(@PathVariable Long id) {
        carRepository.delete(id);
        return "Został usunięty obiekt o id: "+ id;
    }
}
