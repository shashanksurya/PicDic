/*
This java code helps tp extract the words in the app
into a single text file. The delimiter used is '|'

*/
import java.io.*;
import java.util.*;
class nisar{

	public static void main(String args[]) throws Exception{

		// Inititalise the assets folder
		String dir = "/home/nisar/Desktop/assets";

		File folder = new File(dir);

		FileWriter fos = new FileWriter("output.txt");

		//BufferedWriter bw = new BufferedWriter(new FileInputStream);

		// Get all directories inside the root folder
		File[] listOfDirectories = folder.listFiles();


		ArrayList<String> al = new ArrayList<String>();

		for (File f : listOfDirectories)
			al.add(f.getName());

		// Store all folders in ascending order
		Collections.sort(al);


		for (String s : al){
			
			// extract files from each directory
			File file = new File(dir + "/" + s + "/TEXT/");
			File[] listOfFiles = file.listFiles();

			ArrayList<String> files = new ArrayList<String>();

			for (File f : listOfFiles){
				if(f.isFile()){
					String fileName = f.getName();
					fileName = fileName.substring(0,fileName.indexOf('.'));
					files.add(fileName);
				}
			}

			Collections.sort(files);

			for (String str : files){
				File file1 = new File(dir + "/" + s + "/" + "TEXT/" + str + ".TXT");
				FileInputStream fis = new FileInputStream(file1);
				byte[] data = new byte[(int) file1.length()];
				fis.read(data);
				fis.close();
				String content = new String(data);
				
				String[] content_arr = content.split("\\.");
				

				String line = "";

				line += str;
				line += "|";
				for(int i = 0 ; i < content_arr.length ; i++){
					String token = content_arr[i];
					line += token.trim();
					line += "|";
				}
				line = line.substring(0,line.length() - 1);
				fos.write(line);
				fos.write("\n");

			}




		}

		fos.close();
			
		


	}

}
