// A class should have only a sing responsibility
#include<iostream>
#include<cstdio>
#include<string>
#include<vector>
#include<fstream>
#include<boost/lexical_cast.hpp>
using namespace std;
using namespace boost;

struct Journal{
    string title;
    vector<string>entries;
    Journal(const string &title): title(title){}

    void add_entry(const string &entry)
    {
        static int count =1;
        entries.push_back(lexicla_cast<string>(count++)+": "+entry);
    }
    void save(const string& filename)
    {
        ofstream ofs(filename);
        for(auto& e : entries)
        ofs<<e<<endl;
    }
};
 struct PersistenceManager{
    static void save(const Journal& j,const string& filename)
    {
        ofstream ofs(filename);
        for(auto& e : j.entries)
        ofs<<e<<endl;
    } 
 };
int main()
{
    Journal journal{"Dear Diary"};
    journal.add_entry("I ate the food");
    journal.add_entry("I played cricket");
    journal.save("diary.txt")
    PersistenceManager pm;
    pm.save(journal,"diary2.txt");
    getchar();
    return 0;
}