package TavernaParser.Relationships;

import TavernaParser.Edge;
import TavernaParser.Models.ProvONE;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

public class hasOutput extends TemplateParser {
    public hasOutput(Statement statement) {
        super(statement);
    }

    public void execute() {
        Node n1 = new Node(this.statement.getSubject().toString(), ProvONE.PROGRAM);
        Node n2 = new Node(this.statement.getObject().toString(), ProvONE.PORT);
        Edge e = new Edge(ProvONE.HASOUTPORT, n1, n2);
        e.save();
    }
}
