package TavernaParser.Nodes;

import TavernaParser.Models.ProvONE;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

public class Execution extends TemplateParser {
    public Execution(Statement statement) {
        super(statement);
    }

    public void execute() {
        return;
    }

    public void addAttribute() {
        Node node = new Node(statement.getSubject().toString(), ProvONE.EXECUTION);
        node.addAtribute(statement.getPredicate().getLocalName(), stringfyObject(statement.getObject())).save();
    }
}
