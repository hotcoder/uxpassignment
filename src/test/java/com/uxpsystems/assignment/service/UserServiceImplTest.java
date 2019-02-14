package com.uxpsystems.assignment.service;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.repository.UserRepository;
import com.uxpsystems.assignment.service.impl.UserServiceImpl;
import jdk.nashorn.internal.runtime.options.Option;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void whenNoUsersInDB_Then_getAllUsersReturnEmptyList() {
        List<User> emptyUserList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> allUsers = userService.getAllUsers();

        Assert.assertEquals(emptyUserList,allUsers);
    }

    @Test
    public void whenUsersInDBPresent_Then_getAllUsersReturnUsersList() {
        List<User> mockUserList = createMockUserList();
        when(userRepository.findAll()).thenReturn(mockUserList);

        List<User> allUsers = userService.getAllUsers();

        Assert.assertEquals(mockUserList,allUsers);
    }

    @Test
    public void whenUserWithGivenIdPresent_Then_getUserByIdReturnsThatUser() {
        Long mockId = 1L;
        User mockUser = Mockito.mock(User.class);
        Optional<User> mockUserOptional = Optional.of(mockUser);
        when(userRepository.findById(Mockito.any())).thenReturn(mockUserOptional);

        User user = userService.getUserById(mockId);

        Assert.assertEquals(mockUser,user);
    }

    @Test
    public void whenUserDeletedSuccefullyFromDB_Then_getAllUsersReturnUsersExcludingDeletedUser() {
        Long mockId = 1L;
        List<User> mockUserList = createMockUserList();

        doAnswer((Answer)  invocation -> {
            mockUserList.remove(0);
            return null;
        }).when(userRepository).deleteById(Mockito.any());

        userService.deleteUser(mockId);

        Assert.assertTrue(mockUserList.size()==1);

    }

    @Test
    public void whenNewUserAddedToDB_Then_NumberOfUsersPresentInDBIncrease() {
        List<User> mockUserList = createMockUserList();
        User newUser = Mockito.mock(User.class);
        doAnswer((Answer)invocation -> {
            User user = (User)invocation.getArgument(0);
            mockUserList.add(user);
            return null;
        }).when(userRepository).save(newUser);
        when(userRepository.findAll()).thenReturn(mockUserList);

        userService.saveOrUpdate(newUser);

        Assert.assertTrue(userService.getAllUsers().size() == 3);
    }

    private List<User> createMockUserList() {
        List<User> mockUserList = new ArrayList<>();
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        mockUserList.add(user1);
        mockUserList.add(user2);
        return mockUserList;
    }
}
