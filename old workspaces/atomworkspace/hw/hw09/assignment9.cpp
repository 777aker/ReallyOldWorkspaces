#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <queue>
#include "Graph.hpp"

using namespace std;

Graph::Graph() {}

Graph::~Graph() {}

void Graph::displayEdges() {
    for(int i = 0; i < vertices.size(); i++) {
        cout << vertices[i].district << ":" << vertices[i].name << "-->";
        for(int f = 0; f < vertices[i].Edges.size(); f++) {
            if(f == 0) {
                cout << vertices[i].Edges[f].v->name << " (" << vertices[i].Edges[f].distance << " miles)";
            } else {
                cout << "***" << vertices[i].Edges[f].v->name << " (" << vertices[i].Edges[f].distance << " miles)";
            }
        }
        cout << endl;
    }
}

void Graph::setAllVerticesUnvisited() {
    for(int i = 0; i < vertices.size(); i++) {
        vertices[i].visited = false;
    }
}

vertex* Graph::findVertex(string name) {
    for(int i = 0; i < vertices.size(); i++) {
        if(vertices[i].name == name) {
            return &vertices[i];
        }
    }
}

void Graph::addEdge(string v1, string v2, int distance) {
    vertex* temp1 = findVertex(v1);
    vertex* temp2 = findVertex(v2);
    Edge e1;
    e1.v = temp2;
    e1.distance = distance;
    temp1->Edges.push_back(e1);
}

void Graph::addVertex(string name) {
    vertex temp;
    temp.name = name;
    vertices.push_back(temp);
}

void Graph::assignDistricts() {
    setAllVerticesUnvisited();
    int districts = 1;
    for(int i = 0; i < vertices.size(); i++) {
        if(!vertices[i].visited) {
            BFTraversalLabel(vertices[i].name, districts);
            districts++;
        }
    }
}

void Graph::BFTraversalLabel(string startingCity, int distID) {
    list<vertex*> queue;
    vertex *temp = findVertex(startingCity);
    temp->visited = true;
    temp->district = distID;
    queue.push_back(temp);
    while(!queue.empty()) {
        temp = queue.front();
        queue.pop_front();
        for(int i = 0; i < temp->Edges.size(); i++) {
            if(!temp->Edges[i].v->visited) {
                temp->Edges[i].v->visited = true;
                temp->Edges[i].v->district = distID;
                queue.push_back(temp->Edges[i].v);
            }
        }
    }
}

void Graph::printDFS() {
    setAllVerticesUnvisited();

    for(int i = 0; i < vertices.size(); i++) {
        vertex *temp = findVertex(vertices[i].name);
        if(!temp->visited) {
            DFTraversal(temp);
        }
    }
}

void Graph:: DFTraversal(vertex *v) {
    v->visited = true;
    cout << v->name << endl;
    for(int i = 0; i < v->Edges.size(); i++) {
        if(!v->Edges[i].v->visited) {
            DFTraversal(v->Edges[i].v);
        }
    }
}

int main(int argc, const char* argv[]) {
    Graph g1 = Graph();
    string fileName = argv[1];
    string cities[50];
    string fileInfo[50][50];
    ifstream file(fileName);
    int x = 0, y = 0;
    if(file.is_open()) {
        string line;
        string item;
        while(getline(file, line)) {
            stringstream ss;
            ss << line;
            while(getline(ss, item, ',')) {
                fileInfo[x][y] = item;
                x++;
            }
            getline(ss, item);
            fileInfo[x][y] = item;
            x = 0;
            y++;
        }
    }
    file.close();
    for(int i = 1; i < 50; i++) {
        if(fileInfo[0][i] != "") {
            cities[i] = fileInfo[0][i];
            g1.addVertex(cities[i]);
        }
    }
    for(int i = 1; i < 50; i++) {
        if(cities[i] != "") {
            for(int f = 1; f < 50; f++) {
                if(fileInfo[i][f] != "") {
                    if(stoi(fileInfo[i][f])>0) {
                        cout << " ... Reading in " << cities[i] << " -- " << cities[f] << " -- " << fileInfo[i][f] << endl;
                        g1.addEdge(cities[i], cities[f], stoi(fileInfo[i][f]));
                    }
                }
            }
        }
    }
    g1.assignDistricts();
    g1.displayEdges();
}
