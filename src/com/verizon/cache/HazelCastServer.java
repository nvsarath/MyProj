package com.verizon.cache;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.verizon.clent.HazelCastClientUtil;
 
public class HazelCastServer {
 
    public static void main(String[] args) {
        Config cfg = new Config();
        System.setProperty( "hazelcast.logging.type", "none" );
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        
        Map<Integer, String> customerMap = instance.getMap("customers");
     /*   customerMap.put(1, "Bangalore");
        customerMap.put(2, "Chennai");
        customerMap.put(3, "Hyderabad");*/
        //customerMap.clear();
        System.out.println("Map before  Size:" + customerMap.size()); 
        customerMap.clear();
        System.out.println("Map after clear Size:" + customerMap.size()); 
        Set<Entry<Integer,String>> customers = customerMap.entrySet();

        for (Iterator<Entry<Integer, String>> iterator = customers.iterator(); iterator.hasNext();) {
        		Entry<Integer, String> entry = (Entry<Integer, String>) iterator.next();
        		System.out.println("Customer Id : "+ entry.getKey()+" Customer Name : "+entry.getValue());
      }
        
        
        HazelCastClientUtil.printMap(customerMap);
        
        customerMap.remove(1);
        HazelCastClientUtil.printMap(customerMap);
    }
    
    
    public void loadIntoHazelCache(){
    	
    }
    
    
}
