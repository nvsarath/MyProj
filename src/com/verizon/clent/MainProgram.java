package com.verizon.clent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;



public class MainProgram {
	static JSONObject custObj = new JSONObject();
	public static void main(String args[]) {
		long startTime = System.currentTimeMillis();
		System.out.println(startTime);
		BufferedReader reader = null;
		ArrayList<Thread> threads = new ArrayList<Thread>();
		try {
			//readFileMetaData();
			ArrayList<File> filesList = new ArrayList<File>();
			
			filesList = getFilesList(filesList);
			System.out.println("filesList size :" + filesList.size());
			for(int i=0; i<filesList.size();i++) {
				FileReader fr = new FileReader((File)filesList.get(i));
				String fileName = filesList.get(i).getName();
				String str[] = fileName.split("_");
				reader = new BufferedReader(fr);
				for (int j = 0; j < 1; j++) {
					Runnable task = new MultiThreadedProcessor(reader,str[1],custObj);
					Thread worker = new Thread(task);
					// We can set the name of the thread
					worker.setName(String.valueOf(j));
					// Start the thread, never call method run() direct
					worker.start();
					// Remember the thread for later usage
					threads.add(worker);
				}

				int running = 0;
				int runner1 = 0;
				int runner2 = 0;
				do {
					running = 0;
					for (Thread thread : threads) {
						if (thread.isAlive()) {
							runner1 = running++;
						}
					}
					if (runner2 != runner1) {
						runner2 = runner1;
						System.out.println("We have " + runner2 + " running threads. ");

					}
				} while (runner1 > 0);

				if (runner2 == 0) {
					System.out.println(custObj.length());
					System.out.println("Ended");
				}
				long endTime = System.currentTimeMillis();
				System.out.println("Total time taken : " + (endTime-startTime));
				
				//   	public static Map getCustomerObj(String customerId) {

				System.out.println(HazelCastClientUtil.getCustomerObj("100"));
				
				//System.out.println(custDateUsageMap);
			}

		}catch(Exception e) {

		}
	}


	private static ArrayList<File> getFilesList(ArrayList<File> filesList) {
		File fd=new File("C:\\Users\\Administrator\\workspace\\MyProj\\UsageDetails");
		if(fd.isDirectory()){
			filesList = listFilesForFolder(fd,filesList);
		} else {
			filesList.add(fd);
		}
		return filesList;
	}


	public static ArrayList<File> listFilesForFolder(File entity, ArrayList<File> filesList) {
		if(entity.isDirectory()) {
			for (File fileEntry : entity.listFiles()) {
				filesList.add(fileEntry);
				listFilesForFolder(fileEntry,filesList);
			}			
		}
		return filesList;
	}

	private static void readFileMetaData() throws Exception {
		// TODO Auto-generated method stub
		Path file=Paths.get("C:\\Users\\Administrator\\workspace\\MyProj\\src\\test_Oct28_Part1.txt");
		BasicFileAttributes basicAttr = Files.readAttributes(file, BasicFileAttributes.class);
		FileTime creationTime = basicAttr.creationTime();
		System.out.println("creationTime : " + creationTime);
		FileTime lastAccessTime = basicAttr.lastAccessTime();
		System.out.println("lastAccessTime : " + lastAccessTime);
		FileTime lastModifiedTime = basicAttr.lastModifiedTime();
		System.out.println("lastModifiedTime : " +lastModifiedTime);

	}
}