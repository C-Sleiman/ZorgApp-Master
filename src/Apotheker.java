class Apotheker extends User {
    Apotheker(int userID, String userName) {
        super(userID, userName);
    }

    @Override
    boolean hasAccess(int choice) {
        return choice == Administration.STOP
                || choice == Administration.VIEW
                || choice == Administration.PatientList
                || choice == Administration.SelectPatient
                || choice == Administration.AddMeds
                || choice == Administration.EditMeds
                || choice == Administration.DeleteMeds;
    }
}