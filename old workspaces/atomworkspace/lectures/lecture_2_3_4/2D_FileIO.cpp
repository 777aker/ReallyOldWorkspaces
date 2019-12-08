#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

struct Student {
  int ID;
  string name;
  string major;
  Student() {}
  Student(int id, string n, string m) {
    ID = id;
    name = n;
    major = m;
  }
};

int main() {
  Student allStudents[10];
  ifstream myfile("students.txt");
  cout << "First we will print the String Stream" << endl << endl;
  int counter = 0;
  if(myfile.is_open()) {
    string line;
    while(getline(myfile, line)) {
      cout << line << endl;
      stringstream ss;
      ss << line;
      string item;
      getline(ss, item, ',');
      allStudents[counter].ID = stoi(item);
      getline(ss, item, ',');
      allStudents[counter].name = item;
      getline(ss, item);
      allStudents[counter].major = item;
      counter++;
    }
  }else{
    cout << "error" << endl;
  }
  cout << endl << endl << "Now, we will print the contents of the array" << endl << endl;
  for(int i = 0; i < counter; i++) {
    cout << allStudents[i].ID << " " << allStudents[i].name << " " << allStudents[i].major << endl;
  }
}
