package com.verizon.clent;
import java.io.*; 
import java.util.ArrayList;
import java.util.Random;
class UsageFileCreator { 
	public static void main(String args[]) throws Exception { 
		File f=new File("C:\\Users\\Administrator\\workspace\\MyProj\\UsageDetails\\test_Oct29_Part1.txt");

		int customerId =100;
		int usage = 0;
		Random randomGenerator = new Random();
		ArrayList<String> sitesList = new ArrayList<String>();
		sitesList.add("google.com");
		sitesList.add("facebook.com");
		sitesList.add("yahoo.com");
		sitesList.add("twitter.com");
		sitesList.add("cricinfo.com");
		sitesList.add("greatandhra.com");
		sitesList.add("timesofindia.com");
		sitesList.add("thehindu.com");
		sitesList.add("smartprix.com");
		sitesList.add("amazon.com");
		FileWriter f0 = new FileWriter(f,true);
		for(int j=0;j<100;j++) {
			int i=customerId;
			for (; i < 1000; i += 1) { 
				usage=randomGenerator.nextInt(100);
				int siteInd = usage/10;
				f0.write(i + "," + usage + "," + sitesList.get(siteInd)); 
				f0.write("\n");
			} 
		}
		f0.close(); 
	} 
}