package TavernaParser;

import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

abstract public class TemplateParser {
    protected static RDFManager rdfManager;
    protected Statement statement;

    public TemplateParser(Statement statement){
        this.statement = statement;
    }

    public static void closeFile() {
        rdfManager.close();
    }

    abstract public void execute();

    protected static void initializeRdfManager(String inputFile) {
        rdfManager = new RDFManager(inputFile);
    }

    protected static String stringfyObject(RDFNode object) {
        if (object instanceof Resource && object.asResource().getLocalName() != null && !object.asResource().getLocalName().equals(""))
            return object.asResource().getLocalName().split("\\^")[0];
        else
            return object.toString().split("\\^")[0];
    }
}
