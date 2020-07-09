package com.example.beveragefactory.model;

public class OrderResult {
    private boolean success;
    private double price;
    private String errorMessage;
    private String order;

    @Override
    public String toString() {
        if (success) {
           return String.format("\n { 'order' '%s', 'price': %s }", order, price);
        }

        return String.format("\n { 'order: '%s', error: '%s' }", order, errorMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderResult that = (OrderResult) o;

        if (success != that.success) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;
        return order.equals(that.order);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (success ? 1 : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + order.hashCode();
        return result;
    }

    public static class Builder {
        private boolean success = false;
        private Double price = 0d;
        private String errorMessage = null;
        private String order = null;

        public Builder(String order) {
            this.order = order;
        }

        public Builder error(String error){
            this.success = false;
            this.errorMessage = error;
            return this;
        }
        public Builder success(double price){
            this.success = true;
            this.price = price;
            return this;
        }

        public OrderResult build(){

            return new OrderResult(
                   this.success,
                    this.errorMessage,
                    this.price,
                    this.order
            );
        }
    }

    private OrderResult(boolean success, String errorMessage, Double price, String order) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.price = price;
        this.order = order;
    }

    public static OrderResult orderMustContainAtleastOneMenuItem(String order) {
        return new Builder(order).error("Order must contain at least one valid menu item").build();
    }

    public static OrderResult orderMustNotContainInvalidExclusions(String order) {
        return new Builder(order).error("Order must not contain invalid ingredients").build();
    }

    public static OrderResult orderCanNotExcludeAllIngredients(String order) {
        return new Builder(order).error("Order must not exclude all ingredients of menu item").build();
    }

    public static OrderResult success(String order, double price) {
        return new Builder(order).success(price).build();
    }
}
