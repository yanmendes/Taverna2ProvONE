package TavernaParser.Relationships;

import TavernaParser.Edge;
import TavernaParser.Models.Prov;
import TavernaParser.Models.ProvONE;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

public class wasGeneratedBy extends TemplateParser {
    public wasGeneratedBy(Statement statement) {
        super(statement);
    }

    public void execute() {
        Node n1 = new Node(this.statement.getSubject().toString(), Prov.ENTITY);
        Node n2 = new Node(this.statement.getObject().toString(), ProvONE.EXECUTION);
        Edge e = new Edge(Prov.WASGENERATEDBY, n1, n2);
        e.save();
    }
}
