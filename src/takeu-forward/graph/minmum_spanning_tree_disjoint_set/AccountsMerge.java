package graph.minmum_spanning_tree_disjoint_set;

import java.util.*;

/*
Problem:
    https://takeuforward.org/data-structure/accounts-merge-dsu-g-50/
    https://leetcode.com/problems/accounts-merge/description/
 */
public class AccountsMerge {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N+E) + O(E*4ɑ) + O(N*(ElogE + E))
            where N = no. of indices or nodes and E = no. of emails.
            The first term is for visiting all the emails.
            The second term is for merging the accounts.
            And the third term is for sorting the emails and storing them in the answer array.

        Space Complexity: O(N)+ O(N) +O(2N) ~ O(N) where N = no. of nodes/indices.
            The first and second space is for the ‘mergedMail’ and the ‘ans’ array.
            The last term is for the parent and size array used inside the Disjoint set data structure.
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size();
        DisjoinSetByUnionSet disjoinSetByUnionSet = new DisjoinSetByUnionSet(n);

        HashMap<String, Integer> mailNodeMap = new HashMap<>();

        // iterate through every email and attach with its parent in a hash map
        // also invoke union for repeated emails, thus performing path compression and finding ultimate parents.
        for(int i = 0; i < accounts.size(); i++){
            // since email id's will start from index 1 in each row, we start iterating from index 1
            for(int j = 1; j < accounts.get(i).size(); j++){
                String email = accounts.get(i).get(j);
                if(mailNodeMap.containsKey(email) == false) {
                    /* if current email, is not found in the map means
                     we can add it and it should be linked to index - i
                     because this email belongs to person at index i*/
                    mailNodeMap.put(email, i);
                }
                /* else means, this email is already present and was used by someone else
                 so we have to merge, we can use union operation*/
                else
                    disjoinSetByUnionSet.unionBySet(i, mailNodeMap.get(email));
            }
        }

        // now, put each email in appropriate arraylist based on ultimate parent
        ArrayList<ArrayList<String>> mergeEmails = new ArrayList<>(n);

        for(int i = 0; i < n; i++)
            mergeEmails.add(new ArrayList<String>());

        for(Map.Entry map : mailNodeMap.entrySet()){
            String email = (String) map.getKey();
            int node = disjoinSetByUnionSet.findUltimateParent((int)map.getValue());

            mergeEmails.get(node).add(email);
        }

        /* we have each email available in their correct index list after merge operation
         but each of those list should be sorted
         and we have to add actual names of the person to first index*/
        List<List<String>> answer = new ArrayList<>(n);

        for(int i = 0; i < n; i++){
            // if the list is empty, then don't do thing, skip iteration.
            if(mergeEmails.get(i).isEmpty())
                continue;

            Collections.sort(mergeEmails.get(i));

            ArrayList<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            for(String it : mergeEmails.get(i))
                temp.add(it);

            answer.add(temp);
        }

        return answer;
    }
}
