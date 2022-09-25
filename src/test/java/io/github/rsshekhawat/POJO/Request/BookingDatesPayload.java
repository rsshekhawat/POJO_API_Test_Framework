package io.github.rsshekhawat.POJO.Request;

public class BookingDatesPayload {
    String checkin, checkout;

    public BookingDatesPayload(String checkin, String checkout){
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

}
