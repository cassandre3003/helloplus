package fr.ul.miage.helloplus;

import java.io.*;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.csv.*;
import org.apache.commons.cli.*;

/**
 * Hello world!
 *
 */
public class App 
{
	
	
	private static final Logger LOG = Logger.getLogger(App.class.getName());
	
	private String filename;
	
    public App(String filename) {
		super();
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public CSVParser buidCVSParser() throws IOException {
		CSVParser res = null;
		Reader in;
		in = new FileReader(filename);
		CSVFormat csvf = CSVFormat.DEFAULT.withCommentMarker('#').withDelimiter(';');
		res = new CSVParser(in, csvf);
		return res;
	}
	
	
	
	
	public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	
    	String filename = null;
    	
    	Options options = new Options();
    	Option input = new Option("i", "input", true, "nom du fichier .csv contenant la liste des données");
    	
    	CommandLineParser parser = new DefaultParser();
    	try {
    		CommandLine line = parser.parse( options, args );
    		if (line.hasOption("i")) {
    			filename = line.getOptionValue("i");
    		}
    	}
    	catch( ParseException exp ) {
    		LOG.severe("Erreur dans la ligne de commande");
    		HelpFormatter formatter = new HelpFormatter();
    		formatter.printHelp("App", options);
    		System.exit(1);
    	}
    	
    	
    	App app = new App(filename);
    	try {
    		CSVParser p = app.buidCVSParser();
    		for (CSVRecord r : p) {
    			String nom = r.get(0);
    			String prenom = r.get(1);
    			System.out.println("Hello "+nom+" "+prenom+" !");
    		}    		
    	}
    	catch (IOException e) {
    		LOG.severe("Erreur de lecture dans le fichier CSV");
    	}
    }
}
