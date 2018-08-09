package TavernaParser.Relationships;

import TavernaParser.Edge;
import TavernaParser.Models.Prov;
import TavernaParser.Models.ProvONE;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

public class hasDefaultParam extends TemplateParser {
    public hasDefaultParam(Statement statement) {
        super(statement);
    }

    public void execute() {
        Node n1 = new Node(this.statement.getObject().toString(), ProvONE.PORT);
        Node n2 = new Node(this.statement.getSubject().toString(), Prov.ENTITY);
        Edge e = new Edge(ProvONE.HASDEFAULTPARAM, n1, n2);
        e.save();
    }
}
