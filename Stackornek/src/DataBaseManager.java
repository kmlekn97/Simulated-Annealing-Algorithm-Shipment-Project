import java.security.SecureRandom;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseManager {
    private static IDataBaseService iDataBaseService;

    public DataBaseManager(IDataBaseService iDataBaseService) {
        this.iDataBaseService = iDataBaseService;
    }
    public static class Tasitlar{
        public static ArrayList<Tasit> TasitListele(int tasit_id) {
            iDataBaseService.connect();
            ArrayList<Tasit> tasitlar = new ArrayList<Tasit>();
            try {
                if (tasit_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM tasitlar WHERE tasitlar_id=%d", tasit_id));
                }
                else
                {
                    iDataBaseService.read("SELECT * FROM tasitlar");
                }
                while (iDataBaseService.getRs().next()) {
                    int tasitlar_id=iDataBaseService.getRs().getInt("tasitlar_id");
                    String tasitlar_adi=iDataBaseService.getRs().getString("tasitlar_adi");
                    float tasitlar_kapasite=iDataBaseService.getRs().getFloat("tasitlar_kapasite");
                    String tasitlar_plaka=iDataBaseService.getRs().getString("tasitlar_plaka");
                    Tasit tasitim=new Tasit(tasitlar_id,tasitlar_adi,tasitlar_kapasite,tasitlar_plaka);
                    tasitlar.add(tasitim);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            iDataBaseService.connectclose();
            return tasitlar;
        }
        public static void tasitekle(String tasit_adi,float tasit_kapasite,String tasit_plaka) {
            iDataBaseService.connect();
            String sql1=String.format("INSERT INTO tasitlar (tasitlar_adi, tasitlar_kapasite, tasitlar_plaka) VALUES ('%s',",tasit_adi);
            String sql2=String.format("%.2f", tasit_kapasite);
            sql2 = sql2.replace(',', '.');
            String sql3= String.format(",'%s')", tasit_plaka);
            String sql=sql1+sql2+sql3;
            iDataBaseService.databasexecute(sql);
            iDataBaseService.connectclose();
        }
        public static void tasitguncelle(int tasit_id,String tasit_adi,float tasit_kapasite,String tasit_plaka)
        {
            iDataBaseService.connect();
            String sql1=String.format("UPDATE tasitlar SET tasitlar_adi='%s',tasitlar_kapasite=", tasit_adi);
            String sql2=String.format("%.2f",tasit_kapasite);
            sql2 = sql2.replace(',', '.');
            String sql3=String.format(",tasitlar_plaka='%s' WHERE tasitlar_id=%d",tasit_plaka,tasit_id);
            String sql=sql1+sql2+sql3;
            iDataBaseService.databasexecute(sql);
            iDataBaseService.connectclose();
        }
        public static void tasitsil(int tasit_id) {
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("DELETE FROM tasitlar WHERE tasitlar_id=%d", tasit_id));
            iDataBaseService.connectclose();
        }
    }

    public static class Kuryeler{
        public static ArrayList<Kurye> KuryeListele(int tasit_id,int kurye_id) {
            iDataBaseService.connect();
            ArrayList<Kurye> kuryeler = new ArrayList<Kurye>();
            try {
                if (tasit_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM kurye WHERE tasitlar_id=%d", tasit_id));
                }
                else if (kurye_id !=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM kurye WHERE kurye_id=%d", kurye_id));
                }
                else
                {
                    iDataBaseService.read("SELECT * FROM kurye");
                }
                while (iDataBaseService.getRs().next()) {
                    int kuryeler_id=iDataBaseService.getRs().getInt("kurye_id");
                    String kurye_adi=iDataBaseService.getRs().getString("kurye_adi");
                    String kurye_soyadi=iDataBaseService.getRs().getString("kurye_soyadi");
                    int tasitlar_id=iDataBaseService.getRs().getInt("tasitlar_id");
                    Kurye kuryem=new Kurye(kuryeler_id,kurye_adi,kurye_soyadi,tasitlar_id);
                    kuryeler.add(kuryem);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            iDataBaseService.connectclose();
            return kuryeler;
        }
        public static void kuryeekle(String kurye_adi,String kurye_soyadi,int tasitlar_id) {
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("INSERT INTO kurye (kurye_adi, kurye_soyadi,tasitlar_id) VALUES ('%s', '%s',%d)",kurye_adi,kurye_soyadi,tasitlar_id));
            iDataBaseService.connectclose();
        }
        public static void kuryeguncelle(int kurye_id,String kurye_adi,String kurye_soyadi,int tasitlar_id){
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("UPDATE kurye SET kurye_adi='%s',kurye_soyadi='%s',tasitlar_id=%d WHERE kurye_id=%d",kurye_adi,kurye_soyadi,tasitlar_id,kurye_id));
            iDataBaseService.connectclose();
        }
        public static void kuryesil(int kurye_id){
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("DELETE FROM kurye WHERE kurye_id=%d", kurye_id));
            iDataBaseService.connectclose();
        }
    }
    public static class Assign{
        public static Stack<Assigment> AssigmentListele(int assigment_id, int kurye_id) {
            iDataBaseService.connect();
            Stack<Assigment> assigments = new Stack<Assigment>();
            try {
                if (assigment_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM assigment WHERE assigment_id=%d", assigment_id));
                }
                else if (kurye_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM assigment WHERE kurye_id=%d", kurye_id));
                }
                else
                {
                    iDataBaseService.read("SELECT * FROM assigment");
                }
                while (iDataBaseService.getRs().next()) {
                    int gassigment_id=iDataBaseService.getRs().getInt("assigment_id");
                    float assigment_kapasite=iDataBaseService.getRs().getFloat("assigment_kapasite");
                    int kurye_ids=iDataBaseService.getRs().getInt("kurye_id");
                    int assigment_durum=iDataBaseService.getRs().getInt("assigment_durum");
                    int mesafe_id=iDataBaseService.getRs().getInt("mesafe_id");
                    Assigment is=new Assigment(gassigment_id,assigment_kapasite,kurye_ids,assigment_durum,mesafe_id);
                    assigments.push(is);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            iDataBaseService.connectclose();
            return assigments;
        }
        public static Stack<Assigment> AssigmentDurumaGöreListele(int assigment_id,int kurye_id,int assigment_durum) {
            iDataBaseService.connect();
            Stack<Assigment> assigments = new Stack<Assigment>();
            try {
                if (assigment_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM assigment WHERE assigment_id=%d AND assigment_durum=%d", assigment_id,assigment_durum));
                }
                else if (kurye_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM assigment WHERE kurye_id=%d AND assigment_durum=%d", kurye_id,assigment_durum));
                }
                else
                {
                    iDataBaseService.read(String.format("SELECT * FROM assigment WHERE assigment_durum=%d",assigment_durum));
                }
                while (iDataBaseService.getRs().next()) {
                    int gassigment_id=iDataBaseService.getRs().getInt("assigment_id");
                    float assigment_kapasite=iDataBaseService.getRs().getFloat("assigment_kapasite");
                    int kurye_ids=iDataBaseService.getRs().getInt("kurye_id");
                    int assigment_durums=iDataBaseService.getRs().getInt("assigment_durum");
                    int mesafe_id=iDataBaseService.getRs().getInt("mesafe_id");
                    Assigment is=new Assigment(gassigment_id,assigment_kapasite,kurye_ids,assigment_durums,mesafe_id);
                    assigments.push(is);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            iDataBaseService.connectclose();
            return assigments;
        }

        public static boolean checkCapacity(int kurye_id,float lastcapacity){
            boolean durum=true;
            float totalcapacity=0;
            iDataBaseService.connect();
            iDataBaseService.read(String.format("SELECT assigment.*,kurye.*,tasitlar.* from assigment INNER JOIN kurye ON assigment.kurye_id=kurye.kurye_id INNER JOIN tasitlar ON kurye.tasitlar_id=tasitlar.tasitlar_id WHERE kurye.kurye_id=%d",kurye_id));
            try {
                while (iDataBaseService.getRs().next()) {
                    float assigment_kapasite = iDataBaseService.getRs().getFloat("assigment_kapasite");
                    float tasitlar_kapasite = iDataBaseService.getRs().getFloat("tasitlar_kapasite");
                    totalcapacity+=assigment_kapasite;
                    if ((tasitlar_kapasite-(totalcapacity+lastcapacity))<0){
                        durum=false;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            iDataBaseService.connectclose();
            return durum;
        }

        public static void assigmentekle(float assigment_kapasite,int kurye_id,int mesafe_id) {
            if (checkCapacity(kurye_id,assigment_kapasite)) {
                iDataBaseService.connect();
                String sql1 = String.format("INSERT INTO assigment (assigment_kapasite, kurye_id,mesafe_id) VALUES (");
                String sql2 = String.format("%.2f", assigment_kapasite);
                sql2 = sql2.replace(',', '.');
                String sql3 = String.format(",%d,%d)", kurye_id,mesafe_id);
                String sql = sql1 + sql2 + sql3;
                iDataBaseService.databasexecute(sql);
                iDataBaseService.connectclose();
            }
            else{
                System.out.println("Yetersiz Taşıt Alanı!!!");
            }
        }
        public static void assigmentguncelle(int assigment_id,float assigment_kapasite,int kurye_id,int mesafe_id)
        {
            iDataBaseService.connect();
            String sql1=String.format("UPDATE assigment SET assigment_kapasite=");
            String sql2=String.format("%.2f",assigment_kapasite);
            sql2 = sql2.replace(',', '.');
            String sql3=String.format(",kurye_id=%d,mesafe_id=%d WHERE assigment_id=%d",kurye_id,mesafe_id,assigment_id);
            String sql=sql1+sql2+sql3;
            iDataBaseService.databasexecute(sql);
            iDataBaseService.connectclose();
        }

        public static void assigmentDurumdegistir(int assigment_id,int assigment_durum){
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("UPDATE assigment SET assigment_durum=%d WHERE assigment_id=%d",assigment_durum,assigment_id));
            iDataBaseService.connectclose();
        }

        public static void assigmentsil(int assigment_id) {
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("DELETE FROM assigment WHERE assigment_id=%d", assigment_id));
            iDataBaseService.connectclose();
        }
    }
    public static class Mesafeler{
        public static ArrayList<Mesafe> MesafeleriListele(int mesafeler_id,int kurye_id) {
            iDataBaseService.connect();
            ArrayList<Mesafe> mesafeler = new ArrayList<Mesafe>();
            try {
                if (mesafeler_id!=-1 && kurye_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM mesafeler WHERE mesafeler_id=%d AND kurye_id=%d", mesafeler_id,kurye_id));
                }
                else if (mesafeler_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM mesafeler WHERE mesafeler_id=%d", mesafeler_id));
                }
                else if (kurye_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM mesafeler WHERE kurye_id=%d", kurye_id));
                }
                else
                {
                    iDataBaseService.read("SELECT * FROM mesafeler");
                }
                while (iDataBaseService.getRs().next()) {
                    int gmesafeler_id=iDataBaseService.getRs().getInt("mesafeler_id");
                    String mesafe_adi=iDataBaseService.getRs().getString("mesafe_adi");
                    float mesafe_uzakliği=iDataBaseService.getRs().getFloat("mesafe_uzakliği");
                    int kurye_ids=iDataBaseService.getRs().getInt("kurye_id");
                    int baslangic_sehir_no=iDataBaseService.getRs().getInt("baslangic_sehir_no");
                    int varis_sehir_no=iDataBaseService.getRs().getInt("varis_sehir_no");
                    int mesafe_durum=iDataBaseService.getRs().getInt("mesafe_durum");
                    Mesafe mesefe=new Mesafe(gmesafeler_id,mesafe_adi,mesafe_uzakliği,kurye_ids,baslangic_sehir_no,varis_sehir_no,mesafe_durum);
                    mesafeler.add(mesefe);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            iDataBaseService.connectclose();
            return mesafeler;
        }
        public static ArrayList<Mesafe> MesafeleriVarisagöreListele(int baslangic_sehir_nog,int varis_sehir_nog,int kurye_id) {
            iDataBaseService.connect();
            ArrayList<Mesafe> mesafeler = new ArrayList<Mesafe>();
            try {
                iDataBaseService.read(String.format("SELECT * FROM mesafeler WHERE kurye_id=%d AND baslangic_sehir_no=%d AND varis_sehir_no=%d", kurye_id,baslangic_sehir_nog,varis_sehir_nog));
                while (iDataBaseService.getRs().next()) {
                    int gmesafeler_id=iDataBaseService.getRs().getInt("mesafeler_id");
                    String mesafe_adi=iDataBaseService.getRs().getString("mesafe_adi");
                    float mesafe_uzakliği=iDataBaseService.getRs().getFloat("mesafe_uzakliği");
                    int kurye_ids=iDataBaseService.getRs().getInt("kurye_id");
                    int baslangic_sehir_no=iDataBaseService.getRs().getInt("baslangic_sehir_no");
                    int varis_sehir_no=iDataBaseService.getRs().getInt("varis_sehir_no");
                    int mesafe_durum=iDataBaseService.getRs().getInt("mesafe_durum");
                    Mesafe mesefe=new Mesafe(gmesafeler_id,mesafe_adi,mesafe_uzakliği,kurye_ids,baslangic_sehir_no,varis_sehir_no,mesafe_durum);
                    mesafeler.add(mesefe);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            iDataBaseService.connectclose();
            return mesafeler;
        }
        public static void mesafeekle(String mesafe_adi,float mesafe_uzakliği,int kurye_id,int baslangic_sehir_no,int varis_sehir_no) {
            iDataBaseService.connect();
            String sql1=String.format("INSERT INTO mesafeler (mesafe_adi, mesafe_uzakliği,kurye_id,baslangic_sehir_no,varis_sehir_no) VALUES ('%s',",mesafe_adi);
            String sql2=String.format("%.2f", mesafe_uzakliği);
            sql2 = sql2.replace(',', '.');
            String sql3= String.format(",%d,%d,%d)", kurye_id,baslangic_sehir_no,varis_sehir_no);
            String sql=sql1+sql2+sql3;
            iDataBaseService.databasexecute(sql);
            iDataBaseService.connectclose();
        }
        public static void mesafeguncelle(int mesafeler_id,String mesafe_adi,float mesafe_uzakliği,int kurye_id,int baslangic_sehir_no,int varis_sehir_no)
        {
            iDataBaseService.connect();
            String sql1=String.format("UPDATE mesafeler SET mesafe_adi='%s',mesafe_uzakliği=",mesafe_adi);
            String sql2=String.format("%.2f",mesafe_uzakliği);
            sql2 = sql2.replace(',', '.');
            String sql3=String.format(",kurye_id=%d,baslangic_sehir_no=%d,varis_sehir_no=%d WHERE mesafeler_id=%d",kurye_id,baslangic_sehir_no,varis_sehir_no,mesafeler_id);
            String sql=sql1+sql2+sql3;
            iDataBaseService.databasexecute(sql);
            iDataBaseService.connectclose();
        }
        public static void mesafeDurumdegistir(int mesafeler_id,int mesafe_durum){
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("UPDATE mesafeler SET mesafe_durum=%d WHERE mesafeler_id=%d",mesafe_durum,mesafeler_id));
            iDataBaseService.connectclose();
        }
        public static void mesafelersil(int mesafeler_id) {
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("DELETE FROM mesafeler WHERE mesafeler_id=%d", mesafeler_id));
            iDataBaseService.connectclose();
        }
    }
    public static class Teslimatlar{
        public static ArrayList<Teslimat> TeslimatListele(int teslimat_id,int assigment_id) {
            iDataBaseService.connect();
            ArrayList<Teslimat> teslimatlar = new ArrayList<Teslimat>();
            try {
                if (teslimat_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM teslimat WHERE teslimat_id=%d", teslimat_id));
                }
                else if (assigment_id!=-1)
                {
                    iDataBaseService.read(String.format("SELECT * FROM teslimat WHERE assigment_id=%d", assigment_id));
                }
                else
                {
                    iDataBaseService.read("SELECT * FROM teslimat");
                }
                while (iDataBaseService.getRs().next()) {
                    int gteslimat_id=iDataBaseService.getRs().getInt("teslimat_id");
                    String teslimat_kodu=iDataBaseService.getRs().getString("teslimat_kodu");
                    Date teslimat_tarihi=iDataBaseService.getRs().getDate("teslimat_tarihi");
                    int gassigment_id=iDataBaseService.getRs().getInt("assigment_id");
                    Teslimat teslimat=new Teslimat(gteslimat_id,teslimat_kodu,teslimat_tarihi,gassigment_id);
                    teslimatlar.add(teslimat);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            iDataBaseService.connectclose();
            return teslimatlar;
        }
        public static String Createteslimatkodu(){
            final String nelerOlsun = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            SecureRandom karistirici = new SecureRandom();
            StringBuilder rastgeleDegerYapici = new StringBuilder(15);
            for(int i=0;i<15;i++){
                rastgeleDegerYapici.append(nelerOlsun.charAt(karistirici.nextInt(nelerOlsun.length())));
            }
            return rastgeleDegerYapici.toString();
        }
        public static void teslim_Et(int teslimat_id){
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("UPDATE teslimat SET teslimat_durum=%d WHERE teslimat_id=%d",1,teslimat_id));
            iDataBaseService.connectclose();
        }
        public static void teslimatekle(int assigment_id) {
            String teslimat_kodu=Createteslimatkodu();
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("INSERT INTO teslimat (teslimat_kodu, assigment_id) VALUES ('%s',%d)",teslimat_kodu,assigment_id));
            iDataBaseService.connectclose();
        }
        public static void teslimatguncelle(int teslimat_id, String teslimat_kodu, Timestamp teslimat_tarihi, int assigment_id){
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("UPDATE teslimat SET teslimat_kodu='%s',teslimat_tarihi='%s',assigment_id=%d WHERE teslimat_id=%d",teslimat_kodu,teslimat_tarihi,assigment_id,teslimat_id));
            iDataBaseService.connectclose();
        }
        public static void teslimatsil(int teslimat_id){
            iDataBaseService.connect();
            iDataBaseService.databasexecute(String.format("DELETE FROM teslimat WHERE teslimat_id=%d", teslimat_id));
            iDataBaseService.connectclose();
        }

    }
}
