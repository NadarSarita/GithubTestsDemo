package com.app.idnbin.Profile.Settings.Cards;

class CardsData {
    boolean checked;
    String cardNumber;
    String CardName;

    public CardsData(boolean checked, String cardNumber, String cardName) {
        this.checked = checked;
        this.cardNumber = cardNumber;
        CardName = cardName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }
}
