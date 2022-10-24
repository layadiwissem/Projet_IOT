

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader implements DataReceiver {

	private String filename;
	private BufferedReader br;
	private boolean ok;

	public FileReader(String filename) {
		this.filename = filename;
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}
	public void open() {
		
		InputStream ips;
		try {
			ips = new FileInputStream(this.filename);
			InputStreamReader ipsr = new InputStreamReader(ips);
			br = new BufferedReader(ipsr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ok=true;
	
	}
	public String readDatagram() {
		try {
			String data =br.readLine();
			 if (data == null || data.equals("")) {
				 this.ok=false;
				 return null;
			 }else {
				 return data;
				 
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	public boolean ready() {
		// TODO Auto-generated method stub
		return this.ok;
	}
	
	public void close() {
		// TODO Auto-generated method stub
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ok=false;
	
}}
/*
 * public void open() {
		
		InputStream ips;
		try {
			ips = new FileInputStream(this.filename);
			InputStreamReader ipsr = new InputStreamReader(ips);
			br = new BufferedReader(ipsr);
			StringBuffer sb = new StringBuffer();    
		      String line;
		      try {
				while((line = br.readLine()) != null)
				  {
				    // ajoute la ligne au buffer
				    sb.append(line);      
				    sb.append("\n");     
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		      System.out.println("Contenu du fichier: ");
		      System.out.println(sb.toString()); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ok=true;
	
	}*/