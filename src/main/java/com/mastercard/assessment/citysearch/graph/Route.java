package com.mastercard.assessment.citysearch.graph;

public class Route {

    private final String originCity;

    private final String destinationCity;

    public String getOriginCity() {
        return originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    private Route(Builder builder){
        this.originCity = builder.originCity;
        this.destinationCity = builder.destinationCity;
    }


    public static class Builder{

        private String originCity;

        private String destinationCity;

        public Builder(){
        }

        public Builder withOriginCity(final String originCity){
            this.originCity = originCity;
            return this;
        }

        public Builder withDestinationCity(final String destinationCity){
            this.destinationCity = destinationCity;
            return this;
        }

        public Route build(){
            return new Route(this);
        }
    }


}
