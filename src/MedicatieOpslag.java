//import java.util.HashMap;
//
//public class MedicatieOpslag {
//
//    private final HashMap<String, Integer> medicaties = new HashMap<>();
//
//    public MedicatieOpslag() {
//        medicaties.put("paracetamol", 5000);
//        medicaties.put("ibuprofen", 5000);
//        medicaties.put("antibiotica", 5000);
//    }
//
//    public HashMap<String, Integer> getMedicaties() {
//        return medicaties;
//    }
//
//    public void toonMedicaties() {
//        System.out.println("Medicatie opslag:");
//        for (var entry : medicaties.entrySet()) {
//            System.out.println("\n " + entry.getKey() + " = " + entry.getValue() + " mg");
//        }
//    }
//}