#include <iostream>

using namespace std;

//class movie with pretty self explanatory stuff
class Movie {
  private:
    string movieTitle;
    int yearReleased;
    float rating;
  public:
    Movie(string mT, int yR, float r) {
      movieTitle = mT;
      yearReleased = yR;
      rating = r;
    }
    Movie() {
      movieTitle = "No Title given";
      yearReleased = 2018;
      rating = 0.0;
    }
    void setTitle(string newTitle) {
      movieTitle = newTitle;
    }
    string getTitle() {
      return movieTitle;
    }
    void setYear(int newYear) {
      yearReleased = newYear;
    }
    int getYear() {
      return yearReleased;
    }
    void setRating(float newRating) {
      rating = newRating;
    }
    float getRating() {
      return rating;
    }
};

int main() {
  //test your functions and class here

  return 0;
}
