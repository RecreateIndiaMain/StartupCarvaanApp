package recreate.india.main.startupcarvaan.fragments.biding;

public class bidingm {
    String closedin;
    Integer currentbid;
    String currentwinner,producttitle,productdesc,productimage,winnerid;

    public String  getClosedin() {
        return closedin;
    }

    public void setClosedin(String closedin) {
        this.closedin = closedin;
    }

    public Integer getCurrentbid() {
        return currentbid;
    }

    public void setCurrentbid(Integer currentbid) {
        this.currentbid = currentbid;
    }

    public String getCurrentwinner() {
        return currentwinner;
    }

    public void setCurrentwinner(String currentwinner) {
        this.currentwinner = currentwinner;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getWinnerid() {
        return winnerid;
    }

    public void setWinnerid(String winnerid) {
        this.winnerid = winnerid;
    }

    public bidingm(String closedin, Integer currentbid, String currentwinner, String producttitle, String productdesc, String productimage, String winnerid) {
        this.closedin = closedin;
        this.currentbid = currentbid;
        this.currentwinner = currentwinner;
        this.producttitle = producttitle;
        this.productdesc = productdesc;
        this.productimage = productimage;
        this.winnerid = winnerid;
    }

    public bidingm() {
    }
}
