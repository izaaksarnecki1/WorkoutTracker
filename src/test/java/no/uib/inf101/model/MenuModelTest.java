package no.uib.inf101.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import no.uib.inf101.Constants;
import no.uib.inf101.model.db.DatabaseController;
import no.uib.inf101.view.AddWorkoutMenu;
import no.uib.inf101.view.InteractiveWindow;
import no.uib.inf101.view.LoginMenu;
import no.uib.inf101.view.MainMenu;
import no.uib.inf101.view.ProfileMenu;
import no.uib.inf101.view.SignupMenu;
import no.uib.inf101.view.StartMenu;

public class MenuModelTest {

    private static MenuModel menuModel;

    @BeforeAll
    // @TestInstance(Lifecycle.PER_CLASS)
    public static void setUp() {
        DatabaseController db = new DatabaseController();
        menuModel = new MenuModel(db);
    }

    @Test
    public void testHandleMenuAction_SignupMenu_Back() {
        String action = Constants.SIGNUPMENU_BUTTON_BACK;

        InteractiveWindow result = menuModel.handleMenuAction(new SignupMenu(), action, null);
        assertTrue(result instanceof StartMenu);
    }

    @Test
    public void testHandleMenuAction_LoginMenu_Back() {
        String action = Constants.LOGINMENU_BUTTON_BACK;

        InteractiveWindow result = menuModel.handleMenuAction(new LoginMenu(), action, null);

        assertTrue(result instanceof StartMenu);
    }

    @Test
    public void testHandleMenuAction_StartMenu_Signup() {
        String action = Constants.STARTMENU_BUTTON_SIGNUP;

        InteractiveWindow result = menuModel.handleMenuAction(new StartMenu(), action, null);

        assertTrue(result instanceof SignupMenu);
    }

    @Test
    public void testHandleMenuAction_StartMenu_Login() {
        String action = Constants.STARTMENU_BUTTON_LOGIN;

        InteractiveWindow result = menuModel.handleMenuAction(new StartMenu(), action, null);

        assertTrue(result instanceof LoginMenu);
    }

    @Test
    public void testHandleMenuAction_MainMenu_EditUser() {
        String action = Constants.MAINMENU_BUTTON_EDITUSER;

        InteractiveWindow result = menuModel.handleMenuAction(new MainMenu(), action, null);

        assertTrue(result instanceof ProfileMenu);
    }

    @Test
    public void testHandleMenuAction_MainMenu_AddWorkout() {
        String action = Constants.MAINMENU_BUTTON_ADDWORKOUT;

        InteractiveWindow result = menuModel.handleMenuAction(new MainMenu(), action, null);

        assertTrue(result instanceof AddWorkoutMenu);
    }
}
