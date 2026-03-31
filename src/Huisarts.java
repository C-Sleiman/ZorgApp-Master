class Huisarts extends User {
    Huisarts(int userID, String userName) {
        super(userID, userName);
    }

    @Override
    boolean hasAccess(int choice) {
        return choice == Administration.STOP
                || choice == Administration.VIEW
                || choice == Administration.PatientList
                || choice == Administration.SelectPatient
                || choice == Administration.EditPatient
                || choice == Administration.AddMeds
                || choice == Administration.EditMeds
                || choice == Administration.DeleteMeds
                || choice == Administration.AddAllergies
                || choice == Administration.EditAllergies
                || choice == Administration.DeleteAllergies
                || choice == Administration.AddConsult
                || choice == Administration.EditConsult
                || choice == Administration.DeleteConsult;
    }
}