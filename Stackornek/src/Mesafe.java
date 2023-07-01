public class Mesafe {
    private int mesafeler_id;
    private String mesafe_adi;
    private float mesafe_uzakliği;
    private int kurye_id;
    private int baslangic_sehir_no;
    private int varis_sehir_no;
    private int mesafe_durum;

    public Mesafe(int mesafeler_id, String mesafe_adi, float mesafe_uzakliği, int kurye_id, int baslangic_sehir_no, int varis_sehir_no, int mesafe_durum) {
        this.mesafeler_id = mesafeler_id;
        this.mesafe_adi = mesafe_adi;
        this.mesafe_uzakliği = mesafe_uzakliği;
        this.kurye_id = kurye_id;
        this.baslangic_sehir_no = baslangic_sehir_no;
        this.varis_sehir_no = varis_sehir_no;
        this.mesafe_durum = mesafe_durum;
    }

    public int getMesafeler_id() {
        return mesafeler_id;
    }

    public void setMesafeler_id(int mesafeler_id) {
        this.mesafeler_id = mesafeler_id;
    }

    public String getMesafe_adi() {
        return mesafe_adi;
    }

    public void setMesafe_adi(String mesafe_adi) {
        this.mesafe_adi = mesafe_adi;
    }

    public float getMesafe_uzakliği() {
        return mesafe_uzakliği;
    }

    public void setMesafe_uzakliği(float mesafe_uzakliği) {
        this.mesafe_uzakliği = mesafe_uzakliği;
    }

    public int getKurye_id() {
        return kurye_id;
    }

    public void setKurye_id(int kurye_id) {
        this.kurye_id = kurye_id;
    }

    public int getBaslangic_sehir_no() {
        return baslangic_sehir_no;
    }

    public void setBaslangic_sehir_no(int baslangic_sehir_no) {
        this.baslangic_sehir_no = baslangic_sehir_no;
    }

    public int getVaris_sehir_no() {
        return varis_sehir_no;
    }

    public void setVaris_sehir_no(int varis_sehir_no) {
        this.varis_sehir_no = varis_sehir_no;
    }

    public int getMesafe_durum() {
        return mesafe_durum;
    }

    public void setMesafe_durum(int mesafe_durum) {
        this.mesafe_durum = mesafe_durum;
    }
}
