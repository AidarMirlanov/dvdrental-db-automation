package pojoUser;

import org.testng.annotations.Test;

import java.util.List;

public class UserTest {



    @Test(description = "Create new user" )
    public void  createNewUser () {
        ResponseUser user = UserSteps.createUser();

    }
    @Test(description = "Update user by ID")
    public void updateUser () {
        ResponseUser user = UserSteps.updateUser();
    }
    @Test(description =  "get all users")
    public void getAllUsers () {
        List<ResponseUser>  user = UserSteps.getAllUsers();
    }
}
