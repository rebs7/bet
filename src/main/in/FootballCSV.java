package main.in;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


import main.entities.Equipas;
import main.entities.Jogos;
import main.entities.JogosId;
import main.utils.Utils;

public class FootballCSV implements Runnable {

	public void run() {
		try {
			String folderText = "C:\\PROG\\ZipFIles";
			String baseUrl = "http://www.football-data.co.uk/mmz4281/#####/data.zip";
			File folder = new File(folderText);

			for (int StartSeason = 2000; StartSeason < 2017; StartSeason++) {

				int endSeason = StartSeason + 1;
				String season = Integer.toString(StartSeason).substring(2) + Integer.toString(endSeason).substring(2);

			//	System.out.println(season);
		//		this.unpackArchive(new URL(baseUrl.replace("#####", season)), folder, season);

			}
			parseCSV(folderText, this.listFilesForFolder(folder));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public File unpackArchive(URL url, File targetDir, String season) throws IOException {
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		InputStream in = new BufferedInputStream(url.openStream(), 1024);
		// make sure we get the actual file
		File zip = File.createTempFile("arc", ".zip", targetDir);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(zip));
		this.copyInputStream(in, out);
		out.close();
		return unpackArchive(zip, targetDir, season);
	}

	/**
	 * Unpack a zip file
	 * 
	 * @param theFile
	 * @param targetDir
	 * @return the file
	 * @throws IOException
	 */
	public File unpackArchive(File theFile, File targetDir, String season) throws IOException {
		if (!theFile.exists()) {
			throw new IOException(theFile.getAbsolutePath() + " does not exist");
		}
		if (!buildDirectory(targetDir)) {
			throw new IOException("Could not create directory: " + targetDir);
		}
		ZipFile zipFile = new ZipFile(theFile);
		for (Enumeration<?> entries = zipFile.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			File file = new File(targetDir, File.separator + season + "__" + entry.getName());
			if (!buildDirectory(file.getParentFile())) {
				throw new IOException("Could not create directory: " + file.getParentFile());
			}
			if (!entry.isDirectory()) {
				copyInputStream(zipFile.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(file)));
			} else {
				if (!buildDirectory(file)) {
					throw new IOException("Could not create directory: " + file);
				}
			}
		}
		zipFile.close();
		theFile.delete();
		return theFile;
	}

	public void copyInputStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int len = in.read(buffer);
		while (len >= 0) {
			out.write(buffer, 0, len);
			len = in.read(buffer);
		}
		in.close();
		out.close();
	}

	public static boolean buildDirectory(File file) {
		return file.exists() || file.mkdirs();
	}

	public List<String> listFilesForFolder(final File folder) {
		ArrayList<String> list = new ArrayList<>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
				list.add(fileEntry.getName());
			}
		}
		return list;
	}

	public void parseCSV(String folder, List<String> files) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			for (int i = 0; i < files.size(); i++) {
				int lineNumber = 0;
				String csvFile = folder + "\\" + files.get(i);
				System.out.println(csvFile);

				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					if (lineNumber > 0) {

						String[] array = line.split(cvsSplitBy);
						if (array.length > 5) {
						
							Equipas equipaH = new Equipas(array[2], null, null, array[0]);
							Equipas equipaA = new Equipas(array[3], null, null, array[0]);
							String[] data= array[1].split("/");
							String dataFormatada="";
							if(data[2].length() <=2){
							if(Integer.parseInt(data[2]) <90){
								dataFormatada="20"+data[2]+"-"+data[1]+"-"+data[0];
							}else{
								
								dataFormatada="19"+data[2]+"-"+data[1]+"-"+data[0];
							}}else{
								dataFormatada=data[2]+"-"+data[1]+"-"+data[0];
							}
							Jogos jogo = new Jogos(new JogosId(equipaH.getNome(), equipaA.getNome(), dataFormatada));
							jogo.setCompeticao(Utils.competitionsFilter(array[0]));
							jogo.setGolos1(Integer.parseInt(array[4]));
							jogo.setGolos2(Integer.parseInt(array[5]));
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
							Date date = formatter.parse(array[1]);
							jogo.setDataReal(date);
							equipaA.setUser("CSV");
							equipaH.setUser("CSV");
							if(jogo.getCompeticao() != null){
							if (!equipaA.exists()) {
								equipaA.add();
								
							}
							if (!equipaH.exists()) {
								equipaH.add();
							}
							if (!jogo.exists()) {
								jogo.setEstado();
								jogo.add();
								
							}else{
								jogo.update();
							}
							}
						}
					}
					lineNumber++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		(new Thread(new FootballCSV())).start();
	}

}
