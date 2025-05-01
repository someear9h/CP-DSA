#include <iostream>
#include <string>
#include <vector>
using namespace std;

// Forward declaration
struct Station;

// 1) Station
struct Station {
    string name;
    
    // Constructor for Station
    Station(string n) {
        name = n;
    }
};

// 2) Track (just a link between two stations)
struct Track {
    Station* a;
    Station* b;
    
    // Constructor for Track
    Track(Station* _a, Station* _b) {
        a = _a;
        b = _b;
    }
};

// 3) A single stop in a train’s schedule
struct ScheduleEntry {
    Station* station;
    string arrival;    // e.g. "09:15"
    string departure;  // e.g. "09:20"
    
    // Constructor for ScheduleEntry
    ScheduleEntry(Station* s, string arr, string dep) {
        station = s;
        arrival = arr;
        departure = dep;
    }
};

// 4) Train with its full schedule
struct Train {
    string id;
    string name;
    vector<ScheduleEntry> schedule;
    
    // Constructor for Train
    Train(string _id, string _name) {
        id = _id;
        name = _name;
    }
    
    // Add stop to the schedule
    void addStop(Station* st, string arr, string dep) {
        ScheduleEntry entry(st, arr, dep);
        schedule.push_back(entry);
    }
};

// 5) The network itself
class RailwayNetwork {
public:
    vector<Station*> stations;
    vector<Track*> tracks;
    vector<Train*> trains;

    // Add a new Station to the network
    Station* addStation(string name) {
        Station* s = new Station(name);
        stations.push_back(s);
        return s;
    }

    // Add a new Track between two Stations
    void addTrack(Station* a, Station* b) {
        Track* t = new Track(a, b);
        tracks.push_back(t);
    }

    // Add a new Train to the network
    Train* addTrain(string id, string name) {
        Train* t = new Train(id, name);
        trains.push_back(t);
        return t;
    }
};

int main() {
    RailwayNetwork net;

    // Create stations
    Station* delhi = net.addStation("Delhi");
    Station* jaipur = net.addStation("Jaipur");
    Station* agra = net.addStation("Agra");

    // Connect them with tracks
    net.addTrack(delhi, jaipur);
    net.addTrack(jaipur, agra);

    // Add a train with its schedule
    Train* train1 = net.addTrain("123A", "Palace on Wheels");
    train1->addStop(delhi, "08:00", "08:10");
    train1->addStop(jaipur, "10:00", "10:15");
    train1->addStop(agra, "13:00", "13:05");

    // Print out schedule for the train
    cout << "Schedule for " << train1->name << " (" << train1->id << "):\n";
    for (auto& entry : train1->schedule) {
        cout << "  " << entry.station->name
             << "  Arrival: " << entry.arrival
             << "  Departure: " << entry.departure << "\n";
    }

    return 0;
}
/*

27. The railway network of our country is one of the most complex public establishments. You can design a database solution for this network and make the management of the same more natural. Your system should have the following pieces of information:
Station names
Tracks that connect those stations (to keep things simple, you can assume that only one track runs between two stations)
Train IDs with names
Schedules of the trains 
*/