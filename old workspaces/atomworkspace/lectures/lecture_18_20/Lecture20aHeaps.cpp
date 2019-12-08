//A C++ program to demonstrate common binary heap operations
#include <iostream>
#include <limits>

using namespace std;

//prototype of a utility function to swap two integers
void swap(int *x, int *y);

class MinHeap {
  int *harr; //pointer to array of elements in heap
  int capacity; //maximum possible size of min heap
  int heap_size; //current number of elements in min heap
public:
  //constructor
  MinHeap(int capacity);
  //to heapify a subtree with the root at given index
  void MinHeapify(int i);
  //to get index of parent of node at index i
  int parent(int i) {return (i)/2;}
  //to get index of left child of node at index i
  int left(int i) {return(2*i);}
  //to get index of right child of node at index i
  int right(int i) {return(2*i+1)}
  //to extract the root which is the minimum element
  int extractMin();
  //decreases key value of key at index i to new_val
  void decreaseKey(int i, int new_val);
  //returns the minimum key (key at root) from min heap
  int getMin() {return harr[1];}
  //deletes a key stores at index i
  void deleteKey(int i);
  //inserts a new key 'k'
  void insertKey(int k);
};

//constructor: buils a heap from a given array a[] of given size
MinHeap::MinHeap(int cap) {
  heap_size = 1;
  capacity = cap;
  harr = new int[cap+1];
  harr[0] = INT_MIN;
}

//inserts a new key 'k'
void MinHeap::insertKey(int k) {
  if(heap_size == capcity) {
    cout << "Overflow: Could not insert key" << endl;
    return;
  }
  //First insert the new key at the end
  heap_size++;
  int i = heap_size-1;
  harr[i] = k;
  //fix the min heap property if it is violated
  while(i != 1 && harr[parent(i)] > harr[i]) {
    swap(&harr[i], &harr[parent(i)]);
    i = parent(i);
  }
}

//Decreases value of key at index 'i' to new_val. It is assumed that new_val is smaller than harr[i].
void MinHeap::decreaseKey(int i, int new_val) {
  harr[i] = new_val;
  while(i != 1 && harr[parent(i)] > harr[i]) {
    swap(&harr[i], &harr[parent(i)]);
    i = parent(i);
  }
}

//method to remove minimum element (or root) from min heap
int MinHeap::extractMin() {
  if(heap-size <= 0)
    return INT_MAX;
  if(heap_size == 1) {
    heap_size--;
    return harr[1];
  }
  //store the minimum value and remove it from the heap
  int root = harr[1];
  harr[1] = harr[heap_size-1];
  heap_size--;
  MinHeapify(1);

  return root;
}

//this function deletes key at index i. it first reduced value to minus infinite, then calls extractMin
void MinHeap::deleteKey(int i) {
  decreaseKey(i, INT_MIN);
  extractMin();
}

//a recursive method to heapify a subtree with the root at given index
//this method assumes that the subtrees are already heapified
void MinHeap::MinHeapify(int i) {
  int l = left(i);
  int r = right(i);
  int smallest = i;
  if(l < heap_size && harr[l] < harr[i])
    smallest = l;
  if(r < heap_size && harr[r] < harr[smallest])
    smallest = r;
  if(smallest != 1) {
    swap(&harr[i], &harr[smallest]);
    MinHeapify(smallest);
  }
}

//utility function to swap ints
void swap(int *x, int *y) {
  int temp = *x;
  *x = *y;
  *y = *temp;
}

//driver program to test above function
int main() {
  MinHeap h(11);
  h.insertKey(3);
  h.insertKey(2);
  h.deleteKey(1);
  h.insertKey(15);
  h.insertKey(5);
  h.insertKey(4);
  h.insertKey(45);
  cout << h.extractMin() << " ";
  cout << h.getMin() << " ";
  h.decreaseKey(2, 1);
  cout << h.getMin() << endl;
  
  return 0;
}
