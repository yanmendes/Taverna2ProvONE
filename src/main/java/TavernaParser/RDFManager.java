package TavernaParser;

import org.apache.jena.rdf.model.*;

import java.util.ArrayList;

public class RDFManager extends Object {

    private Model model;

    public RDFManager(String inputFileName) {
        model = ModelFactory.createDefaultModel();
        model.read(inputFileName, "TTL");
    }

    public ArrayList<Resource> getResource(String pred, String obj) {

        ArrayList<Resource> resourceList = new ArrayList();

        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();

            if (predicate.getLocalName().equals(pred) && object.asResource().getLocalName().equals(obj))
                resourceList.add(subject);
        }
        return resourceList;
    }

    public ArrayList<Resource> getResource(String pred, RDFNode obj) {

        ArrayList<Resource> resourceList = new ArrayList();

        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();

            if (predicate.getLocalName().equals(pred) && object.asResource().equals(obj)) {

                resourceList.add(subject);

            }
        }
        return resourceList;
    }

    public ArrayList<Statement> getStatements() {

        ArrayList<Statement> stmtList = new ArrayList();

        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            stmtList.add(stmt);
        }

        return stmtList;
    }

    public ArrayList<Statement> getStatementsBySubject(String subj) {

        ArrayList<Statement> stmtList = new ArrayList();

        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();

            if (subject.getLocalName() != null && subject.getLocalName().equals(subj))
                stmtList.add(stmt);
        }
        return stmtList;
    }

    public void close() {
        model.close();
    }
}