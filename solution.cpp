#include <iostream>
#include <numeric>
#include <vector>

using namespace std;

// calculate the checksum
unsigned short calculateChecksum(const vector<unsigned short> &data) {
  unsigned long sum = 0;

  for (unsigned short word : data) {
    sum += word;
  }

  while (sum >> 16) {
    sum = (sum & 0xFFFF) + (sum >> 16);
  }

  return (unsigned short)~sum;
}

// verify the checksum at receiver's end
bool verifyChecksum(vector<unsigned short> data,
                    unsigned short receivedChecksum) {
  unsigned long sum = 0;

  // Add all data words
  for (unsigned short word : data) {
    sum += word;
  }

  // Add the received checksum to the sum
  sum += receivedChecksum;

  // Handle carry-over
  while (sum >> 16) {
    sum = (sum & 0xFFFF) + (sum >> 16);
  }

  // If the data is correct, the 1's complement of the sum should be 0
  // Or, the sum itself should be 0xFFFF (all 1s)
  return (unsigned short)~sum == 0;
}

int main() {
  // Example data: A list of 16-bit integers
  vector<unsigned short> packet = {0x4500, 0x003c, 0x1c46, 0x4000, 0x4006};

  cout << "--- Sender Side ---" << endl;
  unsigned short checksum = calculateChecksum(packet);
  printf("Calculated Checksum: 0x%04X\n", checksum);

  cout << "\n--- Receiver Side ---" << endl;
  if (verifyChecksum(packet, checksum)) {
    cout << "Success: Data is valid (no errors detected)." << endl;
  } else {
    cout << "Error: Data corruption detected!" << endl;
  }

  // Simulating an error by changing a value in the packet
  cout << "\n--- Simulating Error ---" << endl;
  packet[0] = 0x9999;
  if (verifyChecksum(packet, checksum)) {
    cout << "Success: Data is valid." << endl;
  } else {
    cout << "Error: Data corruption detected!" << endl;
  }

  return 0;
}