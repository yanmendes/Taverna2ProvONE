package TavernaParser.Nodes;

import TavernaParser.Models.Prov;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

public class Collection extends TemplateParser {
    public Collection(Statement statement) {
        super(statement);
    }

    public void execute() {
        Node node = new Node(this.statement.getSubject().toString(), Prov.ENTITY);
        node.addAtribute(Prov.TYPE, Prov.COLLECTION);

        node.save();
    }
}
