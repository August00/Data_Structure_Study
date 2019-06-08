package Tree.Heap;

import java.util.*;

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num:nums){
            if(map.containsKey(num))
                map.put(num,map.get(num)+1);
            else
                map.put(num,1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue();
        for(Map.Entry entry:map.entrySet()){
            if(pq.size()<k){
                pq.add((Integer) entry.getKey());
            }
            else{
                if(map.get(entry.getKey())>map.get(pq.peek())){
                    pq.poll();
                    pq.add((Integer) entry.getKey());
                }
            }

        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<k;i++){
            list.add((Integer) pq.poll());
        }
        return list;
    }

    public void main(String[] args){
        int[] arr = {0,1,1,-1,3,-1,4};
        System.out.println(topKFrequent(arr,3));
    }
}
