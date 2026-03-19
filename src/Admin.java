class Admin extends User {
    Admin(int userID, String userName) {
        super(userID, userName);
    }

    @Override
    boolean hasAccess(int choice) {
        return true;
    }
}