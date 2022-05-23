public class Person extends Room {
    private int guestNum;
    private String fName;
    private String sName;
    private int cardNum;

    public Person(String guestName){
        super(guestName);
    }
    public void setGuestNum(int guestNum){
        this.guestNum=guestNum;
    }
    public void setFName(String fName){
        this.fName= fName;
    }
    public void setSName(String sName){
        this.sName=sName;
    }
    public void setCardNum(int cardNum){
        this.cardNum=cardNum;
    }
    public int getGuestNum() {
        return guestNum;
    }
    public String getFName() {
        return fName;
    }
    public String getSName() {
        return sName;
    }
    public int getCardNum() {
        return cardNum;
    }

}
