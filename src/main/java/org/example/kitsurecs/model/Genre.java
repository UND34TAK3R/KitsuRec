//Revision History:
//      NAME                DATE                        COMMENTS
//      Derrick Mangari     2025/04/18              Added Setters
//      Derrick Mangari     2025/04/21              Added Constructors

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

    public Genre(int genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    @Override
    public String toString() {
        return name; // or return "Genre{id=" + id + ", name='" + name + "'}";
    }
}
