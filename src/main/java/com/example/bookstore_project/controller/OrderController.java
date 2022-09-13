package com.example.bookstore_project.controller;
import com.example.bookstore_project.dto.ResponseDto;
import com.example.bookstore_project.model.Order;
import com.example.bookstore_project.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bookstore_project.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    IOrderService orderService;
// add order details
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody OrderDto orderDto) {
        String order = orderService.addOrder(orderDto);
        ResponseDto responseDTO = new ResponseDto("Add order Success", order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    // get all data by using findall() method
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> findAllDetail() {
        List<Order> orderList = orderService.findAll();
        ResponseDto responseDTO = new ResponseDto("** All order List ** ", orderList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    // get  orders using id
    @GetMapping("/get/{orderId}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable Long orderId) {
        Order response = orderService.FindById(orderId);
        ResponseDto responseDto = new ResponseDto("***All order list using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    // delete order details using id
   @DeleteMapping("/delete/{orderId}")
   public ResponseEntity<ResponseDto> deleteById(@PathVariable Long orderId) {
       orderService.deleteById(orderId);
       ResponseDto reponseDTO = new ResponseDto("**order Data deleted successfully ** ", "deleted id " + orderId);
       return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
   }
   // update order details using orderid
    @PutMapping("/update/{orderid}")
    public ResponseEntity<ResponseDto> editData(@PathVariable Long orderid, @Valid @RequestBody OrderDto orderDto) {
        String order = orderService.updateOrderData(orderid, orderDto);
        ResponseDto responseDTO = new ResponseDto("Updated Order Details Successfully", order);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}