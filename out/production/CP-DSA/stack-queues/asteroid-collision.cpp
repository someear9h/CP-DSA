// https://leetcode.com/problems/asteroid-collision/description/

#include <iostream>
#include <vector>
#include <stack>
using namespace std;

vector<int> asteroidCollision(vector<int> &asteroids) {
    stack<int> st;
    for(int asteroid : asteroids) {
        bool asteroidDestroyed = false;

        while(!st.empty() && asteroid < 0 && st.top() > 0) {
            
            if(abs(asteroid) > st.top()) {
                st.pop();
                continue; // check collisions for next asteroids in stack
            } else if(abs(asteroid) == st.top()) {
                st.pop();
            }
            
            asteroidDestroyed = true;
            break; // exit loop and check for next asteroid, this one is destroyed
        }

        if(!asteroidDestroyed) { // asteroid is not destroyed
            st.push(asteroid);
        }
    }

    vector<int> res(st.size());
    for(int i = st.size() - 1; i >= 0; i--) {
        res[i] = st.top();
        st.pop();
    }
    return res;
}

int main() {
    vector<int> asteroids = {5,10,-5};
    vector<int> res = asteroidCollision(asteroids);
    for(int i = 0; i < res.size(); i++) {
        cout << res[i] << " ";
    }

    return 0;
}
    
    