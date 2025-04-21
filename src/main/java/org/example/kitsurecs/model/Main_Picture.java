//Revision History:
//      NAME                DATE                        COMMENTS
//      Derrick Mangari     2025/04/18              Added Setters
//      Derrick Mangari     2025/04/21              Added Constructors

package org.example.kitsurecs.model;

public class Main_Picture {

    //fields
    private String medium;
    private String large;

    //setters
    public void setMedium(String medium) { this.medium = medium; }
    public void setLarge(String large) { this.large = large; }

    //getters
    public String getMedium() {
        return medium;
    }
    public String getLarge(){
        return large;
    }

    //constructor
    public Main_Picture(String medium, String large){
        this.medium = medium;
        this.large = large;
    }
}



