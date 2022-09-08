package com.example.bookstore_project.service;

import com.example.bookstore_project.dto.UserDto;
import com.example.bookstore_project.exception.UserException;
import com.example.bookstore_project.model.User;
import com.example.bookstore_project.repo.UserRepo;
import com.example.bookstore_project.utility.EmailSenderService;
import com.example.bookstore_project.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepo repository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public String addRecord(UserDto addressDto) throws UserException {
        User book =new User(addressDto);
        repository.save(book);
        String token = tokenUtil.createToken(book.getUser_id());
        String userData = "Your Details: \n"+book.getFirst_name()+"\n"+book.getAddress()+"\n"
                +book.getLast_name()+"\n"+book.getDOB()+"\n"+book.getUser_id()+"\n"+
                book.getPassword();
        emailSenderService.sendEmail(book.getEmail_address(),"Added Your Details", userData);
        return token;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> FindById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> getUserByemail(String email) {

         return repository.findUserByemail(email);
    }

    @Override
    public User editByEmail(UserDto userDTO, String email_address) {
        List<User> userdata = repository.findUserByemail(email_address);
        Optional<User> editdata = Optional.ofNullable(userdata.get(0));
        if (editdata.isPresent()) {
            userdata.get(0).setFirst_name(userDTO.getFirst_name());
            userdata.get(0).setLast_name(userDTO.getLast_name());
            userdata.get(0).setAddress(userDTO.getAddress());
            userdata.get(0).setEmail_address(userDTO.getEmail_address());
            userdata.get(0).setDOB(userDTO.getDOB());
            userdata.get(0).setPassword(userDTO.getPassword());
            //Email Body
            String updatedData = "Updated Details: \n" + "First Name: " + userdata.get(0).getFirst_name() + "\n" + "Last Name: " + userdata.get(0).getLast_name() + "\n"
                    + "Address: " + userdata.get(0).getAddress() + "\n" + "Email Address: " + userdata.get(0).getEmail_address() + "\n" + "DOB: " + userdata.get(0).getDOB() +"\n"
                    + "Password: " + userdata.get(0).getPassword();
            //sending email
            emailSenderService.sendEmail(userdata.get(0).getEmail_address(), "Data Updated!!!", updatedData);

            return repository.save(userdata.get(0));
        } else
            throw new UserException("Invalid Email Address: " + email_address);
    }

}

