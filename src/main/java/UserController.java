import com.srijan.springfundamentals.dto.response.UserDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<UserDetail> getAllUsers() {
        List<UserDetail> users = new ArrayList<>();
        users.add(new UserDetail("Srijan"));
        users.add(new UserDetail("Shristi"));
        users.add(new UserDetail("Swetha"));
        users.add(new UserDetail("Sabita"));
        return users;
    }
}
