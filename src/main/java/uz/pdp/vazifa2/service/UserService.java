package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.Cart;
import uz.pdp.vazifa2.entity.User;
import uz.pdp.vazifa2.payload.UserDto;
import uz.pdp.vazifa2.repository.CartRepository;
import uz.pdp.vazifa2.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getOne(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        return null;
    }

    public ApiResponse add(UserDto userDto) {

        boolean existsByName = userRepository.existsByName(userDto.getName());
        if (existsByName)
            return new ApiResponse("already exist", false);

        User user = new User();
        user.setName(userDto.getName());
        userRepository.save(user);

        Cart cart = new Cart();
        cartRepository.save(cart);

        return new ApiResponse("successfully saved", true);
    }

    public ApiResponse edit(Integer id, UserDto userDto) {

        boolean byNameAndIdNot = userRepository.existsByNameAndIdNot(userDto.getName(), id);
        if (byNameAndIdNot)
            return new ApiResponse("already exist ", false);

        User user = new User();
        user.setId(id);
        user.setName(userDto.getName());
        userRepository.save(user);

        return new ApiResponse("successfully edited", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("not found", false);

        userRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }

    public boolean existByUserId(Integer userId){
        return userRepository.existsById(userId);
    }

}
