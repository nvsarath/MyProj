package com.verizon.clent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class TestProgram {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//readFileMetaData();
		String line;
		ArrayList<File> filesList = new ArrayList<File>();
		String customerId;
		String currentUsage;
		String existingUsage;
		int accumilatedUsage = 0;
		String siteName;

		filesList = getFilesList(filesList);
		//System.out.println("filesList size :" + filesList.size());
		ArrayList<String> excludeSites = new ArrayList<String>();
		loadExcludeSites(excludeSites);
		for(int i=0; i<filesList.size();i++) {
			FileReader fr = new FileReader((File)filesList.get(i));
			BufferedReader reader = new BufferedReader(fr);
			while ((line = reader.readLine()) != null) {

				try {
					//System.out.println(line);
					StringTokenizer st = new StringTokenizer(line,",");
					while(st.hasMoreElements()) {
						customerId = st.nextToken();
						currentUsage = st.nextToken();
						siteName = st.nextToken();
						if("100".equals(customerId) && !excludeSites.contains(siteName)) {
							System.out.println(line  + "     " + ((File)filesList.get(i)).getName());
							accumilatedUsage +=Integer.parseInt(currentUsage);
						}
					}
				}catch(Exception e) {
					
				}
			}
		}
		System.out.println(accumilatedUsage);
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
		private static void loadExcludeSites(ArrayList<String> excludeSites) {
			// TODO Auto-generated method stub
			excludeSites.add("facebook.com");

		}
}
