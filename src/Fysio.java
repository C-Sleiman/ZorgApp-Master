class Fysio extends User {
    Fysio(int userID, String userName) {
        super(userID, userName);
    }

    @Override
    boolean hasAccess(int choice) {
        return choice == Administration.STOP
                || choice == Administration.VIEW
                || choice == Administration.PatientList
                || choice == Administration.SelectPatient
                || choice == Administration.EditPatientLungcap
                || choice == Administration.LOGOUT;
    }
}