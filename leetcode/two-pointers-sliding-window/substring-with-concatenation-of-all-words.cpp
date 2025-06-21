#include <iostream>
#include <vector>
#include <unordered_map>
#include <string>
using namespace std;

vector<int> solve(string s, vector<string>& words) {
    unordered_map<string, int> wordMap;
    vector<int> result;
    int wordLen = words[0].size();
    int totalWords = words.size();
    int windowSize = wordLen * totalWords;

    for (const string& word : words) {
    wordMap[word]++;
    }
    

    for(int offset = 0; offset < wordLen; offset++) {
        unordered_map<string, int> windowMap;
        int wordsInWindow = 0;

        for(int i = offset; i + wordLen <= s.size(); i += wordLen) {
            string currWord = s.substr(i, wordLen);
            
            // check if currWord is present in the wordMap
            if(wordMap.find(currWord) == wordMap.end()) {
                // if not present then clear the map and also make wordsInWindow = 0
                windowMap.clear();
                wordsInWindow = 0;
            }

            // if currWord is present in the wordMap increase words count and also frequency in windowMap
            windowMap[currWord]++;
            wordsInWindow++;


            // shrinking condition
            // while frequency of currword in windwMap > freq of currword in wordMap then shrink window
            while(windowMap[currWord] > wordMap[currWord]) {
                int firstWordIdx = i - (wordsInWindow - 1) * wordLen;
                string firstWord = s.substr(firstWordIdx, wordLen);
                // decrement the word count in window and also decrease the frequency in windowMap
                windowMap[firstWord]--;
                wordsInWindow--;

            }

            // if frequency of currword in windwMap == freq of currword in wordMap
            if(wordsInWindow == totalWords) {
                int startIdx = i - (wordsInWindow - 1) * wordLen;
                result.push_back(startIdx);
            }
        }
    }
    return result;
}

int main() {

    string s = "barfoothefoobarman";
    vector<string> words = {"foo","bar"};

    vector<int> ans = solve(s, words);
    for (int x : ans) cout << x << " ";
    cout << endl;

    return 0;

}