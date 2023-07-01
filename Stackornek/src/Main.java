import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        MySqlService service=new MySqlService();
        DataBaseManager database=new DataBaseManager(service);
        SimulatedAnnealing s=new SimulatedAnnealing(1000, 0.99f);
        System.out.println(s.getbestSolutionids()[0]);
        Stack<Assigment> stack=DataBaseManager.Assign.AssigmentDurumaGöreListele(-1,1,0);
        while (!stack.empty()) {
            Assigment nesne = stack.pop();
            ArrayList<Mesafe> mesafeobj=DataBaseManager.Mesafeler.MesafeleriListele(nesne.getMesafe_id(),nesne.getKurye_id());
            for (Mesafe item : mesafeobj) {
                System.out.println(item.getMesafe_adi()+" gidiliyor...");
                DataBaseManager.Teslimatlar.teslimatekle(nesne.getAssigment_id());
                DataBaseManager.Assign.assigmentDurumdegistir(nesne.getAssigment_id(),1);
                System.out.println("Teslimat yapıldı");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}