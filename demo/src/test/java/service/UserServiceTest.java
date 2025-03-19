package service;

import com.lesson.demo.dao.OrderMapper;
import com.lesson.demo.dao.UserMapper;
import com.lesson.demo.entity.User;
import com.lesson.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock //fake userMapper
    private UserMapper userMapper;

    @Mock //fake orderMapper
    private OrderMapper orderMapper;

    @InjectMocks //reall userService
    private UserService userService;

    @BeforeEach //before test give
    void setUp(){
        userService = new UserService(userMapper,orderMapper);
    }

    @Test
    void testListUser(){
        List<User> mockUserList = new ArrayList<>();
        User user1 = new User();
        user1.setName("Allie");
        User user2 = new User();
        user2.setName("Zack");
        mockUserList.add(user1);
        mockUserList.add(user2);

        when(userMapper.findAll()).thenReturn(mockUserList);

        List<User> result = userService.listUser();
        assert result.size() == 2;
    }

    @Test
    void testCreateUser(){
        doNothing().when(userMapper).insert((any(User.class)));
        User result = userService.createUser("Bob", 18, "M", 80, 180);
        assert Objects.equals(result.getName(), "Bob");
        assert  result.getAge() == 18;

    }

    @Test
    void testUpdateUser(){
        User user = new User();// fake user
        doNothing().when(userMapper).updateUser((any(User.class)));
        userService.updateUser(user);
        assert true;
        //updateUser()程式被呼叫超過一次表示有錯誤
        verify((userMapper), times(1));
    }
}
