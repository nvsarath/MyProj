package com.verizon.clent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
 
public class HazelCastClientUtil {
 
    @SuppressWarnings({ "deprecation" })
	public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        System.setProperty( "hazelcast.logging.type", "none" );
        clientConfig.addAddress("127.0.0.1");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Object, Object> map = client.getMap("customers");
        
        System.out.println(map.get("101"));
      
    }
  
    @SuppressWarnings("unchecked")
    public static void printMap(@SuppressWarnings("rawtypes") Map map){
    	System.out.println("Map Size:" + map.size());
		Set<Entry<Integer,String>> customers = map.entrySet();
       for(Iterator<Entry<Integer, String>> iterator = customers.iterator(); iterator.hasNext();) {
        	Entry<Integer, String> entry = (Entry<Integer, String>) iterator.next();
        	System.out.println("Customer Id : "+ entry.getKey()+" Customer Name : "+entry.getValue());
      }	
    }
    
    @SuppressWarnings({ "deprecation" })
   	public static Map getCustomerObj(String customerId) {
    	System.setProperty( "hazelcast.logging.type", "none" );
           ClientConfig clientConfig = new ClientConfig();
           clientConfig.addAddress("127.0.0.1");
           HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
           IMap<Object, Object> imap = client.getMap("customers");
          
           
         return  (Map) imap.get(customerId);
       }

	public static void loadMap(String customerId,Map custDateUsageMap) {
		System.setProperty( "hazelcast.logging.type", "none" );
		ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("127.0.0.1");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Object, Object> imap = client.getMap("customers");
        imap.put(customerId, custDateUsageMap);
       
    }

    
}
