import com.srijan.springfundamentals.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO("Srijan"));
        users.add(new UserDTO("Shristi"));
        users.add(new UserDTO("Swetha"));
        users.add(new UserDTO("Sabita"));
        return users;
    }
}
