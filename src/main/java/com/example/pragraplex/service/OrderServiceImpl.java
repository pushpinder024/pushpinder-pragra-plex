package com.example.pragraplex.service;

import com.example.pragraplex.entity.Customer;
import com.example.pragraplex.entity.Movie;
import com.example.pragraplex.entity.Order;
import com.example.pragraplex.exceptions.CustomerNotFoundException;
import com.example.pragraplex.exceptions.MovieNotFoundException;
import com.example.pragraplex.exceptions.OrderNotFoundException;
import com.example.pragraplex.repo.OrderRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo repo;
    private final CustomerService customerService;
    private final MovieService movieService;

    public OrderServiceImpl(OrderRepo repo, CustomerService customerService, MovieService movieService) {
        this.repo = repo;
        this.customerService = customerService;
        this.movieService = movieService;
    }

    //    @Override
//    public Order createOrder(int custId,Order order) {
//        Optional<Customer> customerById = customerService.getCustomerById(custId);
//        Optional<Movie> movieById = movieService.getMovieById(order.getMovie().getMovieId());
//        if (customerById.isPresent() && movieById.isPresent()) {
//            order.setCustomer(customerById.get());
//            order.setMovie(movieById.get());
//            return repo.save(order);
//        } else {
//            throw new CustomerNotFoundException(String.format("Customer with id %d not found", custId));
//        }
//    }
    @Override
    @Transactional
    public Order createOrder(int custId, Order order) {
        Optional<Customer> customerById = customerService.getCustomerById(custId);
        Optional<Movie> movieById = movieService.getMovieById(order.getMovie().getMovieId());
        log.debug("Customer id {} provided to create order", custId);
        Movie movie = movieById.orElseThrow(
                () -> new MovieNotFoundException(String.format("Movie with Id %d not found", order.getMovie().getMovieId())));
        Customer customer = customerById.orElseThrow(
                () -> new CustomerNotFoundException(String.format("Customer with id %d not found", custId))
        );

        order.setCustomer(customerById.get());
        order.setMovie(movieById.get());
        return repo.save(order);

    }

    @Override
    public List<Order> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Order> findOneById(Integer orderId) {
        return repo.findById(orderId);
    }

    @Override
    public void deleteById(int orderId) {
        Order order = repo.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order with id %d not found", orderId))
        );
        repo.deleteById(orderId);
    }

    @Override
    public List<Order> createMultipleOrders(int custId, List<Order> orders) {
        Optional<Customer> customerById = customerService.getCustomerById(custId);
        Customer customer = customerById.orElseThrow(
                () -> new CustomerNotFoundException(String.format("Customer with Id %d not found", custId))
        );

        orders.forEach(order -> order.setCustomer(customer));

        orders.forEach(order -> {
                    Optional<Movie> movieById = movieService.getMovieById(order.getMovie().getMovieId());
                    Movie movie = movieById.orElseThrow(
                            () -> new MovieNotFoundException(String.format("Movie with Id %d not found", order.getMovie().getMovieId()))
                    );
                    order.setMovie(movie);

                }
        );

        return repo.saveAll(orders);
    }

}
