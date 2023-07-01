import java.util.Date;

public class Teslimat {
    private int teslimat_id;
    private String teslimat_kodu;
    private Date teslimat_tarihi;
    private int assigment_id;

    public Teslimat(int teslimat_id, String teslimat_kodu, Date teslimat_tarihi, int assigment_id) {
        this.teslimat_id = teslimat_id;
        this.teslimat_kodu = teslimat_kodu;
        this.teslimat_tarihi = teslimat_tarihi;
        this.assigment_id = assigment_id;
    }

    public int getTeslimat_id() {
        return teslimat_id;
    }

    public void setTeslimat_id(int teslimat_id) {
        this.teslimat_id = teslimat_id;
    }

    public String getTeslimat_kodu() {
        return teslimat_kodu;
    }

    public void setTeslimat_kodu(String teslimat_kodu) {
        this.teslimat_kodu = teslimat_kodu;
    }

    public Date getTeslimat_tarihi() {
        return teslimat_tarihi;
    }

    public void setTeslimat_tarihi(Date teslimat_tarihi) {
        this.teslimat_tarihi = teslimat_tarihi;
    }

    public int getAssigment_id() {
        return assigment_id;
    }

    public void setAssigment_id(int assigment_id) {
        this.assigment_id = assigment_id;
    }

}
