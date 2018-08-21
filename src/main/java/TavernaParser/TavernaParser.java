package TavernaParser;

import TavernaParser.Nodes.Collection;
import TavernaParser.Nodes.Entity;
import TavernaParser.Nodes.Execution;
import TavernaParser.Relationships.*;
import org.apache.jena.rdf.model.Statement;

public class TavernaParser {
    private static void usage(String[] args) {
        System.out.println(String.format("Invalid number of arguments: expected 5, got %s", args.length));
        System.out.println("Usage:");
        System.out.println("java -jar TavernaParser.jar neo4jUri neo4jUsername neo4jPassword workflowIdentifier turtleFile");
        System.exit(1);
    }

    private static void setUp(String[] args) {
        try {
            Neo4jInterface.logging(false);
            Neo4jInterface.initializeDb(args[0], args[1], args[2]);
            Neo4jInterface.initializeSession();
            Neo4jInterface.setWorkflowIdentifier(args[3]);
            TemplateParser.initializeRdfManager(args[4]);

        } catch (Exception e) {
            cleanUp();
            e.printStackTrace();
            System.exit(2);
        }
    }

    private static void cleanUp() {
        Neo4jInterface.closeSession();
        Neo4jInterface.closeDbConnection();
        TemplateParser.closeFile();
    }

    public static void main(String[] args) {
        if (args.length != 5)
            usage(args);

        setUp(args);

        for (Statement statement : TemplateParser.rdfManager.getStatements()) {
            switch (statement.getPredicate().getLocalName()) {
                //Atomic relationships
                case "agent":
                    (new agent(statement)).execute();
                    break;

                case "activity":
                    (new activity(statement)).execute();
                    break;

                case "entity":
                    (new entity(statement)).execute();
                    break;

                case "describedByParameter":
                    (new hasDefaultParam(statement)).execute();
                    break;

                case "hasInput":
                    (new hasInput(statement)).execute();
                    break;

                case "hasOutput":
                    (new hasOutput(statement)).execute();
                    break;

                case "hadPlan":
                    (new hadPlan(statement)).execute();
                    break;

                case "hadMember":
                    (new qualifiedGeneration(statement)).execute();
                    break;

                case "hasSubProcess":
                    (new hasSubProgram(statement)).execute();
                    break;

                case "qualifiedGeneration":
                    (new qualifiedGeneration(statement)).execute();
                    break;

                case "qualifiedUsage":
                    (new qualifiedUsage(statement)).execute();
                    break;

                case "qualifiedAssociation":
                    (new qualifiedAssociation(statement)).execute();
                    break;

                case "usedInput":
                case "used":
                    (new used(statement)).execute();
                    break;

                case "wasAssociatedWith":
                    (new wasAssociatedWith(statement)).execute();
                    break;

                case "wasGeneratedBy":
                    (new wasGeneratedBy(statement)).execute();
                    break;

                case "wasInformedBy":
                    (new wasInformedBy(statement)).execute();
                    break;

                case "wasOutputFrom":
                    (new wasGeneratedBy(statement)).execute();
                    break;

                case "hasPart":
                case "wasPartOfWorkflowRun":
                    (new wasPartOf(statement)).execute();
                    break;

                //Composite relationships
                case "describedByWorkflow":
                case "describedByProcess":
                    String associationHash = (new qualifiedAssociation(statement)).execute(true);
                    (new hadPlan(statement)).execute(associationHash);
                    break;

                //Descriptive triples
                case "content":
                    (new Entity(statement)).addAttribute();
                    break;

                case "label":
                case "endedAtTime":
                case "startedAtTime":
                    (new Execution(statement)).addAttribute();
                    break;

                case "type":
                    switch (TemplateParser.stringfyObject(statement.getObject())) {
                        case "Collection":
                            (new Collection(statement)).execute();
                            break;
                    }
                    break;

                default:
                    System.out.println(statement);
                    break;

                //Not captured by ProvONE
                case "hadRole":
                case "sha512":
                case "sha1":
                case "wasEnactedBy":
                    continue;
            }
        }

        cleanUp();
    }
}
