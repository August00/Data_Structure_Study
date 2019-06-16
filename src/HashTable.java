import java.util.TreeMap;

public class HashTable<K,V> {
    private static int[] capacity = {
            53, 97, 193, 389, 769, 1543, 3079, 6151,
            12289, 24593, 49157, 98317, 196613, 393241, 786433,
            1572869, 3145739, 6291469, 12582917, 25165843, 50331653,
            100663319, 201326611, 402653189, 805306457, 1610612741
    };
    private TreeMap<K,V>[] hashTable;
    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static int initCapacity = 0;
    private int M;
    private int size;

    public HashTable(){
        this.M = capacity[initCapacity];
        size = 0;
        hashTable = new TreeMap[M];
        for(int i = 0; i<M; i++){
            hashTable[i] = new TreeMap<K,V>();
        }
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff)%M;
    }

    public void add(K key,V value){
        TreeMap map = hashTable[hash(key)];
        if(map.containsKey(key)){
            map.put(key,value);
        } else {
           map.put(key,value);
           size++;
           if(size >= M * upperTol && initCapacity+1 < capacity.length) {
               resize(capacity[++initCapacity]);
           }
        }
    }

    public V remove(K key){
        TreeMap map = hashTable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = (V) map.remove(key);
            size --;
            if(size < M*lowerTol && initCapacity-1>0){
                resize(capacity[--initCapacity]);
            }
        }
        return ret;
    }

    public void set(K key,V value){
        TreeMap<K,V> map =  hashTable[hash(key)];
        if(!map.containsKey(key))
            throw new IllegalArgumentException(key+" doesn't exit!");
        map.put(key,value);
    }

    public V get(K key){
        return hashTable[hash(key)].get(key);
    }

    public boolean contains(K key){
        return hashTable[hash(key)].containsKey(key);
    }

    public int getSize(){
        return size;
    }

    private void resize(int newCapacity){
        System.out.println(newCapacity);
        TreeMap<K,V>[] treeMap = new TreeMap[newCapacity];
        for(int i=0; i<newCapacity; i++){
            treeMap[i] = new TreeMap<>();
        }
        int oldM = M;
        this.M = newCapacity;
        for(int i=0; i<oldM; i++){
            TreeMap<K,V> map = hashTable[i];
            for(K key:map.keySet()){
                treeMap[hash(key)].put(key,map.get(key));
            }
        }
        this.hashTable = treeMap;
    }

//    public static void main(String[] args){
//        HashTable<String,Integer> hashTable = new HashTable<>();
//        for(int i = 0;i<1000;i++) {
//            hashTable.add("i"+i,null);
//        }
//        System.out.println(hashTable.getSize());
//    }
}
