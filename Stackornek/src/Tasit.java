public class Tasit {
    private int tasitlar_id;
    private String tasitlar_adi;
    private float tasitlar_kapasite;
    private String tasitlar_plaka;

    public Tasit(int tasitlar_id, String tasitlar_adi, float tasitlar_kapasite, String tasitlar_plaka) {
        this.tasitlar_id = tasitlar_id;
        this.tasitlar_adi = tasitlar_adi;
        this.tasitlar_kapasite = tasitlar_kapasite;
        this.tasitlar_plaka = tasitlar_plaka;
    }

    public int getTasitlar_id() {
        return tasitlar_id;
    }

    public void setTasitlar_id(int tasitlar_id) {
        this.tasitlar_id = tasitlar_id;
    }

    public String getTasitlar_adi() {
        return tasitlar_adi;
    }

    public void setTasitlar_adi(String tasitlar_adi) {
        this.tasitlar_adi = tasitlar_adi;
    }

    public float getTasitlar_kapasite() {
        return tasitlar_kapasite;
    }

    public void setTasitlar_kapasite(float tasitlar_kapasite) {
        this.tasitlar_kapasite = tasitlar_kapasite;
    }

    public String getTasitlar_plaka() {
        return tasitlar_plaka;
    }

    public void setTasitlar_plaka(String tasitlar_plaka) {
        this.tasitlar_plaka = tasitlar_plaka;
    }
}
