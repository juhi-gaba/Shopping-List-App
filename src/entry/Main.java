package entry;

import dto.User;
import service.UserService;

import java.util.List;

public class Main {

    /**
     * Entry point of the code.
     *
     * @param args Argument.
     */
    public static void main(String[] args) {
        int list_upperLimit = 6;
        int item_upperLimit = 50;
        int noOfItems = 5;
        int noOfUser = 5;

        List<User> list = getDummyData(list_upperLimit, item_upperLimit,
                noOfItems, noOfUser);
        UserService userService = new UserService();
        userService.createUser(list);

    }

    /**
     * Generate the dummy data.
     *
     * @param list_upperLimit List upper limit.
     * @param item_upperLimit Item upper limit.
     * @param noOfItems       Number of items.
     * @param noOfUser        Number of users
     * @return                List of user with dummy data.
     */
    private static List<User> getDummyData(int list_upperLimit,
                                           int item_upperLimit, int noOfItems, int noOfUser) {
        DummyDataGenerator generator = new DummyDataGenerator(list_upperLimit,
                item_upperLimit);
        return generator.getDummyUserList(noOfItems, noOfUser);

    }
}
