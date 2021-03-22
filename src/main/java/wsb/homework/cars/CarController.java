package wsb.homework.cars;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("cars")
@RestController
public class CarController {

    final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    CollectionModel<EntityModel<Car>> index() {

        List<EntityModel<Car>> movies = carRepository.find()
                .stream()
                .map(m -> EntityModel.of(m,
                        linkTo(methodOn(CarController.class).show(m.getId())).withSelfRel()))
                .collect(Collectors.toList());

        return CollectionModel.of(movies,
                linkTo(methodOn(CarController.class).index()).withSelfRel());
    }


//    List<Car> index() {
//        return carRepository.find();
//    }

    @GetMapping("{id}")
    ResponseEntity<EntityModel<Car>> show(@PathVariable Long id) {

        Car car = carRepository.find(id);

        if (car != null) {
            EntityModel<Car> carModel = EntityModel.of(car,
                    linkTo(methodOn(CarController.class).show(id)).withSelfRel(),
                    linkTo(methodOn(CarController.class).index()).withRel("movies"));

            return ResponseEntity.ok().body(carModel);
        } else {
            return ResponseEntity.notFound().build();
        }
//        return carRepository.find(id);
    }

    @PutMapping("{id}")
    ResponseEntity<EntityModel<Car>> update(@RequestBody Car car, @PathVariable Long id) {
        Car updatedCar = carRepository.update(car, id);

        if (updatedCar != null) {
            EntityModel<Car> carModel = EntityModel.of(updatedCar,
                    linkTo(methodOn(CarController.class).show(id)).withSelfRel());

            return ResponseEntity.ok().body(carModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    ResponseEntity<?> save(@RequestBody Car movie) {
        Car savedMovie = carRepository.save(movie);
        if (savedMovie != null) {
            EntityModel<Car> movieModel = EntityModel.of(savedMovie,
                    linkTo(methodOn(CarController.class).show(savedMovie.getId())).withSelfRel());

            return ResponseEntity.created(movieModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(movieModel);
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("{id}")
    String delete(@PathVariable Long id) {
        carRepository.delete(id);
        return "Został usunięty obiekt o id: "+ id;
    }
}
