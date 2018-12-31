package projeh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;


public class Store {

	private File selFile;
	
	public Store() {
		// TODO Auto-generated constructor stub
	}
	public void save(Map m) {
		
		String filename = File.separator + "tmp";
	    JFileChooser fc = new JFileChooser(new File(filename));
	    fc.showSaveDialog(null);
	    File selFile = fc.getSelectedFile();
	    
		
	      try
	      {
	         FileOutputStream fileOut = new FileOutputStream(selFile.getAbsolutePath());
	         
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
	         out.writeObject(m);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	public Map load() {
	      Map m = null;
			String filename = File.separator + "tmp";
		    JFileChooser fc = new JFileChooser(new File(filename));
		    fc.showOpenDialog(null);
		    setSelFile( fc.getSelectedFile());
		    
	      try
	      {
	         FileInputStream fileIn = new FileInputStream(selFile.getAbsolutePath());
	         if(fileIn.available() != 0)
	         {
	        	 ObjectInputStream in = new ObjectInputStream(fileIn);
		         m = (Map) in.readObject();
		         in.close();
		         fileIn.close();
	        
	         }
	
	        }catch(IOException i)
	      {
	         i.printStackTrace();
	         System.err.println("Wrong!");
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println(" not found");
	         c.printStackTrace();
	      } 
	      return m;
	}
	public void setSelFile(File selFile) {
		this.selFile = selFile;
	}
	public File getSelFile() {
		return selFile;
	}
}