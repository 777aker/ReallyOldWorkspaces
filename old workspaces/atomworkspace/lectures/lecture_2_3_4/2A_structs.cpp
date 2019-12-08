#include <iostream>

using namespace std;

struct Student {
  //ID, name, major
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
  //create instance of Student called s
  Student s;
  //setting the properties for student s
  s.ID = 5;
  s.name = "bob";
  s.major = "skiing";
  Student s2(6, "jimmy", "swimming"); //constructor with everything needed
  cout << s.ID << " " << s.name << " " << s.major << endl;
  cout << s2.ID << " " << s2.name << " " << s2.major << endl;
}
