public class Kurye {
    private int kurye_id;
    private String kurye_adi;
    private  String kurye_soyadi;
    private int tasitlar_id;

    public Kurye(int kurye_id, String kurye_adi, String kurye_soyadi, int tasitlar_id) {
        this.kurye_id = kurye_id;
        this.kurye_adi = kurye_adi;
        this.kurye_soyadi = kurye_soyadi;
        this.tasitlar_id = tasitlar_id;
    }

    public int getKurye_id() {
        return kurye_id;
    }

    public void setKurye_id(int kurye_id) {
        this.kurye_id = kurye_id;
    }

    public String getKurye_adi() {
        return kurye_adi;
    }

    public void setKurye_adi(String kurye_adi) {
        this.kurye_adi = kurye_adi;
    }

    public String getKurye_soyadi() {
        return kurye_soyadi;
    }

    public void setKurye_soyadi(String kurye_soyadi) {
        this.kurye_soyadi = kurye_soyadi;
    }

    public int getTasitlar_id() {
        return tasitlar_id;
    }

    public void setTasitlar_id(int tasitlar_id) {
        this.tasitlar_id = tasitlar_id;
    }
}
