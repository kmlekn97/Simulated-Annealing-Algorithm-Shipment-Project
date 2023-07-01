public class Assigment {
    private int assigment_id;
    private float assigment_kapasite;
    private int kurye_id;
    private int assigment_durum;
    private int mesafe_id;

    public Assigment(int assigment_id, float assigment_kapasite, int kurye_id, int assigment_durum, int mesafe_id) {
        this.assigment_id = assigment_id;
        this.assigment_kapasite = assigment_kapasite;
        this.kurye_id = kurye_id;
        this.assigment_durum = assigment_durum;
        this.mesafe_id = mesafe_id;
    }

    public int getAssigment_id() {
        return assigment_id;
    }

    public void setAssigment_id(int assigment_id) {
        this.assigment_id = assigment_id;
    }

    public float getAssigment_kapasite() {
        return assigment_kapasite;
    }

    public void setAssigment_kapasite(float assigment_kapasite) {
        this.assigment_kapasite = assigment_kapasite;
    }

    public int getKurye_id() {
        return kurye_id;
    }

    public void setKurye_id(int kurye_id) {
        this.kurye_id = kurye_id;
    }

    public int getAssigment_durum() {
        return assigment_durum;
    }

    public void setAssigment_durum(int assigment_durum) {
        this.assigment_durum = assigment_durum;
    }

    public int getMesafe_id() {
        return mesafe_id;
    }

    public void setMesafe_id(int mesafe_id) {
        this.mesafe_id = mesafe_id;
    }
}
