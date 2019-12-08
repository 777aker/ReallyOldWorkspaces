#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>

using namespace std;


//user structure
struct User {
  string username;
  float gpa;
  int age;
};

vector<User>* users;

//create a struct and store username, gpa, and age values in it
//add the struct to the vector
void addUser(vector<User> _users, string _username, float _gpa, int _age) {
  
}

//print out each element in the list with the format:
//username [gpa]: _age
void printlist(const vector<User> users) {

}

int main(int argc, const char* argv[]) {
  if(argc != 2) {
    cout << "ERROR: wrong number of command line arguments" << endl;
    return 0;
  }
  string filename = argv[1];
  ifstream file(filename);
  if(file.is_open()) {
    string line;
    while(getline(file, line)) {
      stringstream ss;
      ss << line;
      string item;
      string username;
      float gpa;
      int age;
      getline(ss, item, ',');
      username = item;
      getline(ss, item, ',');
      gpa = stof(item);
      getline(ss, item);
      age = stoi(item);
      addUser(users, username, gpa, age);
    }
  }
  file.close();
  return 0;
}
