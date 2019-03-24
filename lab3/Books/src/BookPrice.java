

public class BookPrice {
    private Double price;
    private String currency;
    private Double polishPrice;
    private Boolean shouldDisplayPolishPrice = false;

    public BookPrice(Double price, String currency) {
        this.price = price;
        this.currency = currency;
        if (currency != null) {
            if (currency.equals("EUR")) {
                this.polishPrice = BooksUtils.convertEurToPln(price);
            }
            else if (currency.equals("USD")) {
                this.polishPrice = BooksUtils.convertUsdToPln(price);
            }
            else {
                this.polishPrice = price;
            }
        }
    }

    public Double getPrice() {
        if (shouldDisplayPolishPrice) {
            return polishPrice;
        }
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        if (shouldDisplayPolishPrice) {
            return "PLN";
        }
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPolishPrice() {
        return polishPrice;
    }

    public void setPolishPrice(Double polishPrice) {
        this.polishPrice = polishPrice;
    }

    public Boolean getShouldDisplayPolishPrice() {
        return shouldDisplayPolishPrice;
    }

    public void setShouldDisplayPolishPrice(Boolean shouldDisplayPolishPrice) {
        this.shouldDisplayPolishPrice = shouldDisplayPolishPrice;
    }


}
