import Tugas2.SecurityService;
import Tugas2.User;
import Tugas2.UserDAO;
import Tugas2.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserTest {
    User user = Mockito.mock(User.class);

    UserDAO dao = Mockito.mock(UserDAO.class);
    SecurityService security = Mockito.mock(SecurityService.class);

    @Test
    public void testPassword() throws Exception {
        UserServiceImpl sut = new UserServiceImpl(dao, security);
        Mockito.when(user.getPassword()).thenReturn("pass123");
        Mockito.when(security.md5(user.getPassword())).thenReturn("md5pass123");

        sut.assignPassword(user);
        //verifikasi pembuatan password baru
        Mockito.verify(user).setPassword("md5pass123");

        //memeriksa password baru
        Assertions.assertEquals(user.getPassword(),"pass123");

        //verifikasi method updateUser
        Mockito.verify(dao).updateUser(user);
    }
}
