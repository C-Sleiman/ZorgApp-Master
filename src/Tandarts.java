class Tandarts extends User {
    Tandarts(int userID, String userName) {
        super(userID, userName);
    }

    @Override
    boolean hasAccess(int choice) {
        return choice == Administration.STOP
                || choice == Administration.VIEW
                || choice == Administration.PatientList
                || choice == Administration.SelectPatient
                || choice == Administration.EditPatient
                || choice == Administration.AddConsult
                || choice == Administration.EditConsult
                || choice == Administration.DeleteConsult;
    }
}