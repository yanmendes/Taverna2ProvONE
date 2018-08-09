package TavernaParser.Nodes;

import TavernaParser.Models.Prov;
import TavernaParser.Models.ProvONE;
import TavernaParser.Node;
import TavernaParser.TemplateParser;
import org.apache.jena.rdf.model.Statement;

public class Entity extends TemplateParser {
    public Entity(Statement statement) {
        super(statement);
    }

    public void execute() {
    }

    public void addAttribute() {
        Node node = new Node(statement.getSubject().toString(), Prov.ENTITY);

        String strObj = stringfyObject(this.statement.getObject());
        node.addAtribute(Prov.VALUE, strObj);

        if (strObj.contains(".txt") || strObj.contains(".doc") || strObj.contains(".pdf") || strObj.contains(".csv") || strObj.contains(".fasta"))
            node.addAtribute(Prov.TYPE, ProvONE.DOCUMENT);
        else if (strObj.contains(".png") || strObj.contains(".jpg") || strObj.contains(".jpeg") || strObj.contains(".bmp") || strObj.contains(".gif") || strObj.contains(".tif"))
            node.addAtribute(Prov.TYPE, ProvONE.VISUALIZATION);
        else
            node.addAtribute(Prov.TYPE, ProvONE.DOCUMENT);

        node.save();
    }
}
