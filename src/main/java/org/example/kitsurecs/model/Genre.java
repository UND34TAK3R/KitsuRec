//Revision History:
//      NAME                DATE                        COMMENTS
//      Derrick Mangari     2025/04/18              Added Setters

package org.example.kitsurecs.model;

public class Genre {
    //fields
    private int genreId;
    private String name;

    //setters
    public void setGenreId(int genreId) { this.genreId = genreId; }
    public void setName(String name) { this.name = name; }

    //getters
    public int getGenreId() {
        return genreId;
    }
    public String getName(){
        return name;
    }
}
